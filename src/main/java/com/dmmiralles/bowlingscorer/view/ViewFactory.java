package com.dmmiralles.bowlingscorer.view;


import com.dmmiralles.bowlingscorer.model.FrameView;
import com.dmmiralles.bowlingscorer.model.GameView;
import com.dmmiralles.bowlingscorer.model.RollView;
import java.util.List;

/**
 * Created by dmmiralles on 11/5/2017.
 */
public interface ViewFactory {
    RollView createRollView(int score, boolean spare, boolean fault);
    RollView createStrike();
    FrameView createFrameView(RollView first, int order);
    FrameView createFrameView(RollView first, RollView second, int order);
    FrameView createFrameView(RollView first, RollView second, RollView extra);
    GameView createGameView(String playerName, List<FrameView> frames);
}
