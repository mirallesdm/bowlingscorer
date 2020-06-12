package com.dmmiralles.bowlingscorer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Concrete implementation of Roll interface.
 */
@Getter
@Setter
@AllArgsConstructor
public class Roll {

  private String playerName;
  private int pins;
  private boolean isFault;
}
