package test.java.pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.LoginResponseDto;

public class MainPage extends BasePage {

    @FindBy(id = "loginBtn")
    public WebElement loginBtn;

    @FindBy(id = "profileBtn")
    public WebElement profileBtn;

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


}
