package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Stores the list of tasks into a given text file and loads the
 * same file whenever the program starts up.
 */
public class Storage {

    private String filePath;
    private File file;

    /**
     * The constructor for a Storage object.
     *
     * @param filePath The path to the file for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and reads the text file and returns an ArrayList of the
     * entries converted into Tasks.
     *
     * @return An ArrayList with Tasks from the text file.
     * @throws DukeException If an error occurs when loading the file.
     */
    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> list = new ArrayList<>();

        file = new File(filePath);

        try {
            if (!file.exists()) {
                File directory = new File("data");

                directory.mkdir();
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                list.add(parseInput(str));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found. Here is your error message: " + e.getMessage());
        }

        return list;
    }

    /**
     * Updates the text file whenever an update is made to the task list.
     *
     * @param tasks The given TaskList.
     * @throws DukeException If there is an writing to the file.
     */
    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                bw.write(task.toFileFormat());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            throw new DukeException("File not updated properly. Here is your error message: " + e.getMessage());
        }
    }

    /**
     * Parses the input from the text file into arguments
     * for Tasks to be created.
     *
     * @param str String input from the text file.
     * @return Task from the string input.
     * @throws DukeException When the file is corrupted and the string cannot be parsed.
     */
    private static Task parseInput(String str) throws DukeException {

        String[] taskArr = str.split(" \\| ");

        switch (taskArr[0]) {
        case "T":
            return parseTodo(taskArr);
        case "D":
            return parseDeadline(taskArr);
        case "E":
            return parseEvent(taskArr);
        default:
            throw new DukeException("File corrupted. Please create a new file or check your existing file.");
        }
    }

    private static Task parseTodo(String[] strArr) {
        Todo todo = new Todo(strArr[2]);

        if (strArr[1].equals("1")) {
            todo.setDone();
        }

        return todo;
    }

    private static Task parseDeadline(String[] strArr) {
        Deadline deadline = new Deadline(strArr[2], LocalDate.parse(strArr[3]));

        if (strArr[1].equals("1")) {
            deadline.setDone();
        }

        return deadline;
    }

    private static Task parseEvent(String[] strArr) {
        Event event = new Event(strArr[2], LocalDate.parse(strArr[3]));

        if (strArr[1].equals("1")) {
            event.setDone();
        }

        return event;
    }
}
