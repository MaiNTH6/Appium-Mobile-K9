package api_learning.testNG;

public class verifier {
    public static void verifyEqual(String actualResult, String expectedResult){
      if(!expectedResult.equals(actualResult))
            throw new AssertionError("Expected value and actual value is diff!");
}
}
