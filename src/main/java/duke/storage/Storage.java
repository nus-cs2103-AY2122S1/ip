package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.parser.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents a storage that stores the task data locally.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Storage {
    private static String path;
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * A constructor for Storage.
     *
     * @param filePath Input path for the data file.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Creates file path if input file does not exist.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public void checkFile() throws IOException {
        final Path p = Paths.get(path);
        final String directory = p.getParent().toString();
        final String filename = p.getFileName().toString();

        File file = new File(filename);
        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Initialize the old tasks into current list of Tasks.
     *
     * @return Array list of Task.
     * @throws IOException If an input or output exception occurred.
     */
    public ArrayList<Task> loadTask() throws IOException {
        try {
            checkFile();
            File f = new File(path);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] words = nextLine.split(", ", 0);
                String type = words[0];
                Boolean isDone = Boolean.valueOf(words[1]);
                String description = words[2];

                switch (type) {
                case "T":
                    Parser todoParser = new Parser("todo " + description);
                    String todo = todoParser.getTodoDescription();
                    tasks.add(new Todo(todo, isDone));
                    break;
                case "D":
                    Parser deadlineParser = new Parser("deadline " + description);
                    String deadline = deadlineParser.getDeadlineDescription();
                    String date = deadlineParser.getDeadlineDate();
                    tasks.add(new Deadline(deadline, date, isDone));
                    break;
                case "E":
                    Parser eventParser = new Parser("event " + description);
                    String event = eventParser.getEventDescription();
                    String duration = eventParser.getEventDate();
                    tasks.add(new Event(event, duration, isDone));
                    break;
                default:
                    throw new IllegalStateException("Unexpected type: " + type);
                }
            }
            System.out.println("Finished loading saved file.");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("A new file is created!");
        }
        return tasks;
    }

    /**
     * Writes the tasks into the local data file.
     */
    public static void saveList() {
        try {
            FileWriter fw = new FileWriter(path);

            for (Task t : tasks) {
                String str = t.getType() + ", " + t.isDone() + ", " + t.getDescription();
                fw.write(str + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new line of task into the file.
     *
     * @param t Task to be stored.
     */
    public void addTask(Task t) {
        try {
            File file = new File(path);
            String str = '\n' + t.getType() + ", " + t.isDone() + ", " + t.getDescription();
            //@@author hsiaotingluv-reused
            //Reused from https://stackoverflow.com/a/37674446 with minor modifications
            Files.write(file.toPath(), str.getBytes(), StandardOpenOption.APPEND);
            //@@author
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a line of task from the file.
     *
     * @param t Task to be deleted.
     */
    public void deleteTask(Task t) {
        try {
            File file = new File(path);
            String str = t.getType() + ", " + t.isDone() + ", " + t.getDescription();
            //@@author hsiaotingluv-reused
            //Reused from https://stackoverflow.com/a/45784174 with minor modifications
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(str))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            //@@author

            //@@author hsiaotingluv-reused
            //Reused from https://stackoverflow.com/a/35811028
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            byte b;
            long length = randomAccessFile.length();
            if (length != 0) {
                do {
                    length -= 1;
                    randomAccessFile.seek(length);
                    b = randomAccessFile.readByte();
                } while (b != 10 && length > 0);
                randomAccessFile.setLength(length);
                randomAccessFile.close();
            }
            //@@author

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates status of a task in the file.
     *
     * @param t Task to be updated.
     */
    public void updateTask(Task t) {
        Path path = Paths.get(Storage.path);
        String original = t.getType() + ", false, " + t.getDescription();
        String newLine = t.getType() + ", true, " + t.getDescription();
        //@@author hsiaotingluv-reused
        //Reused from https://stackoverflow.com/a/67385356 with minor modifications
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            // Do the line replace
            List<String> list = stream.map(line -> line.equals(original) ? newLine : line)
                    .collect(Collectors.toList());
            // Write the content back
            Files.write(path, list, StandardCharsets.UTF_8);
            //@@author
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
