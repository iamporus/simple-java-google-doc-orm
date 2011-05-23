package bestever;

import org.junit.Test;

public class BestEverServiceTest {

    @Test
    public void testAll() {

        BestEverService service = new BestEverService();
        service.setKey(SpreadsheetTest.KEY);
        service.setPassword(SpreadsheetTest.PASSWORD);
        service.setUser(SpreadsheetTest.USER);
        service.setFromName("Best Ever");

        service.sendMail();

    }

}
