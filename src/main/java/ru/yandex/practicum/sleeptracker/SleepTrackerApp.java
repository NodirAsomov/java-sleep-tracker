package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SleepTrackerApp {

    private static final List<SleepAnalysisFunction> FUNCTIONS = List.of(
            new CountSessionsFunction(),
            new MinDurationFunction(),
            new MaxDurationFunction(),
            new AvgDurationFunction(),
            new CountBadQualityFunction(),
            new SleeplessNightsFunction(),
            new ChronotypeFunction()
    );

    public static List<SleepAnalysisFunction> getFunctions() {
        return FUNCTIONS;
    }

    public static void main(String[] args) throws Exception {
        List<SleepingSession> sessions = FileLoader.load("sleep_log.txt");

        FUNCTIONS.stream()
                .map(f -> f.apply(sessions))
                .forEach(res ->
                        System.out.println(res.getDescription() + ": " + res.getResult())
                );
    }
}
