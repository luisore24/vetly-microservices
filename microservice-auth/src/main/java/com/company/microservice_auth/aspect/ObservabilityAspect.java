package com.company.microservice_auth.aspect;

import com.company.microservice_auth.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Aspect
@Component
@Slf4j
public class ObservabilityAspect {

    @Around("@annotation(observed)")
    public Object handleLogging(ProceedingJoinPoint joinPoint, Observed observed) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();

        try {
            if (observed.logRequest()){
                log.info("[START] | Process: {} | Method: {} | Arguments Request: {}", observed.event(), methodName, Arrays.toString(joinPoint.getArgs()));
            }
            else{
                log.info("[START] | Process: {} | Method: {} | Arguments Request: [Hidden]", observed.event(), methodName);
            }

            Object result = joinPoint.proceed();

            long duartionProcess = System.currentTimeMillis() - startTime;

            if (observed.logResponse()){

                if(result instanceof ApiResponse<?> apiResponse){

                    System.out.println(apiResponse.getData().getClass());

                    if(apiResponse.getData() instanceof Collection<?> collection){

                        if(collection.size() > 1){
                            log.info("[END] | Process: {} | Method: {} | Time: {} | Arguments Response: {} records", observed.event(), methodName, duartionProcess, collection.size());
                        }
                        else {
                            log.info("[END] | Process: {} | Method: {} | Time: {} | Arguments Response: {}", observed.event(), methodName, duartionProcess, result);
                        }

                    }
                    else{
                        log.info("[END] | Process: {} | Method: {} | Time: {} | Arguments Response: {}", observed.event(), methodName, duartionProcess, result);
                    }

                }

                else{
                    log.info("[END] | Process: {} | Method: {} | Time: {} | Arguments Response: {}", observed.event(), methodName, duartionProcess, result);
                }



            }
            else{
                log.info("[END] | Process: {} | Method: {} | Time: {} | Arguments Response: [Hidden]", observed.event(), methodName, duartionProcess);
            }
            return result;


        } catch (Throwable e) {

            long duartionProcess = System.currentTimeMillis() - startTime;

            log.error("[END ERROR] | Process: {} | Method: {} | Time: {} | ErrorMessage: {}", observed.event(), methodName, duartionProcess, e.getMessage());

            throw e;
        }

    }

}
