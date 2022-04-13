package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage implements Cloneable {
    private static String filePath;
    private static final String DATA_FOLDER_PATH = "./data";
    private boolean shouldExit = false;

    /**
     * Constructs Storage.
     *
     * @param filePath The filepath to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks in a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    public void save(TaskList tasks) throws IOException {
        Path dataFolderPath = Paths.get(DATA_FOLDER_PATH);
        if (Files.notExists(dataFolderPath)) {
            dataFolderPath = Files.createDirectory(dataFolderPath);
        }

        FileWriter fw = new FileWriter(filePath);
        loadTasks(tasks, fw);
        fw.close();
    }

    /**
     * Loads the given file of tasks.
     *
     * @return The list of tasks.
     * @throws FileNotFoundException If file cannot be located.
     */
    public TaskList load() throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        TaskList tasks = new TaskList();
        while (s.hasNext()) {
            String command = s.nextLine();
            findAndAddTask(command, tasks);
        }
        return tasks;
    }

    /**
     * Checks if a program should end.
     *
     * @return True if program should end, false otherwise.
     */
    public boolean isExit() {
        return shouldExit;
    }

    /**
     * Sets boolean deciding whether a program should end.
     */
    public void setExit() {
        shouldExit = true;
    }

    public Storage clone() throws CloneNotSupportedException {
        return (Storage) super.clone();
    }

    /**
     * Checks the parsed String and finds out what type
     * of task it is.
     *
     * @param command Parsed String.
     * @param tasks The TaskList of tasks to be loaded.
     */
    public void findAndAddTask(String command, TaskList tasks) {
        String activity = command.substring(0, 1);
        String status = command.substring(4, 5);
        String desc;
        String date;

        if (activity.equals("T")) {
            desc = command.substring(8);
            ToDo todo = new ToDo(desc);
            if (status.equals("1")) {
                todo.setDone();
            }
            tasks.add(todo);
        } else {
            int thirdBarId = command.indexOf('|', 7);
            desc = command.substring(8, thirdBarId - 1);
            date = command.substring(thirdBarId + 2);
            if (activity.equals("D")) {
                Deadline deadline = new Deadline(desc, date);
                if (status.equals("1")) {
                    deadline.setDone();
                }
                tasks.add(deadline);
            } else {
                Event event = new Event(desc, date);
                if (status.equals("1")) {
                    event.setDone();
                }
                tasks.add(event);
            }
        }
    }

    /**
     * Loads the tasks previously written into FileWriter.
     *
     * @param tasks The tasks to be loaded.
     * @param fw FileWriter to be loaded into
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    public void loadTasks(TaskList tasks, FileWriter fw) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.getDone() ? "1" : "0";
            String date;
            String activity;
            String information;
            if (task instanceof Event) {
                activity = "E";
                information = task.getDescription();
                date = ((Event) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n", activity, status, information, date);
                fw.write(desc);
            } else if (task instanceof Deadline) {
                activity = "D";
                information = task.getDescription();
                date = ((Deadline) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n", activity, status, information, date);
                fw.write(desc);
            } else if (task instanceof ToDo) {
                activity = "T";
                information = task.getDescription();
                String desc = String.format("%s | %s | %s\n", activity, status, information);
                fw.write(desc);
            }
        }
    }
}
