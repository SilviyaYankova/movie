package bg.softuni.movie.schedule;

import bg.softuni.movie.service.LogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogScheduler {

  private final LogService logService;

  public LogScheduler(LogService logService) {
    this.logService = logService;
  }

//  second,
//  minute,
//  hour,
//  day of month,
//  month,
//  day(s) of week.
  @Scheduled(cron = "0 10 15 * * *")
  public void onEvery24Hours() {
    logService.deleteOlderThen24Hours();
    System.out.println("Logs are deleted!");
  }

}
