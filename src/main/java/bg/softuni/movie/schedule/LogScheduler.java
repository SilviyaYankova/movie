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

    @Scheduled(cron = "0 52 15 * * *")
    public void onEvery24Hours() {
        logService.deleteOlderThen24Hours();
        System.out.println("Logs are deleted!");
    }
}
