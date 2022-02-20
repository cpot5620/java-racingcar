package racingcar.dto;

import java.util.Collections;
import java.util.List;

public class WinnerNamesDto {
    private List<String> names;

    public WinnerNamesDto(List<String> names) {
        this.names = names;
    }

    public List<String> getWinnerNames() {
        return Collections.unmodifiableList(names);
    }
}