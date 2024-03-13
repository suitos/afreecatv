package test.java.uitest.login;

import org.testng.annotations.Test;
import test.java.pageFactory.pages.BasePage;
import test.java.pageFactory.pages.LoginPage;
import test.java.pageFactory.pages.MainPage;
import test.java.uitest.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest2 extends BaseTest {

    @Test(priority = 0, description = "아프리카tv 페이지 진입")
    public void openPage() {
        BasePage basePage = new BasePage();
        basePage.navigate("https://www.afreecatv.com");

    }

    @Test(priority = 1, dependsOnMethods = "openPage", description = "로그인 테스트")
    public void validLoginTest() throws Exception {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginBtn();

        LoginPage loginPage = new LoginPage();
        loginPage.login("suitos", "dkssud12!?");

        assertThat(mainPage.getNickName()).isEqualTo("서준석");
    }

}
