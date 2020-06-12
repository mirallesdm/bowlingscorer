package com.dmmiralles.bowlingscorer.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.repository.impl.RollRepositoryImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("RollRepository class test cases")
public class RollRepositoryTest {

  @Test
  @SuppressWarnings("unchecked")
  public void createTest() {
    //given
    Roll roll = mock(Roll.class);
    List<Roll> rolls = (List<Roll>) spy(List.class);
    RollRepository repository = new RollRepositoryImpl(rolls);

    //then
    repository.create(roll);

    //expect
    verify(rolls, times(1)).add(roll);
  }

  @Test
  public void getByPlayerNameTest() {
    //given
    Roll ok = new Roll("Daniel", 4, false);
    Roll fail = new Roll("David", 5, false);
    List<Roll> rolls = Arrays.asList(ok, fail);
    RollRepository repository = new RollRepositoryImpl(rolls);

    //then
    List<Roll> fromDaniel = repository.getByPlayerName("Daniel");

    //expect
    assertEquals(1, fromDaniel.size());
    assertEquals("Daniel", fromDaniel.get(0).getPlayerName());
  }

  @Test
  public void getAllPlayerNameTest() {
    //given
    Roll ok = new Roll("Daniel", 4, false);
    Roll ok1 = new Roll("Daniel", 5, false);
    Roll fail = new Roll("David", 5, false);
    List<Roll> rolls = Arrays.asList(ok, ok1, fail);
    RollRepository repository = new RollRepositoryImpl(rolls);

    //then
    List<String> names = repository.getAllPlayerNames();

    //expect
    assertEquals(2, names.size());
    assertTrue(names.contains("Daniel"));
    assertTrue(names.contains("David"));
  }

}
