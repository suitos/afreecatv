package test.java.pageFactory.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.LoginRequestDto;

import static test.java.dto.LoginRequestDto.LoginType.COMMON;

public class LoginPage extends BasePage {

    @FindBy(id = "uid")
    public WebElement id;

    @FindBy(id = "password")
    public WebElement pw;

    @FindBy(xpath = "//button[@onclick='login();']")
    public WebElement loginAuthBtn;

    public LoginPage() {
        super();
    }

    public void login(String id, String pw) throws Exception {
        sendKeys(this.id, id, "아이디 인풋");
        sendKeys(this.pw, pw, "패스워드 인풋");
        click(loginAuthBtn, "로그인 인증 버튼");

    }

    public void login(LoginRequestDto loginRequestDto) throws Exception {
        if (loginRequestDto.getLoginType() == COMMON) {
            sendKeys(id, loginRequestDto.getId(), "아이디 인풋");
            sendKeys(pw, loginRequestDto.getPw(), "패스워드 인풋");
            click(loginAuthBtn, "로그인 인증 버튼");
        }

    }
}
