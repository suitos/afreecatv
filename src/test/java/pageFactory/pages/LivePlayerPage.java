package test.java.pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.ContentsResponseDto;

import java.util.HashMap;

public class LivePlayerPage extends BasePage {

    @FindBy(id = "livePlayer")
    public WebElement livePlayer;

    @FindBy(xpath = "//div[@class='nickname']")
    public WebElement liveBjNickName;

    @FindBy(xpath = "//div[@class='broadcast_title']/span[@title]")
    public WebElement liveBroadTitle;

    public LivePlayerPage() {
        super();
    }

    public boolean isLivePlayerElementPresent() {
        waitForGetAttributeToBeNotEmpty(livePlayer, "src", 3);
        return !livePlayer.getAttribute("src").isEmpty();
    }

    public String getLivePageBroadInfo(String key) throws Exception {
        waitForGetAttributeToBeNotEmpty(livePlayer, "src", 5);

        String nickname = getText(liveBjNickName);
        String title = getText(liveBroadTitle);

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);
        broadInfo.put("title", title);

        return broadInfo.get(key);
    }

    public ContentsResponseDto getBroadLiveOnPass(String bjname, String broadtitle, boolean src) throws Exception {
        return ContentsResponseDto.builder().resultBjNickName(bjname).resultBroadTitle(broadtitle).resultVideoSrcAttributeBool(src).build();
    }
}
