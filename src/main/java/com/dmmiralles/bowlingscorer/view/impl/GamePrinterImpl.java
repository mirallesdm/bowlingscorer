package com.dmmiralles.bowlingscorer.view.impl;

import com.dmmiralles.bowlingscorer.model.GameView;
import com.dmmiralles.bowlingscorer.util.Constants;
import com.dmmiralles.bowlingscorer.view.GamePrinter;
import java.io.PrintStream;
import java.util.stream.IntStream;

/**
 * GameView printer helper. This class print the game view and board header in standar output.
 */
public class GamePrinterImpl implements GamePrinter {

    PrintStream printer = System.out;
    private static String getSpaceByScore(int score){
        if (score > 99)
            return Constants.SPACE_6;
        return Constants.SPACE_7;
    }

    @Override
    public void printHeader(){
        printer.print(Constants.FRAME_WORD + Constants.TAB_SPACE + Constants.TAB_SPACE);
        IntStream.rangeClosed(Constants.FIRST_FRAME, Constants.MAX_FRAMES).forEach(x -> printer.print(x + Constants.TAB_SPACE + Constants.TAB_SPACE));
        printer.print(Constants.CURSOR_RETURN);
    }

    @Override
    public void printBody(GameView game){
        printer.println(game.getPlayerName());
        printer.print(Constants.PINFALLS_WORD + Constants.SPACE_5);
        game.getFrames().forEach(x -> printer.print(x + Constants.TAB_SPACE));
        printer.print(Constants.CURSOR_RETURN);
        printer.print(Constants.SCORE_WORD + Constants.TAB_SPACE + Constants.TAB_SPACE);
        game.getFrames().forEach(x -> printer.print(x.getAccumulatedScore() + getSpaceByScore(x.getAccumulatedScore())));
        printer.print(Constants.CURSOR_RETURN);
    }
}
