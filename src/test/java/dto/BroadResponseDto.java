package test.java.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BroadResponseDto {
    private String resultBjNickName;
    private String resultBroadTitle;
    private boolean resultVideoSrcAttributeBool;

}
