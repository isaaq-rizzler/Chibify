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

    public static ModConfig CONFIG = new ModConfig();
    public static ModConfig DEFAULT = new ModConfig();

    public static boolean shrinkSelf = false;
    public static boolean AccurateEyeHeight = false;

    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        File file = CONFIG_FILE.toFile();

        if (file.exists()) {
            try (BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
            )) {
                ModConfig.CONFIG = GSON.fromJson(fileReader, ModConfig.class);
            } catch (IOException e) {
                throw new RuntimeException("Particle Rain config failed to load: ", e);
            }
        }
        if (ModConfig.CONFIG == null) {
            ModConfig.CONFIG = new ModConfig();
        }
    }

    public static void save() {
        File file = CONFIG_FILE.toFile();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            GSON.toJson(ModConfig.CONFIG, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface Percentage {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface OverrideName { String newName(); }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface ReloadsResources {}
}