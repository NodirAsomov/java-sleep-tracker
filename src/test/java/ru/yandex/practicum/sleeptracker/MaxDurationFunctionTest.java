package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxDurationFunctionTest {

    private final MaxDurationFunction function = new MaxDurationFunction();

    private SleepingSession sessionWithDuration(long minutes) {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime end = start.plusMinutes(minutes);
        return new SleepingSession(start, end, SleepQuality.GOOD);
    }

    @Test
    void shouldReturnMaxDurationForSeveralSessions() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(120),
                sessionWithDuration(200),
                sessionWithDuration(150)
        );

        SleepAnalysisResult<Long> result = function.apply(sessions);

        assertEquals(200L, result.getResult());
        assertEquals("Максимальная длительность сессии (мин)", result.getDescription());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult<Long> result = function.apply(sessions);

        assertEquals(0L, result.getResult());
    }

    @Test
    void shouldHandleSingleSession() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(90)
        );

        SleepAnalysisResult<Long> result = function.apply(sessions);

        assertEquals(90L, result.getResult());
    }

    @Test
    void shouldReturnCorrectValueWhenAllDurationsAreEqual() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(50),
                sessionWithDuration(50),
                sessionWithDuration(50)
        );

        SleepAnalysisResult<Long> result = function.apply(sessions);

        assertEquals(50L, result.getResult());
    }
}


