package duke.general;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> storedList;

    /**
     * Constructs the Storage object meant for the saving and loading of files
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        storedList = new ArrayList<>();
    }

    /**
     * Load tasks stored from the save file to load back the saved task list.
     * @return ArrayList of tasks that was previously saved.
     */
    public ArrayList<Task> loadSave() {
        try {
            Files.createDirectories(Paths.get("data/"));
            File f = new File("data/duke.txt");

            if (f.createNewFile()) {
                System.out.println("No save file found. New file created.");
            } else {
                System.out.println("Save file found and restored");
                loadSaveAdder(new Scanner(f));
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return this.storedList;
    }

    /**
     * Helper function for loadSave above to take in lines from save file and add it to the storedList
     * @param s Scanner object created with the save file
     */
    private void loadSaveAdder(Scanner s) {
        while (s.hasNext()) {
            String[] taskLine = s.nextLine().split("/");
            switch (taskLine[0]) {
            case "T":
                addToList(TaskType.TODO, taskLine);
                break;
            case "D":
                addToList(TaskType.DEADLINE, taskLine);
                break;
            case "E":
                addToList(TaskType.EVENT, taskLine);
                break;
            default:
                System.out.println("Unexpected value obtained: " + taskLine[0]);
            }
        }
    }

    /**
     * Takes tasks in the saved file format and adds it to the storedList
     * @param t Type of task being added
     * @param line String[] containing data about the task
     */
    public void addToList(TaskType t, String[] line) {
        Task temp;
        switch (t) {
        case TODO:
            temp = new ToDo(line[2]);
            break;
        case DEADLINE:
            temp = new Deadline(line[2], line[3]);
            break;
        case EVENT:
            temp = new Event(line[2], line[3]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + t);
        }
        if (Objects.equals(line[1], "X")) {
            temp.toggleCompleted();
        }
        this.storedList.add(temp);
    }

    /**
     * Saves the task added into the save file
     * @param t duke.task.Task to be saved into save file
     */
    public void appendSave(Task t) {
        assert(t != null) : "Task does not even exist to append!";

        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            FileWriter fw = new FileWriter(filePath, true);
            fw.write((sc.hasNextLine() ? System.lineSeparator() : "")
                    + t.getType() + "/" + t.getCompletion() + "/" + t.getName()
                    + (t.getType().equals("T") ? "" : "/" + t.getInfo()));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the save file by wiping the duke.txt and adding back all the tasks from the
     * newList into duke.txt
     * @param newList The new task list after modifications (delete, done)
     */
    public void updateSave(ArrayList<Task> newList) {
        assert(newList != null) : "List of tasks needs to exist before adding";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : newList) {
            appendSave(task);
        }
    }
}
