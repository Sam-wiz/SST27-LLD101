package com.example.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Thread-safe singleton with lazy initialization, reflection protection, and serialization handling.
 */
public class AppSettings implements Serializable {
    private static volatile AppSettings instance;
    private static final Object lock = new Object();
    
    private final Properties props = new Properties();
    private volatile boolean loaded = false;

    private AppSettings() {
        // Prevent reflection-based instantiation
        if (instance != null) {
            throw new IllegalStateException("Cannot instantiate singleton via reflection");
        }
    }

    public static AppSettings getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new AppSettings();
                }
            }
        }
        return instance;
    }

    public void loadFromFile(Path file) {
        if (loaded) {
            return; // Prevent reloading
        }
        
        synchronized (lock) {
            if (loaded) {
                return;
            }
            
            try (InputStream in = Files.newInputStream(file)) {
                props.load(in);
                loaded = true;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
    
    // Preserve singleton on deserialization
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
