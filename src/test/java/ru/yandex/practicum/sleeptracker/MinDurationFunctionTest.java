package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinDurationFunctionTest {

    private final MinDurationFunction function = new MinDurationFunction();

    private SleepingSession sessionWithDuration(long minutes) {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime end = start.plusMinutes(minutes);
        return new SleepingSession(start, end, SleepQuality.GOOD);
    }

    @Test
    void shouldReturnMinDurationForSeveralSessions() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(120),
                sessionWithDuration(200),
                sessionWithDuration(150)
        );

        SleepAnalysisResult<Long> result = function.apply(sessions);

        assertEquals(120L, result.getResult());
        assertEquals("Минимальная длительность сессии (мин)", result.getDescription());
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
}

