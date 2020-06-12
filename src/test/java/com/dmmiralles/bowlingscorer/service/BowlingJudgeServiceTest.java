package com.dmmiralles.bowlingscorer.service;

import com.dmmiralles.bowlingscorer.model.FrameView;
import com.dmmiralles.bowlingscorer.model.GameView;
import com.dmmiralles.bowlingscorer.model.Roll;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import com.dmmiralles.bowlingscorer.repository.impl.RollRepositoryImpl;
import com.dmmiralles.bowlingscorer.service.impl.BowlingJugdeServiceImpl;
import com.dmmiralles.bowlingscorer.view.ViewFactory;
import com.dmmiralles.bowlingscorer.view.impl.ViewFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by dmmiralles on 11/8/2017.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BowlingJudgeServiceTest class test cases")
public class BowlingJudgeServiceTest {

    private RollRepository repository;

    private ViewFactory factory;

    private BowlingJudgeService service;

    @BeforeEach
    public void setUp(){
        repository = new RollRepositoryImpl();
        factory = new ViewFactoryImpl();
        service = new BowlingJugdeServiceImpl(repository, factory);
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

    @Test
    public void testJudge(){
        GameView johnGame = service.processRolls("John");
        GameView jeffGame = service.processRolls("Jeff");

        assertEquals("John", johnGame.getPlayerName());
        assertEquals("Jeff", jeffGame.getPlayerName());
        assertEquals(10, johnGame.getFrames().size());
        assertEquals(10, jeffGame.getFrames().size());

        FrameView tenthFrame = jeffGame.getFrames().get(9);

        assertTrue(tenthFrame.toString().contains("X"));
        assertTrue(tenthFrame.toString().contains("8"));
        assertTrue(tenthFrame.toString().contains("1"));
        assertEquals(3, tenthFrame.toString().replaceAll("\\s", "").length());
        assertEquals(167, tenthFrame.getAccumulatedScore());
    }

    @Test
    public void testDetectPlayer(){
        List<String> players = service.detectPlayers();
        assertEquals(2, players.size());
        assertFalse(players.get(0).equalsIgnoreCase(players.get(1)));
        assertTrue(players.get(0).equals("John") || players.get(0).equals("Jeff"));
        assertTrue(players.get(1).equals("Jeff") || players.get(1).equals("John"));
    }

}
