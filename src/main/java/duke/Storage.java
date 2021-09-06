package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     * A method to load tasks from storage and return the list of tasks.
     *
     * @return an ArrayList of Task
     * @throws DukeException if file cannot be loaded
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File storage = new File("data/duke.txt");
            storage.createNewFile();
            Scanner savedTasks = new Scanner(storage);

            ArrayList<Task> tasks = new ArrayList<>();

            while (savedTasks.hasNextLine()) {
                String storedTask = savedTasks.nextLine();
                parseStoredTask(storedTask, tasks);
            }

            return tasks;

        } catch (IOException e) {
            throw new DukeException("There is a problem loading saved data.");
        }
    }

    private void parseStoredTask(String storedTask, ArrayList<Task> tasks) {
        if (storedTask.startsWith("[T]")) {
            Task currentTask = new ToDo(storedTask.substring(7));
            tasks.add(currentTask);
        } else if (storedTask.startsWith("[E]")) {
            int at = storedTask.lastIndexOf(" (at: ");
            int end = storedTask.lastIndexOf(")");
            assert at != -1;
            assert end != -1;
            Task currentTask = new Event(
                    storedTask.substring(7, at),
                    LocalDateTime.parse(storedTask.substring(at + 6, end),
                            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
            );
            tasks.add(currentTask);
        } else if (storedTask.startsWith("[D]")) {
            int by = storedTask.lastIndexOf(" (by: ");
            int end = storedTask.lastIndexOf(")");
            assert by != -1;
            assert end != -1;
            Task currentTask = new Deadline(
                    storedTask.substring(7, by),
                    LocalDateTime.parse(storedTask.substring(by + 6, end),
                            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
            );
            tasks.add(currentTask);
        }
    }

    /**
     * A method to continuously save the current list of tasks.
     *
     * @param tasks a TaskList to be saved
     */
    public void saveTasks(TaskList tasks) {
        String textToAdd = "";

        for (int i = 0; i < tasks.numOfTasks(); i++) {
            textToAdd = textToAdd + tasks.getTask(i).toString() + System.lineSeparator();
        }

        try {
            FileWriter file = new FileWriter("data/duke.txt");
            file.write(textToAdd);
            file.close();
        } catch (IOException e) {
            System.out.println("There is a problem writing saved data.");
        }
    }
}
