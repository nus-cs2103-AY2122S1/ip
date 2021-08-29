package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class that defines the storage for the data needed for Duke
 *
 */
public class DukeStorage {

    /** The destination of the stored data */
    public String path;

    /**
     * Constructor that initializes the storage for Duke
     *
     * @param path The destination of the stored data
     */
    public DukeStorage(String path) {
        this.path = path;
    }

    /**
     * Method to read tasks from storage
     *
     * @return List of tasks read from storage
     * @throws DukeException Error thrown if file cannot be read
     */
    public TaskList readTasks() throws DukeException {
        File file = new File(this.path);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                TaskList taskList = new TaskList();

                String data = "";
                while(sc.hasNext()) {
                    data += sc.nextLine() + "\n";
                }

                if (data.equals("")){
                    sc.close();
                    return new TaskList();
                }

                String[] strArray = data.split("\n");

                for (String str : strArray) {
                    String[] parseTask = str.split(" \\| ");

                    String taskType = parseTask[0];
                    boolean isDone = parseTask[1].equals("1") ? true : false;
                    String taskDescr = parseTask[2];

                    if (taskType.equals("T")) {
                        Todo task = new Todo(taskDescr);
                        if (isDone) {
                            task.setDone();
                        }

                        taskList.add(task);
                    } else if (taskType.equals("D")) {
                        Deadline task = new Deadline(taskDescr, parseTask[3]);
                        if (isDone) {
                            task.setDone();
                        }

                        taskList.add(task);
                    } else {
                        Event task = new Event(taskDescr, parseTask[3]);
                        if (isDone) {
                            task.setDone();
                        }

                        taskList.add(task);
                    }
                }
                sc.close();
                return taskList;
            } catch (FileNotFoundException e) {
                throw new DukeException("File error! Please try again.");
            }
        } else {
            try {
                file.createNewFile();
                return new TaskList();
            } catch (IOException e) {
                throw new DukeException("File error! Please try again.");
            }
        }
    }

    /**
     * Method to write task data into storage
     *
     * @param taskList The list of tasks to be written
     * @throws DukeException Error thrown if file cannot be written
     */
    public void writeTasks(TaskList taskList) throws DukeException {
        File file = new File(this.path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            String data = "";
            for (int i = 0; i < taskList.size(); i++) {
                data += taskList.get(i).getFileString() + "\n";
            }

            FileWriter w = new FileWriter(file);
            w.write(data);
            w.close();
        } catch (IOException e) {
            throw new DukeException("File error! Please try again.");
        }
    }
}