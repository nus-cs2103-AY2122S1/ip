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
    private ArrayList<Task> toStore;
    private final String FILEPATH = "data" + File.separator + "history.txt";
    private final String DIRPATH = "data";

    /**
     * Constructor for Storage.
     */
    public Storage() {
        //        this.FILE_PATH = "data" + File.separator + "history.txt";
        //        this.DIR_PATH = "data";
    }

    /**
     * Initialises the initial ArrayList for Duke.
     *
     * @return ArrayList to be passed into TaskList for Duke.
     */
    public ArrayList<Task> initialise() {
        //read from the data/history.text and return an ArrayList of Tasks
        File file = new File(FILEPATH);

        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] splitTask = task.split("\\|");

                if (splitTask.length == 3) {
                    // it is todotask
                    Task toAdd = new ToDo(splitTask[2]);

                    if (splitTask[1].equals("1")) {
                        toAdd.markAsDone();
                    }

                    output.add(toAdd);
                } else {
                    // can be event or deadline
                    if (splitTask[0].equals("E")) {
                        // event
                        Task toAdd = new Event(splitTask[2], splitTask[3]);

                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }

                        output.add(toAdd);
                    } else {
                        // deadline
                        Task toAdd = new Deadline(splitTask[2], splitTask[3]);

                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }

                        output.add(toAdd);
                    }
                }
            }

            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the taskList given by Duke into user's computer.
     *
     * @param tasks taskList which is being saved into user's computer.
     */
    public void saveFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);

            String textToAdd = "";

            for (int i = 0; i < tasks.getSize(); i++) {
                if (i == 0) {
                    textToAdd += tasks.taskSaveToString(i);
                } else {
                    textToAdd += "\n" + tasks.taskSaveToString(i);
                }
            }

            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(DIRPATH);

            if (file.mkdir()) {
                saveFile(tasks);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }
}
