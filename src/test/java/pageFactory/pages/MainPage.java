package test.java.pageFactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.BroadRequestDto;
import test.java.dto.LoginResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static test.java.dto.BroadRequestDto.BroadChoiceType.RANDOM;

public class MainPage extends BasePage {

    @FindBy(id = "loginBtn")
    public WebElement loginBtn;

    @FindBy(id = "profileBtn")
    public WebElement profileBtn;

    @FindBy(xpath = "//div[@id='broadlist_area']//li")
    public List<WebElement> broadList;


    public MainPage() {
        super();
    }

    public void clickLoginBtn() throws Exception {
        click(loginBtn, "로그인 버튼");
    }

    public String getNickName() throws Exception {
        return getText(profileBtn);
    }

    public LoginResponseDto getLoginOnPass(String nickName) {
        return LoginResponseDto.builder().resultProfileName(nickName).build();
    }

    private WebElement getRandomBroad() {
        waitForVisibilityAll(broadList, 3);

        Random random = new Random();
        int randomIndex = random.nextInt(broadList.size());
        WebElement randomBroad = broadList.get(randomIndex);

        return randomBroad;
    }

    public HashMap<String, String> getBroadInfo(WebElement broadElement) {
        String nickname = broadElement.findElement(By.xpath(".//a[1]/span")).getText();
        String title = broadElement.findElement(By.xpath(".//h3")).getText();

        HashMap<String, String> broadInfo = new HashMap<>();
        broadInfo.put("nickname", nickname);
        broadInfo.put("title", title);

        return broadInfo;
    }

    public void clickBroadList(BroadRequestDto broadRequestDto) throws Exception {
        WebElement broad;
        HashMap<String, String> broadInfo;

        if (broadRequestDto.getBroadChoiceType() == RANDOM) {
            broad = getRandomBroad();
            broadInfo = getBroadInfo(broad);

            broadRequestDto.setBjNickName(broadInfo.get("nickname"));
            broadRequestDto.setBroadTitle(broadInfo.get("title"));

            click(broad, "인기 LIVE 방송 [ChoiceType] RANDOM [닉네임] " + broadRequestDto.getBjNickName() + " [방송 제목] " + broadRequestDto.getBroadTitle());
        }

        changeTab(1);

    }

}
