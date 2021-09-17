package dino.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.Todo;
import dino.user.DinoException;
import dino.user.Ui;

/**
 * Represents the storage portion of the Dino program.
 * Handles saving the task list to a file, and loading from the file upon program execution
 */
public class Storage {

    // The file that data is being written to and retrieved from
    private File dataFile;

    // The list of Tasks retrieved from dataFile
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Basic constructor for a Storage object.
     *
     * @param fileName the string of the file name (e.g. "Dino.txt")
     */
    public Storage(String fileName) {
        this.dataFile = new File(fileName);
    }

    /**
     * Saves the list to the dataFile stored locally.
     * Parses the tasks and separates the different parameters with " | " for ease of parsing
     */
    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter(dataFile, false);

            // Loop through the taskList and parse the tasks to write it in the dataFile
            for (Task current : taskList) {
                myWriter.write(current.getTaskType() + Ui.DELIMITER + current.getDoneStatus() + Ui.DELIMITER
                        + current.getDescription() + Ui.DELIMITER + current.getDueDate() + "\n");
            }
            myWriter.close(); // Must close the writer after each use

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the data from the dataFile and returns it as an ArrayList of Tasks.
     *
     * @return ArrayList representing the tasks in the dataFile
     */
    public ArrayList<Task> loadFromFile() {
        try { // Make a new file if possible
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();


            // Read the txt into the taskList arrayList
            Scanner taskListReader = new Scanner(dataFile);

            // While there are more tasks in the file
            while (taskListReader.hasNext()) {
                String taskDetails = taskListReader.nextLine();

                // [0] is the Task category, [1] is isDone boolean, [2] is task desc, [3] is task dueDate
                int isDoneFlagIndex = 1;
                int taskDescIndex = 2;
                int dueDateTimeIndex = 3;

                if (taskDetails.startsWith("D ")) {
                    taskList.add(new Deadline(taskDetails.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                            LocalDate.parse(taskDetails.split(Ui.SPLIT_DELIMITER)[dueDateTimeIndex].split(" ")[0]),
                            LocalTime.parse(taskDetails.split(Ui.SPLIT_DELIMITER)[dueDateTimeIndex].split(" ")[1]),
                            !taskDetails.split(Ui.SPLIT_DELIMITER)[isDoneFlagIndex].equals("0")));

                } else if (taskDetails.startsWith("E ")) {
                    taskList.add(new Event(taskDetails.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                            taskDetails.split(Ui.SPLIT_DELIMITER)[dueDateTimeIndex],
                            !taskDetails.split(Ui.SPLIT_DELIMITER)[isDoneFlagIndex].equals("0")));

                } else if (taskDetails.startsWith("T ")) {
                    taskList.add(new Todo(taskDetails.split(Ui.SPLIT_DELIMITER)[taskDescIndex],
                            !taskDetails.split(Ui.SPLIT_DELIMITER)[isDoneFlagIndex].equals("0")));

                } else {
                    throw new DinoException("Unknown command type loaded from saved file!");
                }
            }

        } catch (IOException | DinoException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}
