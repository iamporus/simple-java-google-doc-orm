package spreadsheet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import util.ReflectionUtil;


import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * This implementation loads data from a Google spreadsheet.
 */
public class GoogleSpreadSheetLoader implements SpreadSheetLoader {

    private String workbook;

    private SpreadsheetService service;

    public static final String DEFAULT_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";

    private String url = DEFAULT_URL;

    /**
     * Create a loader for the provided user.
     * 
     * @param user
     *            the user
     * @param password
     *            the password
     * @param key
     *            key for google to know who is connecting (can be anything)
     */
    public GoogleSpreadSheetLoader(String user, String password, String key) {
        try {
            service = new SpreadsheetService(key);
            service.setUserCredentials(user, password);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> loadSheetAsList(String workSheet, Class<T> clazz) {
        try {

            SpreadsheetEntry workBookEntry = getSpreadSheet(workbook);

            WorksheetEntry worksheetEntry = getWorksheetEntry(workBookEntry, workSheet);

            List<T> objects = createDataObjects(worksheetEntry, clazz);

            return objects;

        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        } catch (ServiceException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Load objects from worksheet into a list of the provided class
     * 
     * @param <T>
     *            The type to create
     * @param worksheetEntry
     *            the worksheet to pulll from
     * @param clazz
     * @return a list of the objects
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws IOException
     */
    private <T> List<T> createDataObjects(WorksheetEntry worksheetEntry, Class<T> clazz) throws MalformedURLException, ServiceException, IOException {

        URL listFeedUrl = worksheetEntry.getListFeedUrl();

        ListFeed feed1 = service.getFeed(listFeedUrl, ListFeed.class);

        List<T> objects = new ArrayList<T>();

        for (ListEntry entry1 : feed1.getEntries()) {

            try {

                T item = clazz.newInstance();
                objects.add(item);

                for (String tag : entry1.getCustomElements().getTags()) {
                    ReflectionUtil.setMember(item, tag, entry1.getCustomElements().getValue(tag));
                }

            } catch (Exception e) {
                throw new IllegalStateException("Error creating object of type " + clazz);
            }
        }

        return objects;

    }

    /**
     * 
     * Get a handle on a sheet in a workbook.
     * 
     * @param spreadSheetEntry
     * @param workSheet
     * @return
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws IOException
     */
    private WorksheetEntry getWorksheetEntry(SpreadsheetEntry spreadSheetEntry, String workSheet) throws MalformedURLException, ServiceException,
            IOException {

        List<WorksheetEntry> worksheets = spreadSheetEntry.getWorksheets();
        for (WorksheetEntry worksheet : worksheets) {

            if (worksheet.getTitle().getPlainText().equals(workSheet)) {
                return worksheet;
            }
        }

        throw new IllegalStateException("Could not load spreadsheet " + workSheet);

    }

    /**
     * Find a spreadsheet in the users directory.
     * 
     * @param spreadSheetName
     * @return
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws IOException
     */
    private SpreadsheetEntry getSpreadSheet(String spreadSheetName) throws MalformedURLException, ServiceException, IOException {

        URL metafeedUrl = new URL(url);

        SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        for (SpreadsheetEntry spreadSheet : spreadsheets) {

            if (spreadSheet.getTitle().getPlainText().equals(spreadSheetName)) {
                return spreadSheet;
            }
        }

        throw new IllegalStateException("Could not load spreadsheet " + spreadSheetName);

    }

    @Override
    public void setWorkbook(String workbook) {
        this.workbook = workbook;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
