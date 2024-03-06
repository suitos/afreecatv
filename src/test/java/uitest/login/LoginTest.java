package test.java.uitest.login;

import mmarquee.demo.Main;
import org.testng.annotations.Test;
import test.java.dto.LoginRequestDto;
import test.java.dto.LoginResponseDto;
import test.java.pageFactory.pages.BasePage;
import test.java.pageFactory.pages.LoginPage;
import test.java.pageFactory.pages.MainPage;
import test.java.uitest.BaseTest;

import static test.java.dto.LoginRequestDto.LoginType.COMMON;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    LoginRequestDto loginRequestDto;
    LoginResponseDto loginResponseDto;

    @Test(priority = 0, description = "아프리카tv 페이지 진입")
    public void openPage() {
        BasePage basePage = new BasePage();
        basePage.open("https://www.afreecatv.com");

    }

    @Test(priority = 1, dependsOnMethods = "openPage", description = "로그인 테스트")
    public void validLoginTest() throws Exception {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginBtn();

        /*
         * testData 정의
         * 필요한 testData는 보안을 위해 DB에서 불러온다.
         */
        loginRequestDto = LoginRequestDto
                .builder()
                .loginType(COMMON)
                .id("suitos")
                .pw("dkssud12!?")
                .build();

        //로그인 동작
        LoginPage loginPage = new LoginPage();
        loginPage.login(loginRequestDto);

        //기대 결과에 충족하는 testData 정의
        loginResponseDto = mainPage.getLoginOnPass("서준석");

        assertThat(mainPage.getNickName()).isEqualTo(loginResponseDto.getResultProfileName());
    }

}