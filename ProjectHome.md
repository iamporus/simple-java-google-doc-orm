# Google Spreadsheet to Java ORM #
This projects provides a simple implementation of how to load data from Google Spreadsheets into a Java object model.  It also comes with an example called bestever.  In the example, users enter stories in a password protected Google Spreadsheet.  The code can then log into this spreadsheet, select a random story, and email it to the users.  One implementation hosted on GAE uses this to send a childhood memory to a list of subscribed users.

# The API #

The package [spreadsheets](http://code.google.com/p/simple-java-google-doc-orm/source/browse/#svn%2Ftrunk%2Fsrc%2Fspreadsheet) has the Java interface and class used to log into Google Docs and extract the data.  The current version supports a simple interface where the calling code creates a GoogleSpreadSheetLoader object, sets the workbook to be loaded, and then calls a method to load the sheet into a List of desired objects.  Here is an example that gets a list of EmailRecipient objects from a spreadsheet:

```
        GoogleSpreadSheetLoader loader = new GoogleSpreadSheetLoader(USER, PASSWORD, KEY);

        loader.setWorkbook(BestEverProperties.SPREADSHEET_NAME.getValue());

        List<EmailRecipient> emails = loader.loadSheetAsList(
                                            BestEverProperties.EMAIL_WORKBOOK.getValue(), 
                                            EmailRecipient.class);

```

When mapping from the sheet to the object, it is assumed the first row contains headers and these are mapped via reflection to members of the class.  This is case sensitive, so the column headers should follow JavaBean standards.

# Limitations #

  * Only supports primitive types
  * Read only
  * No key support

# Future Development #

Adding an annotation library would allow this to become more ORM like.  Then, a map of objects could be returned based on key and updates would be possible.