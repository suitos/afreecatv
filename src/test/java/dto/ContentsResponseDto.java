package test.java.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentsResponseDto {
    private String resultBjNickName;
    private String resultBroadTitle;
    private boolean resultVideoSrcAttributeBool;

}
