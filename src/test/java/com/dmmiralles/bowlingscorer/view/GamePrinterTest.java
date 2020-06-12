package com.dmmiralles.bowlingscorer.view;

import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import com.dmmiralles.bowlingscorer.repository.impl.RollRepositoryImpl;
import com.dmmiralles.bowlingscorer.service.BowlingJudgeService;
import com.dmmiralles.bowlingscorer.service.impl.BowlingJugdeServiceImpl;
import com.dmmiralles.bowlingscorer.view.impl.GamePrinterImpl;
import com.dmmiralles.bowlingscorer.view.impl.ViewFactoryImpl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GamePrinter class test cases")
public class GamePrinterTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;
  private static final PrintStream originalErr = System.err;

  private RollRepository repository;

  private ViewFactory factory;

  private BowlingJudgeService service;

  private GamePrinter printer;

  @BeforeEach
  public void setUp(){
    repository = new RollRepositoryImpl();
    factory = new ViewFactoryImpl();
    service = new BowlingJugdeServiceImpl(repository, factory);
    printer = new GamePrinterImpl();
    createRolls();
  }

  public void createRolls(){
    List<Roll> allRollsToInsert = new ArrayList<>();
    allRollsToInsert.add( new Roll("Jeff", 10, false));
    allRollsToInsert.add( new Roll("John", 3, false));
    allRollsToInsert.add( new Roll("John", 7, false));
    allRollsToInsert.add( new Roll("Jeff", 7, false));
    allRollsToInsert.add( new Roll("Jeff", 3, false));
    allRollsToInsert.add( new Roll("John", 6, false));
    allRollsToInsert.add( new Roll("John", 3, false));
    allRollsToInsert.add( new Roll("Jeff", 9, false));
    allRollsToInsert.add( new Roll("Jeff", 0, false));
    allRollsToInsert.add( new Roll("John", 10, false));
    allRollsToInsert.add( new Roll("Jeff", 10, false));
    allRollsToInsert.add( new Roll("John", 8, false));
    allRollsToInsert.add( new Roll("John", 1, false));
    allRollsToInsert.add( new Roll("Jeff", 0, false));
    allRollsToInsert.add( new Roll("Jeff", 8, false));
    allRollsToInsert.add( new Roll("John", 10, false));
    allRollsToInsert.add( new Roll("Jeff", 8, false));
    allRollsToInsert.add( new Roll("Jeff", 2, false));
    allRollsToInsert.add( new Roll("John", 10, false));
    allRollsToInsert.add( new Roll("Jeff", 0, true));
    allRollsToInsert.add( new Roll("Jeff", 6, false));
    allRollsToInsert.add( new Roll("John", 9, false));
    allRollsToInsert.add( new Roll("John", 0, false));
    allRollsToInsert.add( new Roll("Jeff", 10, false));
    allRollsToInsert.add( new Roll("John", 7, false));
    allRollsToInsert.add( new Roll("John", 3, false));
    allRollsToInsert.add( new Roll("Jeff", 10, false));
    allRollsToInsert.add( new Roll("John", 4, false));
    allRollsToInsert.add( new Roll("John", 4, false));
    allRollsToInsert.add( new Roll("Jeff", 10, false));
    allRollsToInsert.add( new Roll("Jeff", 8, false));
    allRollsToInsert.add( new Roll("Jeff", 1, false));
    allRollsToInsert.add( new Roll("John", 10, false));
    allRollsToInsert.add( new Roll("John", 9, false));
    allRollsToInsert.add( new Roll("John", 0, false));
    allRollsToInsert.forEach(x -> repository.create(x));
  }

  @BeforeAll
  public static void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterAll
  public static void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void printHeadersTest() {
    printer.printHeader();
    assertTrue(outContent.toString().length() > 0);
  }

  @Test
  public void printBodyTest() {
    printer.printBody(service.processRolls("Jeff"));
    assertTrue(outContent.toString().length() > 0);
  }
}
