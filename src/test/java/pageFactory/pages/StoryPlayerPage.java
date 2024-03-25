package test.java.pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.ContentsResponseDto;

import java.util.HashMap;

public class StoryPlayerPage extends BasePage {
    @FindBy(xpath = "//div[@class='story_player_guide']")
    public WebElement storyPlayerGuide;

    @FindBy(xpath = "//div[@class='story_player_guide']//button")
    public WebElement storyPlayerGuideCloseBtn;

    @FindBy(id = "video_p")
    public WebElement storyPlayer;

    @FindBy(xpath = "//p[@class='nick_wrap']/a[1]")
    public WebElement storyBjNickName;

    public StoryPlayerPage() {
        super();
    }

    public void closeStoryPlayerGuide() {
        try {
            click(storyPlayerGuideCloseBtn, "스토리 플레이어 가이드 닫기 버튼");
        } catch (Exception ignored) {

        }
    }

    public boolean isCatchPlayerElementPresent() {
        waitForGetAttributeToBeNotEmpty(storyPlayer, "src", 3);
        return !storyPlayer.getAttribute("src").isEmpty();
    }

    public String getCatchPageCatchInfo(String key) throws Exception {
        waitForGetAttributeToBeNotEmpty(storyPlayer, "src", 3);

        String nickname = getText(storyBjNickName);

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);

        return broadInfo.get(key);
    }

    public ContentsResponseDto getCatchOnPass(String bjname, boolean src) {
        closeStoryPlayerGuide();

        return ContentsResponseDto.builder().resultBjNickName(bjname).resultVideoSrcAttributeBool(src).build();
    }
}
