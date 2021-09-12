package duke.storage;

import static duke.common.Formats.DT_DATA_FORMAT;
import static duke.common.Formats.DT_NOW_FORMAT_STRING;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents the file used to store task list data..
 */
public class Storage {
    private String fileName;
    private String archiveFileName;

    /**
     * Default constructor for a Storage object.
     *
     * @param fileName Filepath to the main task list text file.
     * @param archiveFileName Filepath to the archive of past tasks.
     */
    public Storage(String fileName, String archiveFileName) {
        this.fileName = fileName;
        this.archiveFileName = archiveFileName;
    }

    /**
     * Loads the task list data from this storage file, and then returns it.
     * Returns an empty task list if the file does not exist, or is not a regular file.
     *
     * @throws IOException If there were errors reading and/or converting data from file.
     */
    public ArrayList<Task> load() throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(100);
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                String[] tokens = line.trim().split("\\s\\|\\s", 3); // max range 2
                String taskIdentifier = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String remainder = tokens[2];
                String desc;
                String time;

                switch (taskIdentifier) {
                case "T":
                    desc = remainder;
                    tasks.add(new Todo(isDone, desc));
                    break;
                case "D":
                    String[] deadlineTokens = remainder.split("\\s\\|\\s*");
                    desc = deadlineTokens[0];
                    time = deadlineTokens[1];
                    tasks.add(new Deadline(isDone, desc, LocalDateTime.parse(time, DT_DATA_FORMAT)));
                    break;
                case "E":
                    String[] eventTokens = remainder.split("\\s\\|\\s*");
                    desc = eventTokens[0];
                    time = eventTokens[1];
                    tasks.add(new Event(isDone, desc, LocalDateTime.parse(time, DT_DATA_FORMAT)));
                    break;
                default:
                    break;
                }
            }
        } catch (NoSuchFileException e) {
            Path storagePath = Paths.get(fileName);
            Files.createDirectories(storagePath.getParent());
            Files.createFile(storagePath);
            System.out.println("No file found, but we created one!");
            return load();
        } catch (IOException e) {
            System.out.println("Something went wrong during reading!");
        } finally {

            return tasks;
        }
    }

    /**
     * Saves the task list data to the storage file.
     *
     * @throws IOException if there were errors reading and/or converting data from file.
     */
    public void write (ArrayList<Task> tasks) {
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileName, false));
            for (int i = 0; i < tasks.size(); i++) {
                outputWriter.write(tasks.get(i).getData());
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during writing!");
            System.out.println(e.toString() + e.getMessage());
        } finally {

        }
    }

    /**
     * Archives the task list data to the storage file.
     *
     * @throws IOException If there were errors reading and/or converting data from file.
     */
    public void archive(ArrayList<Task> tasks) {
        BufferedWriter outputWriter = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DT_NOW_FORMAT_STRING);
        try {
            outputWriter = new BufferedWriter(new FileWriter(archiveFileName, true));
            outputWriter.write(String.format("== ARCHIVE FROM: %s ==\n", sdf.format(cal.getTime())));
            for (int i = 0; i < tasks.size(); i++) {
                outputWriter.write(tasks.get(i).getData());
                outputWriter.newLine();
            }
            outputWriter.write("\n");
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during writing!");
            System.out.println(e.toString() + e.getMessage());
        } finally {

        }
    }
}
