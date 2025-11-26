package ru.yandex.practicum.sleeptracker;

import java.time.*;
import java.util.List;
import java.util.stream.LongStream;

public class SleeplessNightsFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {

        LocalDate startDate = sessions.get(0).getStart().toLocalDate();
        LocalDate endDate = sessions.get(sessions.size() - 1).getEnd().toLocalDate();

        long totalNights = Duration.between(
                startDate.atStartOfDay(),
                endDate.plusDays(1).atStartOfDay()
        ).toDays();

        long sleepless = LongStream.range(0, totalNights)
                .mapToObj(startDate::plusDays)
                .filter(date -> isSleepless(date, sessions))
                .count();

        return new SleepAnalysisResult<>(
                sleepless,
                "Количество бессонных ночей"
        );
    }

    private boolean isSleepless(LocalDate date, List<SleepingSession> sessions) {
        LocalDateTime nightStart = date.atStartOfDay();
        LocalDateTime nightEnd = date.atTime(6, 0);

        return sessions.stream()
                .noneMatch(s -> s.getEnd().isAfter(nightStart)
                        && s.getStart().isBefore(nightEnd));
    }
}
