package com.dmmiralles.bowlingscorer.model;

import com.dmmiralles.bowlingscorer.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by dmmiralles on 11/5/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrameView {

    private int order;
    private RollView first;
    private RollView second;
    private RollView extra;
    private int score;
    private int accumulatedScore;

    public String toString() {
        if (order == Constants.MAX_FRAMES && extra != null){
            return first.getValue() + Constants.SPACE_3 + second.getValue() + Constants.SPACE_3+ extra.getValue();
        } else if (first != null && second != null){
            return this.first.getValue() + Constants.SPACE_3 + this.second.getValue();
        } else if (first != null){
            return Constants.SPACE_4 + this.first.getValue();
        }

        return "";
    }
}
