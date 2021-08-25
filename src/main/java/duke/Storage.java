package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String deliminator = "::";
    private final File file;
    private final List<Task> tasks = new ArrayList<>();
    private final String filePath;
    private String status;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * load the file and parse into a list of task.
     *
     * @return the list of task for display
     * @throws FileNotFoundException thrown when a file is not found
     * @throws FileFormatException   thrown when the file is wrongly formatted
     */
    public List<Task> load() throws LoadingException, FileFormatException {
        parseFile(file);
        return tasks;
    }

    private void parseFile(File file) throws LoadingException, FileFormatException {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = parseLine(sc.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new LoadingException();
        }

    }

    private Task parseLine(String string) throws FileFormatException {
        Task task;
        String[] strings = string.split(deliminator);
        try {
            String type = strings[0];
            boolean isDone = strings[1].equals("1");
            String taskDescription = strings[2];
            String time;
            switch (type) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                time = strings[3];
                task = new Deadline(taskDescription, LocalDate.parse(time));
                break;
            case "E":
                time = strings[3];
                task = new Event(taskDescription, LocalDate.parse(time));
                break;
            default:
                throw new FileFormatException();
            }

            if (isDone) {
                task.setDone();
            }

            return task;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new FileFormatException();
        }
    }

    /**
     * This will overwrite the file at the specified path with the provided tasks.
     *
     * @param tasks a List of task to overwrite to the file
     * @throws IOException thrown during writing
     */
    public void write(List<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get("data"));
        file.createNewFile();
        FileWriter fw = new FileWriter(filePath);
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            String taskString = String.join(deliminator, task.saveStrings()) + "\n";
            text.append(taskString);
        }
        fw.write(text.toString());
        fw.close();
    }
}
