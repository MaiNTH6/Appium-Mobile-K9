package api_learning.testNG;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGHook01 extends BaseTestNG {

    //TestNG Hook

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + "Before Class");
    }

    @BeforeMethod()
    public void beforeMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "Before Method");
    }


    @Test(priority = 1, dependsOnMethods = {"testSth02"})
    public void testSth01() {
        System.out.println(this.getClass().getSimpleName() + "Test Method 01");
    }
    @Test(priority = 2)
    public void testSth02() {
        throw  new RuntimeException("FAILED");
//        System.out.println(this.getClass().getSimpleName() + "Test Method 02");
    }

    @Test
    public void testSth03() {
        System.out.println("Method 03");
        String expectedResult = "a";
        String actualResult = "b";

        // Hard assertion
//        verifier.verifyEqual(actualResult, expectedResult);
//        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult, expectedResult,"ERR Loi roi");
        System.out.println("some thing else ...");
    }

    @Test
    public void testSth04() {
        System.out.println("Method 04");
        String expectedResult = "a";
        String actualResult = "b";

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(actualResult, expectedResult, "[ERR] Loi roi Teo oi");
//        softAssert.assertEquals("actualResult_", "expectedResult_", "[ERR] Loi roi Ti oi");

        softAssert.assertTrue(false);
        softAssert.assertFalse(true);
        softAssert.assertAll();
    }

    @AfterMethod()
    public void afterMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "After Method");
    }
}
