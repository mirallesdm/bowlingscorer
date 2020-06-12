package com.dmmiralles.bowlingscorer.view.impl;

import com.dmmiralles.bowlingscorer.model.FrameView;
import com.dmmiralles.bowlingscorer.model.GameView;
import com.dmmiralles.bowlingscorer.model.RollView;
import com.dmmiralles.bowlingscorer.util.Constants;
import com.dmmiralles.bowlingscorer.view.ViewFactory;
import java.util.List;

public class ViewFactoryImpl implements ViewFactory {

    @Override
    public RollView createStrike() {
        return new RollView(Constants.MAX_PINS, false, false);
    }

    @Override
    public RollView createRollView(int score, boolean spare, boolean fault) {
        return new RollView(score, fault, spare);
    }

    @Override
    public FrameView createFrameView(RollView first, int order) {
        FrameView frameView = new FrameView();
        frameView.setFirst(first);
        frameView.setOrder(order);
        frameView.setScore(0);
        frameView.setAccumulatedScore(0);
        return frameView;
    }

    @Override
    public FrameView createFrameView(RollView first, RollView second, int order) {
        FrameView frameView = new FrameView();
        frameView.setFirst(first);
        frameView.setSecond(second);
        frameView.setOrder(order);
        frameView.setScore(0);
        frameView.setAccumulatedScore(0);
        return frameView;
    }

    @Override
    public FrameView createFrameView(RollView first, RollView second, RollView extra) {
        FrameView frameView = new FrameView();
        frameView.setFirst(first);
        frameView.setSecond(second);
        frameView.setExtra(extra);
        frameView.setOrder(Constants.MAX_FRAMES);
        frameView.setScore(0);
        frameView.setAccumulatedScore(0);
        return frameView;
    }

    @Override
    public GameView createGameView(String playerName, List<FrameView> frames) {
        return new GameView(playerName, frames);
    }
}
