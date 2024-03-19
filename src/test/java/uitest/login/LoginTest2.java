package test.java.uitest.login;

import org.testng.annotations.Test;
import test.java.pageFactory.pages.LoginPage;
import test.java.pageFactory.pages.MainPage;
import test.java.uitest.pretest.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest2 extends BaseTest {

    @Test(priority = 1, groups = {"basic_test"}, description = "로그인 테스트")
    public void validLoginTest() throws Exception {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginBtn();

        LoginPage loginPage = new LoginPage();
        loginPage.login("suitos", "dkssud12!?");

        assertThat(mainPage.getNickName()).isEqualTo("서준석");
    }

}
