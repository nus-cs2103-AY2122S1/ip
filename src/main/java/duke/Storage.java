package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class represents a {@code Storage} to read and write to files.
 *
 * @author Elizabeth Chow
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new {@code Storage} with the given filePath.
     *
     * @param filePath File path of the txt file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads file from the file path and returns a list of {@code Task} in the file.
     *
     * @return An ArrayList of {@code Task}
     * @throws DukeException unknown input when parsing file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            int lineCount = 0;
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                lineCount++;
                tasks.add(handleFileInput(scanner.nextLine(), lineCount));
            }
        } catch (FileNotFoundException e) {
            createNewFile();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tasks;
    }

    private Task handleFileInput(String input, int lineNo) throws DukeException {
        try {
            String fileSplitRegex = " \\| ";
            String action = input.split(fileSplitRegex)[0];
            boolean isDone = convertIsDoneStrToBool(input.split(fileSplitRegex)[1]);
            String title = input.split(fileSplitRegex)[2];
            switch (action) {
            case "T":
                return new Todo(title, isDone);
            case "D":
                String endDate = input.split(fileSplitRegex)[3];
                return new Deadline(title, endDate, isDone);
            case "E":
                String deadline = input.split(fileSplitRegex)[3];
                return new Event(title, deadline, isDone);
            default:
                throw new DukeException(String.format(
                        "Unknown action type at line %d. " + "Only \"T\", \"D\", \"E\" are accepted.", lineNo));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Line %d is missing input values!", lineNo));
        }

    }

    /**
     * Writes to file at the specified file path with the given tasks.
     *
     * @param tasks Tasks to be written to be file.
     * @throws DukeException IOException occured when writing to file.
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(filePath));
            for (Task task : tasks.getTasks()) {
                printWriter.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private boolean convertIsDoneStrToBool(String s) throws DukeException {
        if (s.equals("1")) {
            return true;
        } else if (s.equals("0")) {
            return false;
        } else {
            throw new DukeException("Invalid input for isDone. Only 0 or 1 is accepted.");
        }
    }

    private void createNewFile() throws DukeException {
        try {
            File dir = new File("./data");
            dir.mkdirs();
            File file = new File(dir, "duke.txt");
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to create new file!");
        }
    }
}
