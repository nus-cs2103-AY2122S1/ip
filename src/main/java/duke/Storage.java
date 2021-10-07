package duke;

import duke.exception.DukeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Reads data from the file to load all the current tasks.
     *
     * @return An ArrayList of tasks stored int the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            if (!file.createNewFile()) {
                // if file already exists, read from it
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String taskData = myReader.nextLine();
                    try {
                        // get Tasks from the file and add them to the list
                        Task currTask = Task.readTaskFromFile(taskData);
                        list.add(currTask);
                    } catch (DukeException e) {
                        // file data is in the wrong format, cannot be read
                        System.err.println(e.getMessage());
                        System.exit(-1);
                    }
                }
            }
        } catch (Exception e) {
            // exception when creating file
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        return list;
    }

    /**
     * Saves all the tasks in a given TaskList to the file.
     *
     * @param taskList The TaskList whose data is to be saved.
     */
    public void save(TaskList taskList) {
        ArrayList<Task> list = taskList.getTaskList();
        try {
            BufferedWriter myWriter =
                    new BufferedWriter(new FileWriter(filePath));

            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    myWriter.newLine();
                }
                myWriter.append(list.get(i).toString());
            }
            myWriter.close();

        } catch (IOException e) {
            System.err.println("Oh no! An error occurred while writing to the file.");
            System.exit(-1);
        }
    }
}
