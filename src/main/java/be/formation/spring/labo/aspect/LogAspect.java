package be.formation.spring.labo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Around(value = "execution(* be.formation.spring.labo.*.*.*(..))")
    public Object logEverywhere(ProceedingJoinPoint joinPoint) throws Throwable {

        StringBuilder sb = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            sb.append(arg.toString());
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }

        LOGGER.info("Method called : " + joinPoint.getSignature().toShortString().replace("()", "").replace("(..)", "") + "(" + sb.toString() + ")");

        Object returned = null;

        try {
            returned = joinPoint.proceed(joinPoint.getArgs());

            String returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType().getSimpleName();

            StringBuilder methodLog = new StringBuilder();
            methodLog.append("Method ").append(returnType).append(" ").append(joinPoint.getSignature().toShortString());

            if (!returnType.equals("void")) {
                if (returned != null) {
                    methodLog.append(" returned ").append(returned.toString());
                } else {
                    methodLog.append(" returned null");
                }
            }
            LOGGER.info(methodLog.toString());
        } catch (Throwable throwable) {
            LOGGER.warn("Method " + joinPoint.getSignature().toShortString() + " threw error : " + throwable.getClass().getSimpleName() + " - " + throwable.getMessage());
            throw throwable;
        }

        return returned;
    }
}
