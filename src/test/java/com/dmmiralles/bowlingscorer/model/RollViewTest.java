package com.dmmiralles.bowlingscorer.model;

import com.dmmiralles.bowlingscorer.util.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("RollView class test cases")
public class RollViewTest {

  @Test
  @DisplayName("Testing if is strike")
  public void isStrikeTest() {
    //given
    RollView rollView = new RollView(10, false, false);

    //then
    boolean ret = rollView.isStrike();

    //expect
    assertTrue(ret);
  }

  @Test
  @DisplayName("Testing if is not strike")
  public void notStrikeTest() {
    //given
    RollView rollView = new RollView(9, true, true);

    //then
    boolean ret = rollView.isStrike();

    //expect
    assertFalse(ret);
  }

  @Test
  @DisplayName("Testing value when strikes")
  public void getValueWhenStrike() {
    //given
    RollView rollView = new RollView(10, false, false);

    //then
    String ret = rollView.getValue();

    //expect
    assertEquals(Constants.OUT_STRIKE, ret);
  }

  @Test
  @DisplayName("Testing value when fails")
  public void getValueWhenFails() {
    //given
    RollView rollView = new RollView(3, true, false);

    //then
    String ret = rollView.getValue();

    //expect
    assertEquals(Constants.OUT_FAULT, ret);
  }

  @Test
  @DisplayName("Testing value when spares")
  public void getValueWhenSpares() {
    //given
    RollView rollView = new RollView(3, false, true);

    //then
    String ret = rollView.getValue();

    //expect
    assertEquals(Constants.OUT_SPARE, ret);
  }

  @Test
  @DisplayName("Testing value in normal roll")
  public void getValueWhenNormal() {
    //given
    RollView rollView = new RollView(9, false, false);

    //then
    String ret = rollView.getValue();

    //expect
    assertEquals("9", ret);
  }



}
