package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that manages the storage of the tasks.
 */
public class FileManager {
    private File taskList;

    /**
     * Makes new Filemanager which manages a file with the name inputted.
     *
     * @param filename name of file which will be managed.
     */
    public FileManager(String filename) {
        this.taskList = new File(filename);
    }

    /**
     * Gets the current stored tasklist in the file.
     *
     * @return the tasklist stored in the file.
     */
    public ArrayList<Task> getTaskList() {
        try {
            if (taskList.createNewFile()) {
                return new ArrayList<>();
            } else {
                Scanner fileReader = new Scanner(taskList);
                ArrayList<Task> tasks = new ArrayList<>();
                while (fileReader.hasNextLine()) {
                    try {
                        String nextLine = fileReader.nextLine();
                        String[] splitString = nextLine.split(Task.sep);
                        if (splitString.length < 3) {
                            throw new DukeException("Invalid input in file");
                        }
                        Task newTask = Task.makeTask(splitString[0], splitString[1]);
                        if (splitString[2].equals("1")) {
                            newTask.markDone();
                        }
                        tasks.add(newTask);
                    } catch (DukeException e) {
                        System.out.println("Invalid input in file");
                    }
                }
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Error occured initalising file");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the stored tasks according to tasks inputted.
     *
     * @param tasks the updated tasklist.
     * @param ui the user interface to interact with the user.
     */
    public void updateTaskList(Tasklist tasks, Ui ui) {
        try {
            FileWriter newfileWriter = new FileWriter(this.taskList);
            newfileWriter.write(tasks.stringSaveFile());
            newfileWriter.close();
        } catch (IOException e) {
            ui.showError(e);
        }
    }
}
