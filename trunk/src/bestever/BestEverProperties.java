package bestever;

/**
 * Could configure this, but really shouldn't change.
 */
public enum BestEverProperties {
    EMAIL_WORKBOOK("Email"), ITEMS_WORKBOOK("Items"), SPREADSHEET_NAME("BestEver");

    private String value;

    BestEverProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
