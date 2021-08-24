package duke.general;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private ArrayList<Task> storedList;

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
                Scanner s = new Scanner(f);
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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return this.storedList;
    }

    /**
     * Takes tasks in the saved file format and adds it to the storedList
     * @param t Type of task being added
     * @param line String[] containing data about the task
     */

    //TODO MIGHT BE CRAZY WRONG
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
    public void modifySave(ArrayList<Task> newList) {
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
