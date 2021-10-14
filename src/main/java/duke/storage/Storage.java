package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.data.TaskList;
import duke.exception.DukeException;
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
    public TaskList loadData() throws IOException, DukeException {
        File file = getFile(path);
        tasks = new TaskList();

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            Character taskType = getTaskType(s);
            boolean isDone = getTaskStatus(s);
            String taskName = getTaskName(s);

            switch (taskType) {
            case 'T':
                ToDo todo = new ToDo(taskName, isDone);
                tasks.addTask(todo);
                break;
            case 'D':
                LocalDateTime by = getDateTime(s);
                Deadline deadline = new Deadline(taskName, by, isDone);
                tasks.addTask(deadline);
                break;
            case 'E':
                LocalDateTime at = getDateTime(s);
                Event event = new Event(taskName, at, isDone);
                tasks.addTask(event);
                break;
            default:
                throw new DukeException();
            }
        }
        sc.close();
        return tasks;
    }

    private File getFile(String path) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private Character getTaskType(String s) {
        return s.charAt(0);
    }

    private String getTaskName(String s) {
        String[] arr = s.split("/");
        return arr[2];
    }

    private boolean getTaskStatus(String s) {
        String[] arr = s.split("/");
        return arr[1].equals("1");
    }

    private LocalDateTime getDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] arr = s.split("/");
        assert arr.length == 4;
        return LocalDateTime.parse(arr[3], formatter);
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
