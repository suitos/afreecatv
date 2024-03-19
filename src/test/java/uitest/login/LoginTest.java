package test.java.uitest.login;

import javafx.util.Pair;
import org.testng.annotations.Test;
import test.java.dto.LoginRequestDto;
import test.java.dto.LoginResponseDto;
import test.java.dto.NavigateLnbRequestDto;
import test.java.dto.NavigateLnbResponseDto;
import test.java.dto.enumType.LiveStatusEnum;
import test.java.pageFactory.component.LNB;
import test.java.pageFactory.pages.FavoritePage;
import test.java.pageFactory.pages.LoginPage;
import test.java.pageFactory.pages.MainPage;
import test.java.uitest.pretest.BaseTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static test.java.dto.LoginRequestDto.LoginType.COMMON;
import static test.java.dto.NavigateLnbRequestDto.LnbMenu.FAV;

public class LoginTest extends BaseTest {

    LoginRequestDto loginRequestDto;
    LoginResponseDto loginResponseDto;
    NavigateLnbRequestDto navigateLnbRequestDto;
    NavigateLnbResponseDto navigateLnbResponseDto;

    @Test(priority = 1, groups = {"basic_test"}, description = "로그인 테스트")
    public void AUTO_LOGIN_001() throws Exception {

        /*
         * testData 정의
         * 필요한 testData는 보안을 위해 DB에서 불러온다.
         * 로그인 타입은 COMMON, SNS
         */
        loginRequestDto = LoginRequestDto
                .builder()
                .loginType(COMMON)
                .id("suitos")
                .pw("dkssud12!?")
                .build();

        //로그인 동작
        MainPage mainPage = new MainPage();
        mainPage.clickLoginBtn();
        LoginPage loginPage = new LoginPage();
        loginPage.login(loginRequestDto);

        //기대 결과에 충족하는 testData 정의
        loginResponseDto = mainPage.getLoginOnPass("서준석");

        assertThat(mainPage.getNickName()).isEqualTo(loginResponseDto.getResultProfileName());
    }

    @Test(priority = 2, enabled = true, groups = {"basic_test"}, dependsOnMethods = {"AUTO_LOGIN_001"}, description = "로그인 후 즐겨찾기 이동 테스트")
    public void AUTO_LOGIN_002() throws Exception {

        navigateLnbRequestDto = NavigateLnbRequestDto
                .builder()
                .lnbMenu(FAV)
                .build();

        LNB lnb = new LNB();
        lnb.navigateLnbMenu(navigateLnbRequestDto);

        FavoritePage favoritePage = new FavoritePage();
        navigateLnbResponseDto = favoritePage.getNavigateFavOnPass("즐겨찾기한 BJ");

        assertThat(favoritePage.getBigTitle()).isEqualTo(navigateLnbResponseDto.getResultBigTitle());

    }

    @Test(priority = 3, enabled = false, groups = {"basic_test"}, dependsOnMethods = {"AUTO_LOGIN_001", "AUTO_LOGIN_002"}, description = "즐겨찾기 bj 확인")
    public void AUTO_LOGIN_003() throws Exception {
        FavoritePage favoritePage = new FavoritePage();

        ArrayList<Pair<String, LiveStatusEnum.LiveStatus>> favoriteBjList = favoritePage.getFavoriteBjList();

        for (int i = 0; i < favoriteBjList.size(); i++) {
            Pair<String, LiveStatusEnum.LiveStatus> pair = favoriteBjList.get(i);
            String bjNickName = pair.getKey();
            LiveStatusEnum.LiveStatus status = pair.getValue();
            System.out.println("Index: " + i + ", Bj Nickname: " + bjNickName + ", Status: " + status);
        }

    }
}
