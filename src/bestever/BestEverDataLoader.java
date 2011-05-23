package bestever;

import java.util.List;

import spreadsheet.SpreadSheetLoader;

public class BestEverDataLoader {

    private SpreadSheetLoader loader;
    
    public BestEverDataLoader(SpreadSheetLoader loader){
        this.loader = loader;
    }
    
    public List<Story> getStories(){
       return loader.loadSheetAsList(BestEverProperties.ITEMS_WORKBOOK.getValue(), Story.class);
    }
    
    public List<EmailRecipient> getEmailRecipients(){
        return loader.loadSheetAsList(BestEverProperties.EMAIL_WORKBOOK.getValue(), EmailRecipient.class);
    }
    
}
