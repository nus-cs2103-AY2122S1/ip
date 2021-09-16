package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.data.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with loading tasks from file and saving tasks in file
 */
public class Storage {

    private final String path;
    private TaskList tasks;

    /**
     * Constructs Storage object
     *
     * @param path file location
     */
    public Storage(String path) {
        this.path = path;
        this.tasks = new TaskList();
    }

    /**
     * Loads data from file
     *
     * @return list of tasks
     * @throws IOException
     */
    public TaskList loadData() throws IOException {
        File file = new File(path);
        tasks = new TaskList();

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
        } else {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();

                String[] arr = s.split("/");
                boolean isDone = (arr[1].equals("1"));
                String name = arr[2];

                if (s.contains("T")) {
                    ToDo todo = new ToDo(name, isDone);
                    tasks.addTask(todo);
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                if (s.contains("D")) {
                    LocalDateTime by = LocalDateTime.parse(arr[3], formatter);
                    Deadline deadline = new Deadline(name, by, isDone);
                    tasks.addTask(deadline);
                }

                if (s.contains("E")) {
                    LocalDateTime at = LocalDateTime.parse(arr[3], formatter);
                    Event event = new Event(name, at, isDone);
                    tasks.addTask(event);
                }
            }
            System.out.println(System.lineSeparator());
            sc.close();
        }
        return tasks;
    }

    /**
     * Updates file when tasks are added or deleted
     *
     * @param tasks current list of tasks
     */
    public void updateData(TaskList tasks) {
        try {
            this.tasks = tasks;
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.toStringInStorage() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("( ⚆ _ ⚆ ) OOPS!!! Something went wrong while uploading data!");
        }
    }
}
