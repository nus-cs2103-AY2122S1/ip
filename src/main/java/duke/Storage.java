package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

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
            Ui.displayContent("Can't save the tasks");
        }
    }

    /**
     * Load the saved task list, if the task list does not exist, then raise an exception
     * 
     * @return Saved task list. 
     * @throws IOException Task list does not exist.
     */
    public static ArrayList<Task> loadTaskListFromHardDisk() throws IOException {
        File dir = new File(DIRECTORY);
        File logs = new File(FILEPATH);
        ArrayList<Task> result = new ArrayList<>();
        try {
            result = parseSavedTaskList(logs);
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
            Ui.displayContent("There's an error with the save file, the saved task list is deleted");
        }
        return result;
    }

    private static ArrayList<Task> parseSavedTaskList(File logs)
            throws FileNotFoundException, DukeCorruptedSaveException {
        Scanner sc = new Scanner(logs);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String[] currentLine = sc.nextLine().split(" ; ");
            switch (currentLine[0].trim()) {
            case "T": {
                Task temp = new ToDo(currentLine[2].trim());
                if (currentLine[1].equals("1")) {
                    temp.markAsDone();
                }
                tasks.add(temp);
                break;
            }
            case "D": {
                Task temp = new Deadline(currentLine[2].trim(), LocalDate.parse(currentLine[3]));
                if (currentLine[1].equals("1")) {
                    temp.markAsDone();
                }
                tasks.add(temp);
                break;
            }
            case "E": {
                Task temp = new Event(currentLine[2].trim(), LocalDate.parse(currentLine[3].trim()));
                if (currentLine[1].equals("1")) {
                    temp.markAsDone();
                }
                tasks.add(temp);
                break;
            }
            default:
                throw new DukeCorruptedSaveException("The save file is corrupted!");
            }
        }
        return tasks;
    }
}
