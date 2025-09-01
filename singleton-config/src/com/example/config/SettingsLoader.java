package com.example.config;

import java.nio.file.Path;

/** Wrapper that uses the singleton instance. */
public class SettingsLoader {
    public AppSettings load(Path file) {
        AppSettings s = AppSettings.getInstance(); // use singleton
        s.loadFromFile(file);
        return s;
    }
}
