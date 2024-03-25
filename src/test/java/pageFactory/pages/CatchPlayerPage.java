package test.java.pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.ContentsResponseDto;

import java.util.HashMap;

public class CatchPlayerPage extends BasePage {
    @FindBy(xpath = "//button[@class='clip_nick ictFunc']")
    public WebElement catchDataBjNickName;

    @FindBy(xpath = "//h3[@class='title']")
    public WebElement catchTitle;

    @FindBy(id = "video_p")
    public WebElement catchVideoPlayer;

    public CatchPlayerPage() {
        super();
    }

    public boolean isCatchPlayerElementPresent() {
        waitForGetAttributeToBeNotEmpty(catchVideoPlayer, "src", 3);
        return !catchVideoPlayer.getAttribute("src").isEmpty();
    }

    public String getCatchPageCatchInfo(String key) throws Exception {

        waitForGetAttributeToBeNotEmpty(catchVideoPlayer, "src", 3);

        String nickname = getText(catchDataBjNickName);
        String title = getText(catchTitle);

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);
        broadInfo.put("title", title);

        return broadInfo.get(key);
    }

    public ContentsResponseDto getCatchOnPass(String bjname, String broadtitle, boolean src) {
        return ContentsResponseDto.builder().resultBjNickName(bjname).resultBroadTitle(broadtitle).resultVideoSrcAttributeBool(src).build();
    }
}
