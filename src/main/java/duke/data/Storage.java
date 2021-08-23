package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;


public class Storage {

    File dataFile;
    ArrayList<Task> taskList = new ArrayList<>();


    public Storage(String fileName) {
        this.dataFile = new File(fileName);
    }

    // save to txt file after each change
    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter(dataFile, false);
            for (Task current : taskList) {
                myWriter.write(current.getTaskType() + " | " + current.getDoneStatus() + " | " +
                        current.getDescription() + " | " + current.getDueDate() + "\n");
            }
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        try {
            dataFile.getParentFile().mkdirs();
            if (dataFile.createNewFile()) {
                System.out.println("New task list saved at: " + dataFile.getName());
            } else {
                System.out.println("Loading your previous task list...");
                // Read the txt into the taskList arrayList
                Scanner taskListReader = new Scanner(dataFile);
                while (taskListReader.hasNext()) {
                    String taskDetails = taskListReader.nextLine();
                    if (taskDetails.startsWith("D ")) {
                        // [0] is the Task category, [1] is the isDone boolean, [2] is the task desc, [3] is the task dueDate
                        if (taskDetails.split(" \\| ")[1].equals("0")) {
                            //System.out.println(taskDetails.split(" \\| ")[3]);
                            taskList.add(new Deadline(taskDetails.split(" \\| ")[2],
                                    LocalDate.parse(taskDetails.split(" \\| ")[3].split(" ")[0]),
                                    LocalTime.parse(taskDetails.split(" \\| ")[3].split(" ")[1])));
                        } else {
                            //taskList.add(new Deadline(taskDetails.split(" \\| ")[2], taskDetails.split(" \\| ")[3], true));
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
