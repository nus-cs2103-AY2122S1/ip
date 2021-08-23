package configuration;

import java.nio.file.Paths;

/**
 * Constant Settings that are used to configure Dude CLI chatbot.
 */
public final class Setting {
	/** Path to file containing the tasks */
	public static final String FILE_TASK_PATH = Paths.get("data/tasks").toString();
}
