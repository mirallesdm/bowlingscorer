package com.dmmiralles.bowlingscorer.service.impl;

import com.dmmiralles.bowlingscorer.model.FrameView;
import com.dmmiralles.bowlingscorer.model.GameView;
import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.model.RollView;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import com.dmmiralles.bowlingscorer.service.BowlingJudgeService;
import com.dmmiralles.bowlingscorer.util.Constants;
import com.dmmiralles.bowlingscorer.view.ViewFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Concrete implementation of BowlingJudgeService.
 *
 * @author Daniel Miralles
 */
@AllArgsConstructor
public class BowlingJugdeServiceImpl implements BowlingJudgeService {

    private final RollRepository repository;
    private final ViewFactory factory;


    protected boolean isStrike(int index, List<Roll> rolls){
        return rolls.get(index).getPins() == Constants.MAX_PINS;
    }

    protected int rollsScore(int index, List<Roll> rolls){
        return rolls.get(index).getPins() + rolls.get(index + 1).getPins();
    }

    protected boolean isSpare(int index, List<Roll> rolls){
        return rollsScore(index, rolls) == Constants.MAX_PINS;
    }

    protected int bonusByStrike(int index, List<Roll> rolls){
        return rollsScore(index + 1, rolls);
    }

    protected int bonusBySpare(int index, List<Roll> rolls){
        return rolls.get(index + 2).getPins();
    }

    /**
     * Principal method in application
     *
     * @param playerName Name of player.
     * @return The GameView for a player. In few word the player point board
     */
    @Override
    public GameView processRolls(String playerName) {

        List<Roll> playerRolls = repository.getByPlayerName(playerName);
        List<FrameView> frameViews = new ArrayList<>();

        int frameIndex = 0;
        int accumulatedScore = 0;

        //Iterating frames

        for (int i = 0; i < Constants.MAX_FRAMES; i++) {
            int score = 0;
            RollView first;
            RollView second = null;
            RollView extra = null;
            FrameView actualFrame;

            if (isStrike(frameIndex, playerRolls)) { //Checking if strike.
                score = Constants.MAX_PINS + bonusByStrike(frameIndex, playerRolls); //Calculating score. 10 points plus bonus for strike.
                first = factory.createStrike(); // Creating frame view.
                if (i == 9) { // Checking if actual frame is last in game and creating second and extra roll view.
                    second = factory.createRollView(playerRolls.get(frameIndex + 1).getPins(), false, playerRolls.get(frameIndex + 1).isFault());
                    extra = factory.createRollView(playerRolls.get(frameIndex + 2).getPins(), false, playerRolls.get(frameIndex + 2).isFault());
                }
                frameIndex++;
            } else if (isSpare(frameIndex, playerRolls)) { // Checking if spare.
                score = Constants.MAX_PINS + bonusBySpare(frameIndex, playerRolls); //Calculating score. 10 points plus bonus for spare
                first = factory.createRollView(playerRolls.get(frameIndex).getPins(), false, playerRolls.get(frameIndex).isFault());
                second = factory.createRollView(playerRolls.get(frameIndex + 1).getPins(), true, playerRolls.get(frameIndex + 1).isFault());
                if (i == 9) { // Checking if actual frame is last in game and creating second and extra roll view.
                    extra = factory.createRollView(playerRolls.get(frameIndex + 2).getPins(), false, playerRolls.get(frameIndex + 2).isFault());
                }
                frameIndex += 2;
            } else { //Normal frames.
                score = rollsScore(frameIndex, playerRolls);
                first = factory.createRollView(playerRolls.get(frameIndex).getPins(), false, playerRolls.get(frameIndex).isFault());
                second = factory.createRollView(playerRolls.get(frameIndex + 1).getPins(), false, playerRolls.get(frameIndex + 1).isFault());
                frameIndex += 2;
            }

            accumulatedScore += score;

            // Creating Frames View
            if (second == null){
                actualFrame = factory.createFrameView(first, i + 1);
            } else if (extra == null){
                actualFrame = factory.createFrameView(first, second, i + 1);
            } else {
                actualFrame = factory.createFrameView(first, second, extra);
            }

            //Setting score and accumulated.
            actualFrame.setScore(score);
            actualFrame.setAccumulatedScore(accumulatedScore);

            //Adding to actual game view frames.
            frameViews.add(actualFrame);
        }

        return factory.createGameView(playerName, frameViews);
    }



    @Override
    public List<String> detectPlayers() {
        return repository.getAllPlayerNames();
    }
}
