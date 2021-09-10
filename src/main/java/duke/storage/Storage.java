package duke.storage;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.parser.Parser;
import duke.tasks.Deadline;

import java.io.*;

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

/**
 * Represents a storage that stores the task data locally.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Storage {
    private static String PATH;
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * A constructor for Storage.
     *
     * @param filePath Input path for the data file.
     */
    public Storage(String filePath) {
        this.PATH = filePath;
    }

    /**
     * Creates file path if input file does not exist.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public void checkFile() throws IOException {
        final Path p = Paths.get(PATH);
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
            File f = new File(PATH);
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
}