package com.dmmiralles.bowlingscorer.service;

import com.dmmiralles.bowlingscorer.model.GameView;
import java.util.List;

/**
 * Represents the judge (referee) in game. It has 2 responsability:
 *
 * 1. Detects the players
 * 2. Process the rolls to judge.
 *
 */
public interface BowlingJudgeService {
    GameView processRolls(String playerName);
    List<String> detectPlayers();
}
