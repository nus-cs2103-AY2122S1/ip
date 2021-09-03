package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class that encapsulates the saving/reading data for Duke.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage object initialised with the filepath
     * to save/read the data to/from.
     *
     * @param filePath The String representation of the filepath to the text file
     *                 containing the saved data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the data of the task in the list into a text file formatted.
     *
     * @param list The TaskList containing the tasks that is to be saved into
     */
    public void saveData(TaskList list) {
        try {
            // get file directory string
            String[] dir = this.filePath.split("/");
            StringBuilder dirString = new StringBuilder();
            for (int i = 0; i < dir.length - 1; i++) {
                dirString.append("/").append(dir[i]);
            }

            String path = new File("").getAbsoluteFile() + dirString.toString();
            File file = new File(path);

            // create directory if not yet created
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            FileWriter fw = new FileWriter(file + "/" + dir[dir.length - 1]);

            list.iterateList(x -> {
                try {
                    fw.write(x.toData() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from the saved file if it exists to update Duke on running.
     */
    public TaskList readData() {
        try {
            // set-up to check if file exists
            TaskList list = new TaskList();
            String path = new File("").getAbsoluteFile() + this.filePath;
            File file = new File(path);

            if (!file.isFile()) {
                return list;
            }

            // read and update TaskList
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] inputArray = s.nextLine().split(" \\| ");
                Task.TaskName type = Task.TaskName.getTaskType(inputArray[0]);

                list.addTask(type, inputArray[2] + type.getSplit()
                                + (type != Task.TaskName.TODO ? inputArray[3] : ""),
                        inputArray[1].equals("1"));
            }
            return list;
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }
}
