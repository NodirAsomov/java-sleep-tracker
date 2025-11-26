package ru.yandex.practicum.sleeptracker;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoader {

    public static List<SleepingSession> load(String resourceName) throws Exception {
        InputStream inputStream = FileLoader.class.getClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(SleepingSession::fromLine)
                    .collect(Collectors.toList());
        }
    }
}


