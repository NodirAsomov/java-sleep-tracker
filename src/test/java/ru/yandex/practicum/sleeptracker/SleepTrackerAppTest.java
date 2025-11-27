package ru.yandex.practicum.sleeptracker;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepTrackerAppTest {

    private SleepingSession session(long minutes) {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime end = start.plusMinutes(minutes);
        return new SleepingSession(start, end, SleepQuality.GOOD);
    }

    @Test
    void shouldRunAllFunctionsWithoutErrors() {
        List<SleepingSession> sessions = List.of(
                session(60),
                session(120),
                session(180)
        );

        SleepTrackerApp.getFunctions().stream()
                .map(f -> f.apply(sessions))
                .forEach(res -> {
                    assertNotNull(res.getResult(), "Result should not be null");
                    assertNotNull(res.getDescription(), "Description should not be null");
                    System.out.println(res.getDescription() + ": " + res.getResult());
                });

    }
}
