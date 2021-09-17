package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Storage {

    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    /**
     * Constructor for Storage object.
     */
    public Storage() {
    }

    /**
     * Loads the TaskList stored in text format in the user's hard drive when Duke is run.
     *
     * @return Arraylist of task objects loaded from the file stored on the hard drive.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            File directory = new File(DIR_NAME);
            // If directory not found, create directory
            directory.mkdir();
            File data = new File(DIR_NAME, FILE_NAME);
            // If file not found, create file
            data.createNewFile();
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                String[] taskDetails = task.split("\\|");
                initialiseTask(taskDetails, listOfTasks);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return listOfTasks;
    }

    /**
     * Initialise Task object and adds it to the arraylist.
     *
     * @param taskDetails information on a particular task.
     * @param listOfTasks ArrayList of tasks to be returned by load().
     */
    private void initialiseTask(String[] taskDetails, ArrayList<Task> listOfTasks) {
        Task t;
        switch (taskDetails[0].trim()) {
        case "T": {
            t = new ToDo(taskDetails[2].trim());
            break;
        }
        case "D": {
            t = new Deadline(taskDetails[2].trim(), taskDetails[3].trim());
            break;
        }
        case "E": {
            t = new Event(taskDetails[2].trim(), taskDetails[3].trim());
            break;
        }
        default: {
            t = new ToDo("");
            break;
        }
        }
        if (Integer.parseInt(taskDetails[1].trim()) == 1) {
            t.completeItem();
        }
        listOfTasks.add(t);
    }

    /**
     * Updates the txt file containing the list of tasks.
     *
     * @param l current TaskList that would be saved in the txt file.
     */
    public static void updateFile(TaskList l) {
        try {
            String fullPathName = DIR_NAME + "/" + FILE_NAME;
            FileWriter writer = new FileWriter(fullPathName, false);
            writer.write(l.format());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
