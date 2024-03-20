package test.java.pageFactory.pages;

import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.BroadResponseDto;

import java.util.HashMap;

public class LivePage extends BasePage {

    @FindBy(id = "livePlayer")
    public WebElement livePlayer;

    @FindBy(xpath = "//div[@class='nickname']")
    public WebElement liveBjNickName;

    @FindBy(xpath = "//div[@class='broadcast_title']/span[@title]")
    public WebElement liveBroadTitle;

    public LivePage() {
        super();
    }

    public boolean isLivePlayerElementPresent() {
        waitForGetAttribute(livePlayer, "src", 3);
        return !livePlayer.getAttribute("src").isEmpty();
    }

    public String getLivePageBroadInfo(String key) throws Exception {
        waitForGetAttribute(livePlayer, "src", 3);

        String nickname = getText(liveBjNickName);
        String title = getText(liveBroadTitle);

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);
        broadInfo.put("title", title);

        return broadInfo.get(key);
    }

    public BroadResponseDto getBroadLiveOnPass(String bjname, String broadtitle, boolean src) throws Exception {
        return BroadResponseDto.builder().resultBjNickName(bjname).resultBroadTitle(broadtitle).resultVideoSrcAttributeBool(src).build();
    }
}
