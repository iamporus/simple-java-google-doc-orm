package spreadsheet;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import bestever.EmailRecipient;
import bestever.BestEverProperties;
import bestever.Story;

public class GoogleSpreadsheetLoaderTest {

    public static final String USER = "*******@gmail.com";

    public static final String PASSWORD = "*****";

    public static final String KEY = "*****";

    @Test
    public void testEmailLoad() {

        GoogleSpreadSheetLoader loader = new GoogleSpreadSheetLoader(USER, PASSWORD, KEY);

        loader.setWorkbook(BestEverProperties.SPREADSHEET_NAME.getValue());

        List<EmailRecipient> emails = loader.loadSheetAsList(BestEverProperties.EMAIL_WORKBOOK.getValue(), EmailRecipient.class);

        Assert.assertTrue("Nothing returned", emails.size() > 0);
        
        for(EmailRecipient recipient : emails){
           System.out.println(recipient);
        }

        List<Story> stories = loader.loadSheetAsList(BestEverProperties.ITEMS_WORKBOOK.getValue(), Story.class);

        Assert.assertTrue("Nothing returned", stories.size() > 0);
        
        for(Story story : stories){
           System.out.println(story);
        }
        
    }

}
