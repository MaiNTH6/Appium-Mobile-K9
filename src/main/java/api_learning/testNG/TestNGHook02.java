package api_learning.testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGHook02 extends BaseTestNG{

    //TestNG Hook

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + "Before Class");
    }

    @BeforeMethod()
    public void beforeMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "Before Method");
    }



    @Test
    public void testSth01() {
        System.out.println(this.getClass().getSimpleName() + "Test Method 01");
    }
    @Test
    public void testSth02() {
        System.out.println(this.getClass().getSimpleName() + "Test Method 02");
    }

    @AfterMethod()
    public void afterMethod(){
        System.out.println("\t\t\t\t--->" +this.getClass().getSimpleName() + "After Method");
    }
}
