package tests.testNG;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class TestParameter {
    @Test
    @Parameters({"udid", "systemPort"})
    public void testParameter(String udid, String systemPort){ //Dat ten j cung duoc
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("Udid: %s | systemPort: %s\n", udid, systemPort);
    }
}
