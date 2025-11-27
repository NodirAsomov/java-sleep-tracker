package ru.yandex.practicum.sleeptracker;


import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<String> apply(List<SleepingSession> sessions) {


        Map<String, Long> counts = sessions.stream()
                .filter(this::isNightSession)
                .map(this::detectType)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));


        long maxCount = counts.values().stream().mapToLong(Long::longValue).max().orElse(0);


        List<String> topTypes = counts.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();


        String dominantType = topTypes.size() == 1 ? topTypes.get(0) : "Голубь";

        return new SleepAnalysisResult<>(dominantType, "Хронотип пользователя");
    }


    private boolean isNightSession(SleepingSession s) {
        LocalTime startTime = s.getStart().toLocalTime();
        return startTime.isBefore(LocalTime.NOON);
    }


    private String detectType(SleepingSession s) {
        LocalTime fallAsleep = s.getStart().toLocalTime();
        LocalTime wakeUp = s.getEnd().toLocalTime();

        if (fallAsleep.isAfter(LocalTime.of(23, 0)) && wakeUp.isAfter(LocalTime.of(9, 0))) {
            return "Сова";
        } else if (fallAsleep.isBefore(LocalTime.of(22, 0)) && wakeUp.isBefore(LocalTime.of(7, 0))) {
            return "Жаворонок";
        } else {
            return "Голубь";
        }
    }
}
