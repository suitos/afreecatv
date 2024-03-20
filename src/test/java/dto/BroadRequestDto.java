package test.java.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BroadRequestDto {
    private BroadChoiceType broadChoiceType;
    private String bjNickName;
    private String broadTitle;

    public enum BroadChoiceType {
        RANDOM, NON_RANDOM
    }

    public BroadRequestDto(BroadChoiceType broadChoiceType, String bjNickName, String broadTitle) {
        this.broadChoiceType = broadChoiceType;
        this.bjNickName = bjNickName;
        this.broadTitle = broadTitle;
    }
}
