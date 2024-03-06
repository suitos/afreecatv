package test.java.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginRequestDto {
    private LoginType loginType;
    private String id;
    private String pw;

    @Getter
    public enum LoginType {
        COMMON, SNS
    }

    public LoginRequestDto(LoginType loginType, String id, String pw) {
        this.loginType = loginType;
        this.id = id;
        this.pw = pw;
    }
}
