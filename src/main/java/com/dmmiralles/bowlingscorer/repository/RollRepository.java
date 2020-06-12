package com.dmmiralles.bowlingscorer.repository;

import com.dmmiralles.bowlingscorer.model.Roll;
import java.util.List;

/**
 * Represents the repository service for Roll model entity (Repository Design Pattern)
 */
public interface RollRepository {
    void create(Roll value);
    List<Roll> getByPlayerName(String playerName);
    List<String> getAllPlayerNames();
}
