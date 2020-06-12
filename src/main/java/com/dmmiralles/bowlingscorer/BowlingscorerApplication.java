package com.dmmiralles.bowlingscorer;

import com.dmmiralles.bowlingscorer.controller.BowlingJudgeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BowlingscorerApplication implements CommandLineRunner {

	@Autowired
	private BowlingJudgeController bowlingJudgeController;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BowlingscorerApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}


	@Override
	public void run(String... strings) throws Exception {
		bowlingJudgeController.judgeGame(strings[0]);
	}

}
