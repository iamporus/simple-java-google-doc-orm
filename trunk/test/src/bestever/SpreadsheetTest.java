package bestever;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import spreadsheet.GoogleSpreadSheetLoader;

public class SpreadsheetTest {

    public static final String USER = "*******@gmail.com";

    public static final String PASSWORD = "*******";

    public static final String KEY = "*******";

    @Test
    public void testLoads() {

        GoogleSpreadSheetLoader spreadSheetLoader = new GoogleSpreadSheetLoader(USER, PASSWORD, KEY);

        spreadSheetLoader.setWorkbook(BestEverProperties.SPREADSHEET_NAME.getValue());

        BestEverDataLoader loader = new BestEverDataLoader(spreadSheetLoader);

        List<EmailRecipient> emails = loader.getEmailRecipients();

        Assert.assertTrue("Nothing returned", emails.size() > 0);

        for (EmailRecipient recipient : emails) {
            System.out.println(recipient);
        }

        List<Story> stories = loader.getStories();

        Assert.assertTrue("Nothing returned", stories.size() > 0);

        for (Story story : stories) {
            System.out.println(story);
        }

    }

}
