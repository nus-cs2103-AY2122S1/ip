package duke.storage;

import duke.gui.TextUi;
import duke.exception.DukeCorruptedSaveException;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.ToDo;
import duke.logic.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String DIRECTORY = "src/data";
    private static final String FILEPATH = "src/data/duke.txt";

    /**
     * Save the task list to a location on the hard disk
     *
     * @param tasks The task list to be saved.
     */
    public static void saveTaskListToHardDisk(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            if (tasks.size() == 0) {
                fileWriter.write("");
            } else {
                for (Task t : tasks) {
                    fileWriter.write(t.toSaveInHardDisk() + System.getProperty("line.separator"));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            TextUi.display("Can't save the duke.logic.tasks");
        }
    }

    /**
     * Load the saved task list, if the task list does not exist, then raise an duke.exception
     *
     * @return Saved task list.
     * @throws IOException Task list does not exist.
     */
    public static ArrayList<Task> loadTaskListFromHardDisk() throws IOException {
        File dir = new File(DIRECTORY);
        File logs = new File(FILEPATH);
        ArrayList<Task> results = new ArrayList<>();
        try {
            results = parseSavedTaskList(logs);
        } catch (FileNotFoundException e) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!logs.exists()) {
                logs.createNewFile();
            }
        } catch (DukeCorruptedSaveException e) {
            logs.delete();
            logs.createNewFile();
            TextUi.display("There's an error with the save file, the saved task list is deleted");
        }
        return results;
    }

    private static ArrayList<Task> parseSavedTaskList(File logs)
            throws FileNotFoundException, DukeCorruptedSaveException {
        Scanner sc = new Scanner(logs);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String[] currentLine = sc.nextLine().split(" ; ");
            switch (currentLine[0].trim()) {
            case "T": {
                handleTodo(tasks, currentLine);
                break;
            }
            case "D": {
                handleDeadline(tasks, currentLine);
                break;
            }
            case "E": {
                handleEvent(tasks, currentLine);
                break;
            }
            default:
                throw new DukeCorruptedSaveException("The save file is corrupted!");
            }
        }
        return tasks;
    }

    private static void handleEvent(ArrayList<Task> tasks, String[] currentLine) {
        Task temp;
        if (currentLine.length == 4) {
            temp = new Event(currentLine[2].trim(), "",
                    LocalDate.parse(currentLine[3]));
        } else {
            temp = new Event(currentLine[2].trim(),
                    currentLine[4].trim(), LocalDate.parse(currentLine[3]));
        }
        if (currentLine[1].equals("1")) {
            temp.markAsDone();
        }
        tasks.add(temp);
    }

    private static void handleDeadline(ArrayList<Task> tasks, String[] currentLine) {
        Task temp;
        if (currentLine.length == 4) {
            temp = new Deadline(currentLine[2].trim(), "", 
                    LocalDate.parse(currentLine[3]));
        } else {
            temp = new Deadline(currentLine[2].trim(), 
                    currentLine[4].trim(), LocalDate.parse(currentLine[3]));
        }

        if (currentLine[1].equals("1")) {
            temp.markAsDone();
        }
        tasks.add(temp);
    }

    private static void handleTodo(ArrayList<Task> tasks, String[] currentLine) {
        Task temp;
        if (currentLine.length == 3) {
            temp = new ToDo(currentLine[2].trim(), "");
        } else {
            temp = new ToDo(currentLine[2].trim(), currentLine[3].trim());
        }
        if (currentLine[1].equals("1")) {
            temp.markAsDone();
        }
        tasks.add(temp);
    }
}
