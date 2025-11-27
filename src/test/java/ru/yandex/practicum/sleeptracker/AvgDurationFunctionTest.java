package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvgDurationFunctionTest {

    private final AvgDurationFunction function = new AvgDurationFunction();

    private SleepingSession sessionWithDuration(long minutes) {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime end = start.plusMinutes(minutes);
        return new SleepingSession(start, end, SleepQuality.GOOD);
    }

    @Test
    void shouldReturnAverageForSeveralSessions() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(60),
                sessionWithDuration(120),
                sessionWithDuration(180)
        );

        SleepAnalysisResult<Double> result = function.apply(sessions);

        assertEquals(120.0, result.getResult(), 0.001);
        assertEquals("Средняя длительность сессии (мин)", result.getDescription());
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult<Double> result = function.apply(sessions);

        assertEquals(0.0, result.getResult(), 0.001);
    }

    @Test
    void shouldHandleSingleSession() {
        List<SleepingSession> sessions = List.of(
                sessionWithDuration(90)
        );

        SleepAnalysisResult<Double> result = function.apply(sessions);

        assertEquals(90.0, result.getResult(), 0.001);
    }
}

