package com.dmmiralles.bowlingscorer.view;

import com.dmmiralles.bowlingscorer.model.GameView;

public interface GamePrinter {
  void printHeader();
  void printBody(GameView view);

}
