package test.java.pageFactory.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.NavigateLnbRequestDto;
import test.java.pageFactory.pages.BasePage;

public class LNB extends BasePage {

    @FindBy(xpath = "//a[@class='home']")
    public WebElement homeMenuBtn;

    @FindBy(id = "myplus")
    public WebElement myPlusMenuBtn;

    @FindBy(id = "all")
    public WebElement allMenuBtn;

    @FindBy(id = "game")
    public WebElement gameMenuBtn;

    @FindBy(id = "bora")
    public WebElement boraMenuBtn;

    @FindBy(id = "sports")
    public WebElement sportsMenuBtn;

    @FindBy(xpath = "//a[@class='esports']")
    public WebElement eSportsMenuBtn;

    @FindBy(xpath = "//a[@class='vod']")
    public WebElement vodMenuBtn;

    @FindBy(xpath = "//a[@class='fav']")
    public WebElement favMenuBtn;

    @FindBy(xpath = "//a[@href='/favorite']")
    public WebElement favoriteMenuBtn;

    @FindBy(xpath = "//a[@href='/subscribe']")
    public WebElement subscribeMenuBtn;

    @FindBy(xpath = "//a[@href='/fanclub']")
    public WebElement fanclubMenuBtn;

    @FindBy(xpath = "//a[@href='/history']")
    public WebElement histroyMenuBtn;

    @FindBy(xpath = "//a[@href='/story']")
    public WebElement storyMenuBtn;

    public LNB() {
        super();
    }

    public void navigateLnbMenu(NavigateLnbRequestDto navigateLnbRequestDto) throws Exception {
        sleep(1000);
        
        switch (navigateLnbRequestDto.getLnbMenu()) {
            case HOME:
                click(homeMenuBtn, "홈 메뉴 버튼");

                switch (navigateLnbRequestDto.getHomeDetailMenu()) {
                    case MYPLUS:
                        click(myPlusMenuBtn, "MY+ 메뉴 버튼");
                        break;
                    case ALL:
                        click(allMenuBtn, "전체 메뉴 버튼");
                        break;
                    case GAME:
                        click(gameMenuBtn, "게임 메뉴 버튼");
                        break;
                    case BORA:
                        click(boraMenuBtn, "보이는라디오 메뉴 버튼");
                        break;
                    case SPORTS:
                        click(sportsMenuBtn, "스포츠 메뉴 버튼");
                        break;
                }
                break;

            case ESPROTS:
                click(eSportsMenuBtn, "e스포츠 메뉴 버튼");
                break;

            case VOD:
                click(vodMenuBtn, "VOD 메뉴 버튼");
                break;

            case FAV:
                click(favMenuBtn, "MY 버튼");

                try {
                    switch (navigateLnbRequestDto.getFavDetailMenu()) {
                        case FAVORITE:
                            click(favoriteMenuBtn, "즐겨찾기한 BJ 메뉴 버튼");
                            break;

                        case SUBSCRIBE:
                            click(subscribeMenuBtn, "구독한 BJ 메뉴 버튼");
                            break;

                        case FANCLUB:
                            click(fanclubMenuBtn, "팬가입한 BJ 메뉴 버튼");
                            break;

                        case HISTORY:
                            click(histroyMenuBtn, "기록 메뉴 버튼");
                            break;

                        case STORY:
                            click(storyMenuBtn, "내 글*댓글 메뉴 버튼");
                            break;

                    }
                } catch (NullPointerException ignored) {

                }
                break;
            default:
                break;

        }
    }

}
