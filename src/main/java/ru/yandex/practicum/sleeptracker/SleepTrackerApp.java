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

        if (args.length == 0) {
            System.err.println("Usage: java SleepTrackerApp <path-to-sleep-log>");
            return;
        }

        String logFilePath = args[0];

        List<SleepingSession> sessions = FileLoader.load(logFilePath);

        FUNCTIONS.stream()
                .map(f -> f.apply(sessions))
                .forEach(res ->
                        System.out.println(res.getDescription() + ": " + res.getResult())
                );
    }

}
