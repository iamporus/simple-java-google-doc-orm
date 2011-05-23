package bestever;

import java.util.List;

import spreadsheet.GoogleSpreadSheetLoader;
import spreadsheet.SpreadSheetLoader;
import util.RandomUtil;

public class BestEverService {

    private String user;

    private String password;

    private String key;

    private String fromName;

    public void sendMail() {

        // Set up loaders
        SpreadSheetLoader spreadSheetLoader = new GoogleSpreadSheetLoader(user, password, key);
        spreadSheetLoader.setWorkbook(BestEverProperties.SPREADSHEET_NAME.getValue());
        BestEverDataLoader loader = new BestEverDataLoader(spreadSheetLoader);

        // Get data
        List<EmailRecipient> emails = loader.getEmailRecipients();
        List<Story> stories = loader.getStories();
        Story story = RandomUtil.getRandomElement(stories);

        // Send mail
        MailSender mailSender = new MailSender(user, fromName);
        mailSender.setRecipients(emails);
        mailSender.sendMail(story);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

}
