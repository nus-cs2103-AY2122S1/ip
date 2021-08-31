package bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Handles saving and loading bot data
 */
public class Storage {

    public static final String SAVE_FILE_PATH = "data/save.txt";

    private static void write(List<String> texts) {
        try {
            FileWriter writer = new FileWriter(getAbsolutePath());
            for (String s : texts) {
                writer.write(s + System.lineSeparator());
            }
            writer.close();
        } catch (IOException ignored) {
        }
    }

    private static List<String> read() {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Save Bot's task list
     *
     * @param bot running bot
     */
    public static void save(Bot bot) {
        Storage.write(bot.getTaskList().getTaskStringList());
    }

    /**
     * Load saved data to Bot's saved list
     *
     * @param bot running bot
     */
    public static void load(Bot bot) {
        List<Task> tasks = new ArrayList<>();
        List<String> taskStrings = Storage.read();
        for (String taskString : taskStrings) {
            if (!taskString.equals("")) {
                tasks.add(Task.deserialize(taskString));
            }
        }
        bot.getTaskList().set(tasks);
    }

    private static String getAbsolutePath() {
        return new File(SAVE_FILE_PATH).getAbsolutePath();
    }

}
