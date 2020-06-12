package com.dmmiralles.bowlingscorer.configuration;

import com.dmmiralles.bowlingscorer.controller.BowlingJudgeController;
import com.dmmiralles.bowlingscorer.repository.RollRepository;
import com.dmmiralles.bowlingscorer.repository.impl.RollRepositoryImpl;
import com.dmmiralles.bowlingscorer.service.BowlingJudgeService;
import com.dmmiralles.bowlingscorer.service.impl.BowlingJugdeServiceImpl;
import com.dmmiralles.bowlingscorer.view.GamePrinter;
import com.dmmiralles.bowlingscorer.view.ViewFactory;
import com.dmmiralles.bowlingscorer.view.impl.GamePrinterImpl;
import com.dmmiralles.bowlingscorer.view.impl.ViewFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BownlingJudgeConfiguration {

  @Bean
  public RollRepository repository() {
    return new RollRepositoryImpl();
  }


  @Bean
  public ViewFactory viewFactory() {
    return new ViewFactoryImpl();
  }

  @Bean
  public BowlingJudgeService service(RollRepository repository, ViewFactory factory){
    return new BowlingJugdeServiceImpl(repository, factory);
  }

  @Bean
  public GamePrinter printer(){
    return new GamePrinterImpl();
  }

  @Bean
  public BowlingJudgeController controller(RollRepository repository, BowlingJudgeService service, GamePrinter printer) {
    return new BowlingJudgeController(repository, service, printer);
  }

}
