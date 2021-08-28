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
    private BufferedReader bufferedReader;

    /**
     * Creates a Logger with a specified filepath.
     *
     * @param filepath The specified filepath that the logger uses.
     */
    public Logger(String filepath) {
        Path path = Paths.get(filepath);
        try {
            if (!Files.exists(path)) {
                File file = new File(filepath);
                file.createNewFile();
            }
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
    public void write(List<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
            StringBuilder builder = new StringBuilder();

            for (Task currentTask : tasks) {
                int flag = currentTask.getStatusIcon().equals("X") ? 1 : 0;
                if (currentTask instanceof ToDo) {
                    builder.append("T | ")
                            .append(flag)
                            .append(" | ")
                            .append(currentTask.getDescription())
                            .append("\n");
                } else if (currentTask instanceof Deadline) {
                    builder.append("D | ")
                            .append(flag)
                            .append(" | ")
                            .append(currentTask.getDescription())
                            .append(" | ")
                            .append(((Deadline) currentTask).getBy())
                            .append("\n");
                } else if (currentTask instanceof Event) {
                    builder.append("E | ")
                            .append(flag)
                            .append(" | ")
                            .append(currentTask.getDescription())
                            .append(" | ")
                            .append(((Event) currentTask).getTiming())
                            .append("\n");
                }
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
    public List<Task> loadList() {
        List<Task> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader("tasks.txt"));
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] words = line.split("[|]");
                switch (words[0].trim()) {
                case "T":
                    ToDo todo = new ToDo(words[2].trim());
                    if (words[1].trim().equals("1")) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(words[2].trim(), words[3].trim());
                    if (words[1].trim().equals("1")) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Event event = new Event(words[2].trim(), words[3].trim());
                    if (words[1].trim().equals("1")) {
                        event.markAsDone();
                    }
                    list.add(event);
                    break;
                default:
                    break;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
