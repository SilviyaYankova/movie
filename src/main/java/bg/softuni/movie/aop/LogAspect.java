package bg.softuni.movie.aop;

import bg.softuni.movie.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* bg.softuni.movie.web.DramaController.dramaDetails(..))")
    public void dramaDetailsPointcut(){};

    @After("dramaDetailsPointcut()")
    public void afterDramaDetailsAdvice(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();

        Long dramaId = (Long) args[0];
        String action = joinPoint.getSignature().getName();

        logService.createDramaLog(action, dramaId);
    }

    @Pointcut("execution(* bg.softuni.movie.web.MovieController.movieDetails(..))")
    public void movieDetailsPointcut(){};

    @After("movieDetailsPointcut()")
    public void afterMovieDetailsPointcut(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();

        Long movieId = (Long) args[0];
        String action = joinPoint.getSignature().getName();

        logService.createMovieLog(action, movieId);
    }

}
