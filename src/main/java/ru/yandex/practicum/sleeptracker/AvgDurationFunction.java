package ru.yandex.practicum.sleeptracker;


import java.util.List;

public class AvgDurationFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Double> apply(List<SleepingSession> sessions) {
        double avg = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult<>(
                avg,
                "Средняя длительность сессии (мин)"
        );
    }
}
