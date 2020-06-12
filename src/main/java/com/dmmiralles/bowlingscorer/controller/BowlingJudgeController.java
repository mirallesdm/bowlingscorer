package com.dmmiralles.bowlingscorer.controller;

import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import com.dmmiralles.bowlingscorer.service.BowlingJudgeService;
import com.dmmiralles.bowlingscorer.util.Constants;
import com.dmmiralles.bowlingscorer.view.GamePrinter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

/**
 * Application unique controller (The C in MVC architecture style). Responsible of 3 steps:
 *
 * 1. Process the input.
 * 2. Invoke the judge service.
 * 3. Print the result using the Printer Helper
 *
 * @author Daniel Miralles
 */

@AllArgsConstructor
public class BowlingJudgeController {

    private final RollRepository repository;
    private final BowlingJudgeService service;
    private final GamePrinter printer;


    private Roll convertFileToRoll(String fileLine) {
        String[] line = fileLine.split(Constants.SPLIT_CHARACTER);
        String name = line[0];
        int pin = Constants.OUT_FAULT.equalsIgnoreCase(line[1]) ? 0 :  Integer.parseInt(line[1]);
        return new Roll(name, pin, Constants.OUT_FAULT.equalsIgnoreCase(line[1]));
    }

    private void processInput(String fileName) throws IOException {
        try (Stream<String> input = Files.lines(Paths.get(fileName))){
            input
                .map(this::convertFileToRoll)
                .forEach(repository::create);
        }
    }

    public void judgeGame(String fileName) throws IOException {
        processInput(fileName);
        printer.printHeader();
        service
            .detectPlayers()
            .stream()
            .map(service::processRolls)
            .forEach(printer::printBody);
    }


}
