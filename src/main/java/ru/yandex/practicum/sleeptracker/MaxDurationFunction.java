package ru.yandex.practicum.sleeptracker;


import java.util.List;

public class MaxDurationFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long max = sessions.stream()
                .map(SleepingSession::getDurationMinutes)
                .max(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult<>(
                max,
                "Максимальная длительность сессии (мин)"
        );
    }
}
