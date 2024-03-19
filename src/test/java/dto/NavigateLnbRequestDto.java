package test.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class NavigateLnbRequestDto {
    private LnbMenu lnbMenu;
    private HomeDetailMenu homeDetailMenu;
    private FavDetailMenu favDetailMenu;

    public enum LnbMenu {
        HOME, ESPROTS, VOD, FAV
    }

    public enum HomeDetailMenu {
        MYPLUS, ALL, GAME, BORA, SPORTS
    }

    public enum FavDetailMenu {
        FAVORITE, SUBSCRIBE, FANCLUB, HISTORY, STORY
    }

    public NavigateLnbRequestDto(LnbMenu lnbMenu) {
        this.lnbMenu = lnbMenu;
    }

    public NavigateLnbRequestDto(HomeDetailMenu homeDetailMenu) {
        this.homeDetailMenu = homeDetailMenu;
    }

    public NavigateLnbRequestDto(FavDetailMenu favDetailMenu) {
        this.favDetailMenu = favDetailMenu;
    }

}
