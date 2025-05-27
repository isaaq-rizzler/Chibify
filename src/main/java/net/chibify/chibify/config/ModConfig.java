package net.chibify.chibify.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModConfig {
    public static final Path CONFIG_FILE = Path.of("config").resolve("chibify" + ".json");

    public static ModConfig INSTANCE = new ModConfig();

    public boolean shrinkSelf = false;
    public boolean AccurateEyeHeight = false;

    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        File file = CONFIG_FILE.toFile();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
            )) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load Chibify config", e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        File file = CONFIG_FILE.toFile();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}