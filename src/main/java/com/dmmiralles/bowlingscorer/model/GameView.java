package com.dmmiralles.bowlingscorer.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dmmiralles on 11/5/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class GameView {
    private String playerName;
    private List<FrameView> frames;
}
