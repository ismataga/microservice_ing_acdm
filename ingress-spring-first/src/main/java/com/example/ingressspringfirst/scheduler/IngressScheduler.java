package com.example.ingressspringfirst.scheduler;

import com.example.ingressspringfirst.service.UserService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngressScheduler {
    private final UserService userService;

    @Scheduled(fixedDelayString =  "PT1M")
    @SchedulerLock(name = "HandleReverseFailures",
            lockAtLeastFor = "PT1M", lockAtMostFor = "PT5M")
    public void test() {
        userService.test();
    }
}
