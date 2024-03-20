package test.java.uitest.pretest;

import lombok.Getter;
import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.driver.manager.DriverManagerFactory;
import main.java.com.afreecatv.driver.type.DriverType;
import main.java.com.afreecatv.utils.PropertyReader;
import org.testng.annotations.*;
import test.java.dto.LoginRequestDto;
import test.java.dto.LoginResponseDto;
import test.java.pageFactory.pages.BasePage;
import test.java.pageFactory.pages.LoginPage;
import test.java.pageFactory.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;
import static test.java.dto.LoginRequestDto.LoginType.COMMON;

public class BaseTest {

    @Getter
    private String Browser;
    public static String TargetUrl;

    LoginRequestDto loginRequestDto;
    LoginResponseDto loginResponseDto;

    private void setBrowser(String browser) {
        if (browser == null) browser = DriverType.CHROME.name();
        browser = System.getProperty("browser", browser);

        Browser = browser;
    }

    private void setTargetUrl() {
        try {
            if (System.getProperty("url").contains("stable")) {
                TargetUrl = PropertyReader.getProperty("url");
            }
        } catch (NullPointerException npe) {
            TargetUrl = PropertyReader.getProperty("url");
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional String browser) {
        setTargetUrl();
        setBrowser(browser);
        browser = getBrowser();
        DriverManager.setDriver(new DriverManagerFactory().getManager(browser));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }

    @Test(alwaysRun = true, groups = {"non_login"}, description = "비로그인")
    public void nonLogin() {
        BasePage basePage = new BasePage();
        basePage.navigate(TargetUrl);
    }

    @Test(alwaysRun = true, groups = {"login"}, description = "로그인")
    public void login() throws Exception {
        BasePage basePage = new BasePage();
        basePage.navigate(TargetUrl);

        loginRequestDto = LoginRequestDto
                .builder()
                .loginType(COMMON)
                .id("suitos")
                .pw("dkssud12!?")
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickLoginBtn();
        LoginPage loginPage = new LoginPage();
        loginPage.login(loginRequestDto);

        //기대 결과에 충족하는 testData 정의
        loginResponseDto = mainPage.getLoginOnPass("서준석");

        assertThat(mainPage.getNickName()).isEqualTo(loginResponseDto.getResultProfileName());

    }
}
