package configuration;

import java.nio.file.Paths;

/**
 * Constant Settings that are used to configure chatbot.Dude CLI chatterbot.
 */
public final class Setting {
    /** Path to data directory */
    public static final String DATA_DIR_PATH = Paths.get("data").toString();
    
    /** File name to data tasks directory */
    public static final String DATA_TASKS = Paths.get("tasks").toString();
}
