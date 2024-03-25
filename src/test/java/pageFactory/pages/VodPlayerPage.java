package test.java.pageFactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.ContentsResponseDto;

import java.util.HashMap;

public class VodPlayerPage extends BasePage {

    public By videoPlayerBy = By.id("video");
    public By adVideoPlayerBy = By.id("adVideo");

    @FindBy(id = "video")
    public WebElement videoPlayer;

    @FindBy(id = "adVideo")
    public WebElement adVideoPlayer;

    @FindBy(xpath = "//button[@class='ictFunc']")
    public WebElement vodBjNickName;

    @FindBy(xpath = "//div[@class='broadcast_title']")
    public WebElement vodBroadTitle;

    public VodPlayerPage() {
        super();
    }

    public boolean isVodPlayerElementPresent() throws Exception {
        boolean isPlayerPresent;
        if (getAttribute(adVideoPlayerBy, "style").contains("none")) {
            waitForGetAttributeToBeNotEmpty(videoPlayer, "src", 3);
            isPlayerPresent = !videoPlayer.getAttribute("src").isEmpty();
        } else {
            waitForGetAttributeToBeNotEmpty(adVideoPlayer, "src", 3);
            isPlayerPresent = !adVideoPlayer.getAttribute("src").isEmpty();

        }

        return isPlayerPresent;
    }

    public String getVodPageBroadInfo(String key) throws Exception {

        if (getAttribute(adVideoPlayerBy, "style").contains("none")) {
            waitForGetAttributeToBeNotEmpty(videoPlayer, "src", 3);
        } else {
            waitForGetAttributeToBeNotEmpty(adVideoPlayer, "src", 3);
        }

        String nickname = getText(vodBjNickName);
        String title = getText(vodBroadTitle);

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);
        broadInfo.put("title", title);

        return broadInfo.get(key);
    }

    public ContentsResponseDto getBroadVodOnPass(String bjname, String broadtitle, boolean src) {
        return ContentsResponseDto.builder().resultBjNickName(bjname).resultBroadTitle(broadtitle).resultVideoSrcAttributeBool(src).build();
    }
}
