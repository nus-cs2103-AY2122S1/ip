package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new ArrayList<>();
    }

    /**
     * Reads from the storage file and return an ArrayList of tasks.
     *
     * @return An ArrayList of Task objects from the storage file.
     * @throws DukeException If there is an error in creating the storage file.
     */
    public ArrayList<Task> load() throws DukeException {
        taskList.clear();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String item = myReader.nextLine();
                parseStorageLine(item);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating new file with given path.");
            try {
                File myObj = new File(filePath);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ioe) {
                throw new DukeException("Error occurred while creating the file");
            }
        }
        return this.taskList;
    }

    /**
     * Reads a line as stored in the storage and adds the corresponding task
     * to the task list.
     *
     * @throws DukeException If there is an error in recognising the type of task
     */
    private void parseStorageLine(String item) throws DukeException {
        String[] splitItem = item.split("\\|");
        if (splitItem.length < 2) {
            return;
        }
        String taskType = splitItem[0];
        String completed = splitItem[1];
        String desc = splitItem[2];
        String date = "";
        if (splitItem.length > 3) {
            date = splitItem[3];
        }
        Task nextTask = null;
        switch (taskType) {
        case ("T"):
            nextTask = new Todo(desc);
            break;
        case ("D"):
            nextTask = new Deadline(desc, date);
            break;
        case ("E"):
            nextTask = new Event(desc, date);
            break;
        default:
            throw new DukeException("Unrecognised task type");
        }
        if (completed.equals("1") && nextTask != null) {
            nextTask.markAsDone();
        }
        taskList.add(nextTask);
    }

    /**
     * Writes a task to the storage file.
     *
     * @param task Task object to add to the storage file.
     * @throws DukeException If the file path is invalid.
     */
    public void add(Task task) throws DukeException {
        String newString = "\n" + task.toStorageString();
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.append(newString);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving new task");
        }
    }

    /**
     * Rewrite the storage file to match the content of the given TaskList.
     *
     * @param tl TaskList object to read from.
     * @throws DukeException If the file path is invalid.
     */
    public void rewrite(TaskList tl) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(tl.toStorageString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error deleting or marking task as done");
        }
    }
}
