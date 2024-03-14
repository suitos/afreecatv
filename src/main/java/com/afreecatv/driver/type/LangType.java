package main.java.com.afreecatv.driver.type;

import lombok.Getter;

@Getter
public enum LangType {

    KR("ko-KR"),
    EN("EN"),
    JP("ja-JP"),
    TW("zh-TW"),
    CN("zh-CN");

    public final String value;

    LangType(String type) {
        this.value = type;
    }

}