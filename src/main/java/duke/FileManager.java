package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.task.Task;



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
                return getTasks(taskList);
            }
        } catch (IOException e) {
            System.out.println("Error occured initalising file");
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<Task> getTasks(File taskList) throws FileNotFoundException {
        Scanner fileReader = new Scanner(taskList);
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            try {
                getOneTask(fileReader, tasks);
            } catch (DukeException e) {
                System.out.println("Invalid input in file");
            }
        }
        return tasks;
    }

    private static void getOneTask(Scanner fileReader, ArrayList<Task> tasks) throws DukeException {
        String nextLine = fileReader.nextLine();
        String[] splitString = nextLine.split(Task.SEP);
        if (splitString.length < 4) {
            assert false : "Invalid input in file";
        }
        Task newTask = Task.makeTask(splitString[0], splitString[1]);
        if (splitString[2].equals("1")) {
            newTask.markDone();
        }
        newTask.addTags(FileManager.getTags(splitString[3]));
        tasks.add(newTask);
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

    private static ArrayList<String> getTags(String arrayString) {
        String stringWithoutBrackets = arrayString.substring(0, arrayString.length() - 1).substring(1);
        String[] splitByComma = stringWithoutBrackets.split(",");
        ArrayList<String> tags = new ArrayList<>();
        Collections.addAll(tags, splitByComma);
        return tags;
    }
}
