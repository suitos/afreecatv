package test.java.dto.enumType;

public class SelectTypeEnum {
    public enum ChoiceType {
        RANDOM, NON_RANDOM
    }

    public enum ContentsType {
        LIVE, VOD, CATCH, CATCH_STORY
    }

    public enum CatchStoryType {
        STORY, NON_STORY
    }

    public enum LiveAreaType {
        PREFER_BROAD("선호 LIVE 방송"), BROAD("인기 LIVE 방송"), HOT_ISSUE("실시간 핫이슈");

        public final String value;

        LiveAreaType(String type) {
            this.value = type;
        }
    }

    public enum VodAreaType {
        PREFER_VOD_BROAD("선호 BJ VOD");

        public final String value;

        VodAreaType(String type) {
            this.value = type;
        }
    }

}
