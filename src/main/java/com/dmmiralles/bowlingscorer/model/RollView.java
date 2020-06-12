package com.dmmiralles.bowlingscorer.model;

import com.dmmiralles.bowlingscorer.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dmmiralles on 11/5/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class RollView {

    private int score;
    private boolean fault;
    private boolean spare;

    public boolean isStrike() {
        return score == Constants.MAX_PINS;
    }

    public String getValue() {
        if (isStrike())
            return Constants.OUT_STRIKE;
        if (spare)
            return Constants.OUT_SPARE;
        if (fault)
            return Constants.OUT_FAULT;

        return String.valueOf(score);
    }
}
