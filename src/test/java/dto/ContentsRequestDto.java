package test.java.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import test.java.dto.enumType.SelectTypeEnum;

@Setter
@Getter
@Builder
public class ContentsRequestDto {
    private SelectTypeEnum.ContentsType contentsType;
    private SelectTypeEnum.CatchStoryType catchStoryType;
    private SelectTypeEnum.LiveAreaType liveAreaType;
    private SelectTypeEnum.VodAreaType vodAreaType;
    private SelectTypeEnum.ChoiceType choiceType;
    private String bjNickName;
    private String contentsTitle;
    private String contentsDate;

    public ContentsRequestDto(SelectTypeEnum.ContentsType contentsType, SelectTypeEnum.CatchStoryType catchStoryType, SelectTypeEnum.LiveAreaType liveAreaType, SelectTypeEnum.VodAreaType vodAreaType, SelectTypeEnum.ChoiceType choiceType, String bjNickName, String contentsTitle, String contentsDate) {
        this.contentsType = contentsType;
        this.catchStoryType = catchStoryType;
        this.liveAreaType = liveAreaType;
        this.vodAreaType = vodAreaType;
        this.choiceType = choiceType;
        this.bjNickName = bjNickName;
        this.contentsTitle = contentsTitle;
        this.contentsDate = contentsDate;
    }
}
