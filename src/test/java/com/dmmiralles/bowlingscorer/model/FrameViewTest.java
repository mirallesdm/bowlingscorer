package com.dmmiralles.bowlingscorer.model;

import com.dmmiralles.bowlingscorer.util.Constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("FrameView class test cases")
public class FrameViewTest {

  @Test
  @DisplayName("Checking printing the last frame with extra")
  public void toStringLastFrameWithExtraTest() {
    //given
    RollView first = mock(RollView.class);
    when(first.getValue()).thenReturn("1");
    RollView second = mock(RollView.class);
    when(second.getValue()).thenReturn("2");
    RollView extra = mock(RollView.class);
    when(extra.getValue()).thenReturn("3");
    FrameView fv = new FrameView();
    fv.setFirst(first);
    fv.setSecond(second);
    fv.setOrder(Constants.MAX_FRAMES);
    fv.setExtra(extra);

    //then
    String ret = fv.toString();

    //expect
    assertEquals(ret, "1" + Constants.SPACE_3 + "2" + Constants.SPACE_3 + "3");
    verify(first, times(1)).getValue();
    verify(second, times(1)).getValue();
    verify(extra, times(1)).getValue();
  }

  @Test
  @DisplayName("Checking printing first and second no null")
  public void toStringFirstAndSecondNoNullTest() {
    //given
    RollView first = mock(RollView.class);
    when(first.getValue()).thenReturn("1");
    RollView second = mock(RollView.class);
    when(second.getValue()).thenReturn("2");

    FrameView fv = new FrameView();
    fv.setFirst(first);
    fv.setSecond(second);

    //then
    String ret = fv.toString();

    //expect
    assertEquals(ret, "1" + Constants.SPACE_3 + "2");
    verify(first, times(1)).getValue();
    verify(second, times(1)).getValue();
  }

  @Test
  @DisplayName("Checking printing first no null")
  public void toStringFirstNoNullTest() {
    //given
    RollView first = mock(RollView.class);
    when(first.getValue()).thenReturn("1");

    FrameView fv = new FrameView();
    fv.setFirst(first);

    //then
    String ret = fv.toString();

    //expect
    assertEquals(ret, Constants.SPACE_4 + "1");
    verify(first, times(1)).getValue();
  }

  @Test
  @DisplayName("Checking printing empty object")
  public void toStringEmptyObject() {
    FrameView fv = new FrameView();

    //then
    String ret = fv.toString();

    //expect
    assertEquals("", ret);
  }

}
