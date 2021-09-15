package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and retrieves information of the tasklist for Duke.
 */
public class Storage {
    private final String TASKLIST_FILE_PATH;
    private final String DIR_PATH;
    private final String ARCHIVELIST_FILE_PATH;

    /**
     * Constructor for Storage.
     */
    public Storage() {
        this.TASKLIST_FILE_PATH = "data" + File.separator + "history.txt";
        this.DIR_PATH = "data";
        this.ARCHIVELIST_FILE_PATH = "data" + File.separator + "archive.txt";
    }

    /**
     * Initialises the initial ArrayList for Duke.
     *
     * @return ArrayList to be passed into TaskList for Duke.
     */
    public ArrayList<Task> initialise() {
        //read from the data/history.text and return an ArrayList of Tasks
        File file = new File(TASKLIST_FILE_PATH);

        return readArrayList(file);
    }

    /**
     * Initialises the ArrayList for the archiveList of Duke.
     *
     * @return ArrayList of the archiveList.
     */
    public ArrayList<Task> initialiseArchive() {
        //read from the data/archive.text and return an ArrayList of Tasks
        File file = new File(ARCHIVELIST_FILE_PATH);

        return readArrayList(file);
    }

    /**
     * Reads the given file and generates an array list with the tasks from the file.
     *
     * @param file File to be read from.
     * @return ArrayList with tasks read from the file.
     */
    private ArrayList<Task> readArrayList(File file) {
        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] splitTask = task.split("\\|");

                if (splitTask[0].equals("T")) {
                    // it is todotask
                    initialiseToDo(output, splitTask);
                } else if (splitTask[0].equals("E")) {
                    // event
                    initialiseEvent(output, splitTask);
                } else if (splitTask[0].equals("D")) {
                    // deadline
                    initialiseDeadline(output, splitTask);
                }

            }

            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Initialises a Deadline Object and adds it to an ArrayList.
     *
     * @param tasks ArrayList for the Deadline Object to be added to.
     * @param splitTask Details of the Deadline Object stored in an array.
     */
    private void initialiseDeadline(ArrayList<Task> tasks, String[] splitTask) {
        Task toAdd = new Deadline(splitTask[2], splitTask[3]);

        if (splitTask[1].equals("1")) {
            toAdd.markAsDone();
        }

        tasks.add(toAdd);
    }

    /**
     * Initialises an Event Object and adds it to an ArrayList.
     *
     * @param tasks ArrayList for the Event Object to be added to.
     * @param splitTask Details of the Event Object stored in an array.
     */
    private void initialiseEvent(ArrayList<Task> tasks, String[] splitTask) {
        Task toAdd = new Event(splitTask[2], splitTask[3]);

        if (splitTask[1].equals("1")) {
            toAdd.markAsDone();
        }

        tasks.add(toAdd);
    }

    /**
     * Initialises a ToDo Object and adds it to an ArrayList.
     *
     * @param tasks ArrayList for the ToDo Object to be added to.
     * @param splitTask Details of the ToDo Object stored in an array.
     */
    private void initialiseToDo(ArrayList<Task> tasks, String[] splitTask) {
        Task toAdd = new ToDo(splitTask[2]);

        if (splitTask[1].equals("1")) {
            toAdd.markAsDone();
        }

        tasks.add(toAdd);
    }

    /**
     * Saves the taskList given by Duke into user's computer.
     *
     * @param taskList taskList which is being saved into user's computer.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(TASKLIST_FILE_PATH);

            String textToAdd = convertTaskListToSaveForm(taskList);

            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(DIR_PATH);

            if (file.mkdir()) {
                saveTaskList(taskList);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }

    /**
     * Saves the archiveList given by Duke into user's computer.
     *
     * @param archiveList ArchiveList to be saved into user's computer.
     */
    public void saveArchive(ArchiveList archiveList) {
        try {
            FileWriter fw = new FileWriter(ARCHIVELIST_FILE_PATH);

            String textToAdd = convertTaskListToSaveForm(archiveList);

            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(DIR_PATH);

            if (file.mkdir()) {
                saveArchive(archiveList);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }

    /**
     * Converts dukeList into a text form which can be saved.
     *
     * @param dukeList dukeList to be saved.
     * @return Text form of dukeList to be saved.
     */
    private String convertTaskListToSaveForm(DukeList dukeList) {
        String textToAdd = "";

        for (int i = 0; i < dukeList.getSize(); i++) {
            if (i == 0) {
                textToAdd += dukeList.taskSaveToString(i);
            } else {
                textToAdd += "\n" + dukeList.taskSaveToString(i);
            }
        }

        return textToAdd;
    }
}
