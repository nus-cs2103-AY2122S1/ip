package bot.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Task;
import bot.tasks.ToDo;

/**
 * Represents a logger that can read logs and act according to logs provided.
 */
public class Logger {
    private static BufferedReader bufferedReader;

    private Logger() {}

    protected static void initialize() {
        try {
            ensureTasksExist();
            Path path = Paths.get("tasks.txt");
            bufferedReader = Files.newBufferedReader(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes to the file containing the Bot.tasks.
     *
     * @param tasks The list of tasks that are due for writing.
     */
    public static void write(List<Task> tasks) {
        try {
            ensureTasksExist();
            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
            StringBuilder builder = new StringBuilder();

            for (Task currentTask : tasks) {
                builder.append(currentTask.toListFormat());
            }

            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the file according to the filepath of this logger.
     *
     * @return A list of Tasks.
     */
    public static List<Task> loadList() {
        List<Task> list = new ArrayList<>();
        try {
            ensureTasksExist();
            bufferedReader = new BufferedReader(new FileReader("tasks.txt"));
            String line = bufferedReader.readLine();

            while (line != null) {
                markAndAddTask(line, list);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void ensureTasksExist() throws IOException {
        Path path = Paths.get("tasks.txt");
        // Adapted the following file setup from https://github.com/Yukun99/ip/
        if (!Files.exists(path)) {
            String newPath = System.getProperty("user.dir");
            newPath = newPath.replace("\\", "/");
            newPath += "/tasks.txt";
            File file = new File(newPath);
            file.createNewFile();
        }
    }

    private static void markAndAddTask(String line, List<Task> list) {
        String[] words = line.split("[|]");
        switch (words[0].trim()) {
        case "T":
            markAndAddTodo(list, words);
            break;
        case "D":
            markAndAddDeadline(list, words);
            break;
        case "E":
            markAndAddEvent(list, words);
            break;
        default:
            break;
        }
    }

    private static void markAndAddTodo(List<Task> list, String[] words) {
        ToDo todo = new ToDo(words[2].trim());
        if (words[1].trim().equals("1")) {
            todo.markAsDone();
        }
        list.add(todo);
    }

    private static void markAndAddDeadline(List<Task> list, String[] words) {
        Deadline deadline = new Deadline(words[2].trim(), words[3].trim());
        if (words[1].trim().equals("1")) {
            deadline.markAsDone();
        }
        list.add(deadline);
    }

    private static void markAndAddEvent(List<Task> list, String[] words) {
        Event event = new Event(words[2].trim(), words[3].trim());
        if (words[1].trim().equals("1")) {
            event.markAsDone();
        }
        list.add(event);
    }
}
