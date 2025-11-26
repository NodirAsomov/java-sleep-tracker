package ru.yandex.practicum.sleeptracker;


import java.util.List;

public class MinDurationFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long min = sessions.stream()
                .map(SleepingSession::getDurationMinutes)
                .min(Long::compareTo)
                .orElse(0L);

        return new SleepAnalysisResult<>(
                min,
                "Минимальная длительность сессии (мин)"
        );
    }
}

