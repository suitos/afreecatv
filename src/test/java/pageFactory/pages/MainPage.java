package test.java.pageFactory.pages;

import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.java.dto.ContentsRequestDto;
import test.java.dto.LoginResponseDto;
import test.java.extentReports.ExtentLogging;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static test.java.dto.enumType.SelectTypeEnum.ChoiceType.RANDOM;
import static test.java.dto.enumType.SelectTypeEnum.ContentsType.LIVE;

public class MainPage extends BasePage {

    @FindBy(id = "loginBtn")
    public WebElement loginBtn;

    @FindBy(id = "profileBtn")
    public WebElement profileBtn;

    @FindBy(xpath = "//div[@id='prefer_broadlist_area']//li")
    public List<WebElement> preferBroadList;

    @FindBy(xpath = "//div[@id='recommendCatchArea']//li")
    public List<WebElement> recommendCatchList;

    @FindBy(xpath = "//div[@id='recommendCatchArea']//button[@class='arrow-next slide_vod_arrow-next']")
    public WebElement recommendCatchSlideBtn;

    @FindBy(xpath = "//div[@id='prefer_vodlist_area']//li")
    public List<WebElement> preferVODList;

    @FindBy(xpath = "//div[@id='newHotArea']")
    public WebElement hotIssue;

    @FindBy(xpath = "//div[@id='newHotArea']//li")
    public List<WebElement> hotIssueList;

    @FindBy(xpath = "//li[.//div[@class='story_profile']]")
    public List<WebElement> catchStory_StoryList;

    @FindBy(xpath = "//div[@id='pbjCatchStoryList']//li[not(.//div[@class='story_profile'])]")
    public List<WebElement> catchStory_Non_StoryList;

    @FindBy(xpath = "//div[@class='slide-catch-story']//button[@class='arrow-next slide_vod_arrow-next']")
    public WebElement catchStorySlideBtn;

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

    private List<WebElement> getLiveContentsElementList(ContentsRequestDto contentsRequestDto) {
        switch (contentsRequestDto.getLiveAreaType()) {
            case PREFER_BROAD:
                return preferBroadList;
            case BROAD:
                return broadList;
            case HOT_ISSUE:
                return hotIssueList;
            default:
                throw new IllegalArgumentException("Invalid contents area type for LIVE type.");
        }
    }

    private List<WebElement> getContentsElementList(ContentsRequestDto contentsRequestDto) {
        switch (contentsRequestDto.getContentsType()) {
            case LIVE:
                return getLiveContentsElementList(contentsRequestDto);
            case VOD:
                return preferVODList;
            case CATCH:
                return recommendCatchList;
            case CATCH_STORY:
                switch (contentsRequestDto.getCatchStoryType()) {
                    case STORY:
                        return catchStory_StoryList;
                    case NON_STORY:
                        return catchStory_Non_StoryList;
                }
            default:
                throw new IllegalArgumentException("Invalid contents type.");
        }
    }

    private WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    private WebElement getRandomContentsElement(ContentsRequestDto contentsRequestDto) throws Exception {
        List<WebElement> broadElementList = getContentsElementList(contentsRequestDto);
        switch (contentsRequestDto.getContentsType()) {
            case LIVE:
            case VOD:
                try {
                    waitForVisibilityAll(broadElementList, 3);
                } catch (Exception e) {
                    Logging.logger.error(contentsRequestDto.getContentsType() + " 리스트가 없습니다!!");
                    ExtentLogging.fail(contentsRequestDto.getContentsType() + " 리스트가 없습니다!!");
                    throw new Exception(contentsRequestDto.getContentsType() + " 리스트가 없습니다!!");
                }
                break;
            case CATCH:
                scrollElementIntoMiddle(recommendCatchSlideBtn);
                waitForMoreElementsThan(By.xpath("//div[@id='recommendCatchArea']//li"), 1, 3);
                break;
            case CATCH_STORY:
                scrollElementIntoMiddle(hotIssue);
                waitForMoreElementsThan(By.xpath("//div[@id='pbjCatchStoryList']//li"), 1, 3);
                break;
        }

        return getRandomElement(broadElementList);
    }

    private String generateLogMessage(ContentsRequestDto contentsRequestDto) {
        return String.format("[ChoiceType] RANDOM [ContentsType] %s [닉네임] %s [방송 제목] %s",
                contentsRequestDto.getContentsType(), contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle());
    }

    private String generateCatchLogMessage(ContentsRequestDto contentsRequestDto) {
        switch (contentsRequestDto.getContentsType()) {
            case CATCH:
                return String.format("[ChoiceType] RANDOM [ContentsType] %s [닉네임] %s [캐치 제목] %s",
                        contentsRequestDto.getContentsType(), contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle());

            case CATCH_STORY:
                switch (contentsRequestDto.getCatchStoryType()) {
                    case STORY:
                        return String.format("[ChoiceType] RANDOM [ContentsType] %s [Type] %s [닉네임] %s [스토리 날짜] %s",
                                contentsRequestDto.getContentsType(), contentsRequestDto.getCatchStoryType(), contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsDate());
                    case NON_STORY:
                        return String.format("[ChoiceType] RANDOM [ContentsType] %s [Type] %s [닉네임] %s [캐치 제목] %s",
                                contentsRequestDto.getContentsType(), contentsRequestDto.getCatchStoryType(), contentsRequestDto.getBjNickName(), contentsRequestDto.getContentsTitle());

                }
            default:
                return "";
        }

    }

    public void clickContentsList(ContentsRequestDto contentsRequestDto) throws Exception {
        if (contentsRequestDto.getChoiceType() == RANDOM) {
            WebElement contents = getRandomContentsElement(contentsRequestDto);

            switch (contentsRequestDto.getContentsType()) {
                case LIVE:
                case VOD:
                    contentsRequestDto.setBjNickName(getContentsInfo(contentsRequestDto, contents).get("nickname"));
                    contentsRequestDto.setContentsTitle(getContentsInfo(contentsRequestDto, contents).get("title"));

                    String logMsg = generateLogMessage(contentsRequestDto);
                    logMsg += String.format(" [AreaType] %s", contentsRequestDto.getContentsType() == LIVE ? contentsRequestDto.getLiveAreaType().value : contentsRequestDto.getVodAreaType().value);

                    click(contents, logMsg);
                    break;
                case CATCH:
                case CATCH_STORY:
                    clickCatchTypeContents(contentsRequestDto, contents);
                    break;
            }


        }

        changeTab(1);

    }

    public void clickSlideBtn(ContentsRequestDto contentsRequestDto) throws Exception {
        switch (contentsRequestDto.getContentsType()) {
            case CATCH:
                sleep(1000);
                click(recommendCatchSlideBtn, "Catch 슬라이드 버튼");
                sleep(1000);

                break;
            case CATCH_STORY:
                sleep(1000);
                click(catchStorySlideBtn, "Catch & 스토리 슬라이드 버튼");
                sleep(1000);
                break;
        }
    }

    public void clickCatchTypeContents(ContentsRequestDto contentsRequestDto, WebElement contents) throws Exception {
        while (true) {
            if (isElementDisplayed(contents)) {
                try {
                    clickSlideBtn(contentsRequestDto);

                    HashMap<String, String> contentsInfo = getContentsInfo(contentsRequestDto, contents);
                    contentsRequestDto.setBjNickName(contentsInfo.get("nickname"));
                    contentsRequestDto.setContentsTitle(contentsInfo.get("title"));
                    contentsRequestDto.setContentsDate(contentsInfo.get("date"));

                    String logMsg = generateCatchLogMessage(contentsRequestDto);

                    click(contents, logMsg);

                    break;
                } catch (Exception ignored) {
                }
            } else {
                clickSlideBtn(contentsRequestDto);

            }

        }

    }

    public HashMap<String, String> getContentsInfo(ContentsRequestDto contentsRequestDto, WebElement contentsElement) throws Exception {
        String nickname = "";
        String title = "";
        String date = "";

        switch (contentsRequestDto.getContentsType()) {
            case LIVE:
            case VOD:
                nickname = getTextByXPath(contentsElement, ".//a[1]/span");
                title = getTextByXPath(contentsElement, ".//h3");
                break;
            case CATCH:
                nickname = getAttribute(contentsElement.findElement(By.xpath(".//a/div/button")), "data-user-nick");
                title = getTextByXPath(contentsElement, ".//a/div/p");

                break;
            case CATCH_STORY:
                switch (contentsRequestDto.getCatchStoryType()) {
                    case STORY:
                        nickname = getTextByXPath(contentsElement, ".//a/div/p[1]");
                        date = getTextByXPath(contentsElement, ".//a/div/p[2]");
                        break;
                    case NON_STORY:
                        nickname = getAttribute(contentsElement.findElement(By.xpath(".//a/div/button")), "data-user-nick");
                        title = getTextByXPath(contentsElement, ".//a/div/p[1]");

                        break;
                }
                break;
        }

        HashMap<String, String> contentsInfo = new HashMap<>();
        contentsInfo.put("nickname", nickname);
        contentsInfo.put("title", title);
        contentsInfo.put("date", date);

        return contentsInfo;
    }


}
