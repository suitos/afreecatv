package test.java.uitest.login;

import org.testng.annotations.Test;
import test.java.dto.*;
import test.java.pageFactory.pages.LivePage;
import test.java.pageFactory.pages.MainPage;
import test.java.uitest.pretest.BaseTest;

import static test.java.dto.BroadRequestDto.BroadChoiceType.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

public class LiveBroadTest extends BaseTest {

    BroadRequestDto broadRequestDto;
    BroadResponseDto broadResponseDto;

    @Test(priority = 2, groups = {"basic_test"}, description = "인기 LIVE 랜덤 진입")
    public void AUTO_LOGIN_002() throws Exception {

        broadRequestDto = BroadRequestDto
                .builder()
                .broadChoiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickBroadList(broadRequestDto);

        LivePage livePage = new LivePage();
        broadResponseDto = livePage.getBroadLiveOnPass(broadRequestDto.getBjNickName(), broadRequestDto.getBroadTitle(), true);

        assertThat(livePage.getLivePageBroadInfo("nickname")).isEqualTo(broadResponseDto.getResultBjNickName());
        assertThat(livePage.getLivePageBroadInfo("title")).isEqualTo(broadResponseDto.getResultBroadTitle());
        assertThat(livePage.isLivePlayerElementPresent()).isEqualTo(broadResponseDto.isResultVideoSrcAttributeBool());
    }


    /*
    @Test(priority = 2, groups = {"basic_test"}, description = "로그인 후 즐겨찾기 이동 테스트")
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

    @Test(priority = 3, enabled = false, groups = {"basic_test"}, dependsOnMethods = {"AUTO_LOGIN_002"}, description = "즐겨찾기 bj 확인")
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
    */
}
