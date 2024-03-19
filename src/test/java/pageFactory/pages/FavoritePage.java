package test.java.pageFactory.pages;

import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.NavigateLnbResponseDto;
import test.java.dto.enumType.LiveStatusEnum;

import java.util.ArrayList;
import java.util.List;

import static test.java.dto.enumType.LiveStatusEnum.LiveStatus.LIVE_OFF;
import static test.java.dto.enumType.LiveStatusEnum.LiveStatus.LIVE_ON;

public class FavoritePage extends BasePage {

    @FindBy(xpath = "//h2[@class='big']")
    public WebElement bigTitle;

    @FindBy(xpath = "//div[@class='favor-list']//li")
    public List<WebElement> favoriteBjListElement;

    public FavoritePage() {
        super();
    }

    public NavigateLnbResponseDto getNavigateFavOnPass(String bigTitle) {
        return NavigateLnbResponseDto.builder().resultBigTitle(bigTitle).build();
    }

    public String getBigTitle() throws Exception {
        return getText(bigTitle);
    }

    public ArrayList<Pair<String, LiveStatusEnum.LiveStatus>> getFavoriteBjList() throws Exception {
        ArrayList<Pair<String, LiveStatusEnum.LiveStatus>> favoriteBjArrayList = new ArrayList<>();

        for (WebElement favoriteBjElement : favoriteBjListElement) {
            String bjNickName = getText(favoriteBjElement.findElement(By.xpath(".//div[@class='nick']/strong")));
            LiveStatusEnum.LiveStatus status = !favoriteBjElement.findElements(By.xpath(".//div[@class='btns']")).isEmpty() ?
                    LIVE_ON : LIVE_OFF;

            favoriteBjArrayList.add(new Pair<>(bjNickName, status));
        }
        return favoriteBjArrayList;
    }
}
