package com.dmmiralles.bowlingscorer.integration;

import com.dmmiralles.bowlingscorer.BowlingscorerApplication;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;

import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BowlingscorerApplication.class },
    initializers = ConfigFileApplicationContextInitializer.class)
public class BowlingIntegrationTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;
  private static final PrintStream originalErr = System.err;

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

  @SpyBean
  CommandLineRunner commandLineRunner;

  @Test
  void whenContextLoads_thenRunnersDoNotRun() throws Exception {
    assertNotNull(commandLineRunner);

    verify(commandLineRunner, times(0)).run(any());
  }

  @Test
  public void case1Test() throws Exception {
    commandLineRunner.run( "input.txt");
    assertTrue(outContent.toString().length() > 0);
  }

  @Test
  public void case2Test() throws Exception {
    commandLineRunner.run( "input1.txt");
    assertTrue(outContent.toString().length() > 0);
  }


}
