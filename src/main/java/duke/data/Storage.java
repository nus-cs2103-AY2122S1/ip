package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the storage portion of the Duke program.
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
     * @param fileName the string of the file name (e.g. "Duke.txt")
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
                myWriter.write(current.getTaskType() + " | " + current.getDoneStatus() + " | "
                        + current.getDescription() + " | " + current.getDueDate() + "\n");
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
            if (dataFile.createNewFile()) {
                //System.out.println("New task list saved at: " + dataFile.getName());
            } else {
                //System.out.println("Loading your previous task list...");

                // Read the txt into the taskList arrayList
                Scanner taskListReader = new Scanner(dataFile);

                // While there are more tasks in the file
                while (taskListReader.hasNext()) {
                    String taskDetails = taskListReader.nextLine();
                    if (taskDetails.startsWith("D ")) {

                        // [0] is the Task category, [1] is isDone boolean, [2] is task desc, [3] is task dueDate
                        if (taskDetails.split(" \\| ")[1].equals("0")) {
                            taskList.add(new Deadline(taskDetails.split(" \\| ")[2],
                                    LocalDate.parse(taskDetails.split(" \\| ")[3].split(" ")[0]),
                                    LocalTime.parse(taskDetails.split(" \\| ")[3].split(" ")[1])));
                        } else {
                            taskList.add(new Deadline(taskDetails.split(" \\| ")[2],
                                    LocalDate.parse(taskDetails.split(" \\| ")[3].split(" ")[0]),
                                    LocalTime.parse(taskDetails.split(" \\| ")[3].split(" ")[1]),
                                    true));
                        }
                    } else if (taskDetails.startsWith("E ")) {
                        if (taskDetails.split(" \\| ")[1].equals("0")) {
                            taskList.add(new Event(taskDetails.split(" \\| ")[2], taskDetails.split(" \\| ")[3]));
                        } else {
                            taskList.add(new Event(taskDetails.split(" \\| ")[2], taskDetails.split(" \\| ")[3], true));
                        }

                    } else {
                        if (taskDetails.split(" \\| ")[1].equals("0")) {
                            taskList.add(new Todo(taskDetails.split(" \\| ")[2]));
                        } else {
                            taskList.add(new Todo(taskDetails.split(" \\| ")[2], true));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}
