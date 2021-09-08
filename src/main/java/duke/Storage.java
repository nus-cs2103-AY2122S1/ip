package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

        file = new File(System.getProperty("user.dir") + "\\" + filePath);

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
            FileWriter fw = new FileWriter(file);
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
        Task newTask;

        switch (taskArr[0]) {
        case "T":
            Todo todo = new Todo(taskArr[2]);

            if (taskArr[1].equals("1")) {
                todo.setDone();
            }

            newTask = todo;
            break;
        case "E":
            Event event = new Event(taskArr[2], LocalDate.parse(taskArr[3]));

            if (taskArr[1].equals("1")) {
                event.setDone();
            }

            newTask = event;
            break;
        case "D":
            Deadline deadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3]));

            if (taskArr[1].equals("1")) {
                deadline.setDone();
            }

            newTask = deadline;
            break;
        default:
            throw new DukeException("File corrupted. Please create a new file or check your existing file.");
        }

        return newTask;
    }
}
