package minimalmenu.config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;
import static com.google.gson.JsonParser.parseReader;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigHandler {
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("minimalmenu.json");

    public static boolean REMOVE_SPLASH;
    public static boolean REMOVE_EDITION;
    public static boolean REMOVE_SINGLEPLAYER;
    public static boolean REMOVE_MULTIPLAYER;
    public static boolean REMOVE_REALMS;
    public static boolean REMOVE_LANGUAGE;
    public static boolean REMOVE_ACCESSIBILITY;
    public static boolean REMOVE_COPYRIGHT;

    public static boolean REMOVE_ONLINE;
    public static boolean REMOVE_CREDITS;
    public static boolean REMOVE_TELEMETRY;

    public static boolean REMOVE_FEEDBACK;
    public static boolean REMOVE_BUGS;

    public static boolean DEV_MODE;

    public static void write() {
        try (
            final FileWriter fw = new FileWriter(CONFIG_PATH.toString());
            final JsonWriter jw = new JsonWriter(fw)
        ) {
            jw.setIndent("    ");
            jw.beginObject()
                    .name("REMOVE_SPLASH").value(REMOVE_SPLASH)
                    .name("REMOVE_EDITION").value(REMOVE_EDITION)
                    .name("REMOVE_SINGLEPLAYER").value(REMOVE_SINGLEPLAYER)
                    .name("REMOVE_MULTIPLAYER").value(REMOVE_MULTIPLAYER)
                    .name("REMOVE_REALMS").value(REMOVE_REALMS)
                    .name("REMOVE_LANGUAGE").value(REMOVE_LANGUAGE)
                    .name("REMOVE_ACCESSIBILITY").value(REMOVE_ACCESSIBILITY)
                    .name("REMOVE_COPYRIGHT").value(REMOVE_COPYRIGHT)

                    .name("REMOVE_ONLINE").value(REMOVE_ONLINE)
                    .name("REMOVE_CREDITS").value(REMOVE_CREDITS)
                    .name("REMOVE_TELEMETRY").value(REMOVE_TELEMETRY)

                    .name("REMOVE_FEEDBACK").value(REMOVE_FEEDBACK)
                    .name("REMOVE_BUGS").value(REMOVE_BUGS)

                    .name("DEV_MODE").value(DEV_MODE)
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() { //Runs on init to get data from json file.
        if (CONFIG_PATH.toFile().exists()) {
            try (final FileReader fr = new FileReader(CONFIG_PATH.toString())) {
                final JsonElement je = parseReader(fr);
                if (!je.isJsonObject()) {
                    setDefaults();
                }

                final JsonObject object = je.getAsJsonObject();
                REMOVE_SPLASH = readBoolean(object, "REMOVE_SPLASH");
                REMOVE_EDITION = readBoolean(object, "REMOVE_EDITION");
                REMOVE_SINGLEPLAYER = readBoolean(object, "REMOVE_SINGLEPLAYER");
                REMOVE_MULTIPLAYER = readBoolean(object, "REMOVE_MULTIPLAYER");
                REMOVE_REALMS = readBoolean(object, "REMOVE_REALMS");
                REMOVE_LANGUAGE = readBoolean(object, "REMOVE_LANGUAGE");
                REMOVE_ACCESSIBILITY = readBoolean(object, "REMOVE_ACCESSIBILITY");
                REMOVE_COPYRIGHT = readBoolean(object, "REMOVE_COPYRIGHT");

                REMOVE_ONLINE = readBoolean(object, "REMOVE_ONLINE");
                REMOVE_CREDITS = readBoolean(object, "REMOVE_CREDITS");
                REMOVE_TELEMETRY = readBoolean(object, "REMOVE_TELEMETRY");

                REMOVE_FEEDBACK = readBoolean(object, "REMOVE_FEEDBACK");
                REMOVE_BUGS = readBoolean(object, "REMOVE_BUGS");

                DEV_MODE = readBoolean(object, "DEV_MODE");
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            setDefaults();
        }
    }

    private static void setDefaults() {
        REMOVE_SPLASH = false;
        REMOVE_EDITION = false;
        REMOVE_SINGLEPLAYER = false;
        REMOVE_MULTIPLAYER = false;
        REMOVE_REALMS = false;
        REMOVE_LANGUAGE = false;
        REMOVE_COPYRIGHT = false;

        REMOVE_ONLINE = false;
        REMOVE_CREDITS = false;
        REMOVE_TELEMETRY = false;

        REMOVE_FEEDBACK = false;
        REMOVE_BUGS = false;

        DEV_MODE = false;
    }

    private static boolean readBoolean(JsonObject json, String key) {
        final JsonElement el = json.get(key);
        if (el == null || !el.isJsonPrimitive()) return false;
        try {
            return el.getAsBoolean();
        } catch (ClassCastException e) {
            return false;
        }
    }
}
