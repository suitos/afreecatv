package test.java.uitest.login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import test.java.dto.ContentsRequestDto;
import test.java.dto.ContentsResponseDto;
import test.java.pageFactory.pages.*;
import test.java.uitest.pretest.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;
import static test.java.dto.enumType.SelectTypeEnum.CatchStoryType.NON_STORY;
import static test.java.dto.enumType.SelectTypeEnum.CatchStoryType.STORY;
import static test.java.dto.enumType.SelectTypeEnum.ChoiceType.RANDOM;
import static test.java.dto.enumType.SelectTypeEnum.ContentsType.*;
import static test.java.dto.enumType.SelectTypeEnum.LiveAreaType.BROAD;
import static test.java.dto.enumType.SelectTypeEnum.VodAreaType.PREFER_VOD_BROAD;

public class LiveBroadTest extends BaseTest {

    ContentsRequestDto contentsRequestDto;
    ContentsResponseDto contentsResponseDto;

    @AfterMethod(onlyForGroups = {"basic_test"}, groups = {"basic_test"})
    private void closeTab() throws Exception {
        BasePage basePage = new BasePage();
        if (basePage.getTabSize() == 2) {
            basePage.changeAndCloseTab(0);
        }
        basePage.sleep(2000);


    }

    @Test(priority = 2, enabled = false, groups = {"basic_test"}, description = "인기 LIVE 랜덤 진입")
    public void AUTO_LOGIN_002() throws Exception {

        contentsRequestDto = ContentsRequestDto
                .builder()
                .contentsType(LIVE)
                .liveAreaType(BROAD)
                .choiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickContentsList(contentsRequestDto);

        LivePlayerPage livePlayerPage = new LivePlayerPage();
        contentsResponseDto = livePlayerPage.getBroadLiveOnPass(contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle(), true);

        assertThat(livePlayerPage.getLivePageBroadInfo("nickname")).isEqualTo(contentsResponseDto.getResultBjNickName());
        assertThat(livePlayerPage.getLivePageBroadInfo("title")).isEqualTo(contentsResponseDto.getResultBroadTitle());
        assertThat(livePlayerPage.isLivePlayerElementPresent()).isEqualTo(contentsResponseDto.isResultVideoSrcAttributeBool());
    }

    @Test(priority = 3, enabled = false, groups = {"basic_test"}, description = "선호 BJ VOD 랜덤 진입")
    public void AUTO_LOGIN_003() throws Exception {

        contentsRequestDto = ContentsRequestDto
                .builder()
                .contentsType(VOD)
                .vodAreaType(PREFER_VOD_BROAD)
                .choiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickContentsList(contentsRequestDto);

        VodPlayerPage vodPlayerPage = new VodPlayerPage();
        contentsResponseDto = vodPlayerPage.getBroadVodOnPass(contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle(), true);

        assertThat(vodPlayerPage.getVodPageBroadInfo("nickname")).isEqualTo(contentsResponseDto.getResultBjNickName());
        assertThat(vodPlayerPage.getVodPageBroadInfo("title")).isEqualTo(contentsResponseDto.getResultBroadTitle());
        assertThat(vodPlayerPage.isVodPlayerElementPresent()).isEqualTo(contentsResponseDto.isResultVideoSrcAttributeBool());

    }

    @Test(priority = 4, groups = {"basic_test"}, description = "CATCH 랜덤 진입")
    public void AUTO_LOGIN_004() throws Exception {

        contentsRequestDto = ContentsRequestDto
                .builder()
                .contentsType(CATCH)
                .choiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickContentsList(contentsRequestDto);

        CatchPlayerPage catchPlayerPage = new CatchPlayerPage();
        contentsResponseDto = catchPlayerPage.getCatchOnPass(contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle(), true);

        assertThat(catchPlayerPage.getCatchPageCatchInfo("nickname")).isEqualTo(contentsResponseDto.getResultBjNickName());
        assertThat(catchPlayerPage.getCatchPageCatchInfo("title")).isEqualTo(contentsResponseDto.getResultBroadTitle());
        assertThat(catchPlayerPage.isCatchPlayerElementPresent()).isEqualTo(contentsResponseDto.isResultVideoSrcAttributeBool());

    }

    @Test(priority = 5, groups = {"basic_test"}, description = "CATCH&STORY 내 스토리 랜덤 진입")
    public void AUTO_LOGIN_005() throws Exception {

        contentsRequestDto = ContentsRequestDto
                .builder()
                .contentsType(CATCH_STORY)
                .catchStoryType(STORY)
                .choiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickContentsList(contentsRequestDto);

        StoryPlayerPage storyPlayerPage = new StoryPlayerPage();
        contentsResponseDto = storyPlayerPage.getCatchOnPass(contentsRequestDto.getBjNickName(), true);

        assertThat(storyPlayerPage.getCatchPageCatchInfo("nickname")).isEqualTo(contentsResponseDto.getResultBjNickName());
        assertThat(storyPlayerPage.isCatchPlayerElementPresent()).isEqualTo(contentsResponseDto.isResultVideoSrcAttributeBool());

    }

    @Test(priority = 6, groups = {"basic_test"}, description = "CATCH&STORY 내 캐치 랜덤 진입")
    public void AUTO_LOGIN_006() throws Exception {

        contentsRequestDto = ContentsRequestDto
                .builder()
                .contentsType(CATCH_STORY)
                .catchStoryType(NON_STORY)
                .choiceType(RANDOM)
                .build();

        MainPage mainPage = new MainPage();
        mainPage.clickContentsList(contentsRequestDto);

        CatchPlayerPage catchPlayerPage = new CatchPlayerPage();
        contentsResponseDto = catchPlayerPage.getCatchOnPass(contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle(), true);

        assertThat(catchPlayerPage.getCatchPageCatchInfo("nickname")).isEqualTo(contentsResponseDto.getResultBjNickName());
        assertThat(catchPlayerPage.getCatchPageCatchInfo("title")).isEqualTo(contentsResponseDto.getResultBroadTitle());
        assertThat(catchPlayerPage.isCatchPlayerElementPresent()).isEqualTo(contentsResponseDto.isResultVideoSrcAttributeBool());

    }

}
