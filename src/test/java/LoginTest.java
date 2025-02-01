import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    Random random = new Random();

    @Test
    void openSignupForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openSignupForm();
    }

    @Test
    @Tag("loginPositiveTest")
    void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsernameIntoField("user123");
        loginPage.enterPasswordIntoField("user123");
        loginPage.clickLoginButton();

        String expectedOutput = String.valueOf(loginPage.successfulLoginAssert("user123"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(Boolean.parseBoolean(expectedOutput), "User not logged in");
    }

    @Test
    @Tag("loginNegativeTest")
    void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        String username = "user" + random.nextInt(1000);

        loginPage.enterUsernameIntoField(username);
        loginPage.enterPasswordIntoField("user123");
        loginPage.clickLoginButton();

        String expectedOutput = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actualOutput = loginPage.getTextLoginErrorMessage();
        assertEquals(expectedOutput, actualOutput, "To login, user must be signed up");
    }
    @Test
    @Tag("loginNegativeTest")
    void loginFieldsEmpty(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsernameIntoField("");
        loginPage.enterPasswordIntoField("");
        loginPage.clickLoginButton();

        String expectedOutput = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actualOutput = loginPage.getTextLoginErrorMessage();
        assertEquals(expectedOutput, actualOutput, "To login, all fields must be filled up");
    }
}
