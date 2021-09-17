package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Storage class encapsulates the action of saving the taskList created by Duke.
 */
public class Storage {

    /** The file for the Storage to save the tasklist in. */
    private File file;

    /** Constant String for the symbol to separate the descriptions of the saved tasklist. */
    private final String SEPARATE = " / ";

    /**
     * Constructor to initialise the Storage.
     *
     * @param pathName The path name to be converted to create a new File instance.
     */
    public Storage(String pathName) {
        this.file = new File(pathName);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }
    }

    /**
     * Saves the contents of the tasklist to the designated file.
     *
     * @param taskList The current state of the tasklist handled by Duke.
     */
    public void saveFile(ArrayList<Task> taskList) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(this.file);
            String taskToFile = "";

            // Iterate through every element in the tasklist to create the text version of the task
            for (Task task : taskList) {
                String checkTaskType = task.toString();
                String taskStatus = (task.getStatusIcon().equals("X")) ? "1" : "0";

                // Determine the type of task
                switch(checkTaskType.charAt(1)) {
                case 'T':
                    Todo todo = (Todo) task;
                    taskToFile = "Todo" + SEPARATE + taskStatus + SEPARATE + todo.getTaskDescription();
                    break;
                case 'D':
                    Deadline deadline = (Deadline) task;
                    taskToFile = "Deadline" + SEPARATE + taskStatus + SEPARATE + deadline.getTaskDescription()
                            + SEPARATE + deadline.getBy();
                    break;
                case 'E':
                    Event event = (Event) task;
                    taskToFile = "Event" + SEPARATE + taskStatus + SEPARATE + event.getTaskDescription()
                            + SEPARATE + event.getAt();
                    break;
                default:
                    assert false : "There should not be other cases of tasks!";
                }

                // Save the newly text version of the task into the file
                myWriter.write(taskToFile + "\n");
            }

            myWriter.close();

        } catch (IOException e) {
            System.out.println("Master something went wrong, I can't save the Tasklist! ☹");
            e.printStackTrace();
        }
    }

    /**
     * Loads the contents of the previously saved tasklist from the designated file.
     *
     * @return The current state of the tasklist handled by Duke.
     * @throws IOException
     */
    public ArrayList<Task> loadFile() throws IOException {
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Task> taskList = new ArrayList<>();

            // Iterate through line by line of the designated file to obtain every task in text form
            while (scanner.hasNextLine()) {
                String currTask = scanner.nextLine();
                Task task = null;
                String[] taskBreakdown = currTask.split(SEPARATE);

                // Determine the type of task from the text file and initialise the specified task
                switch(taskBreakdown[0]) {
                case "Todo":
                    task = new Todo(taskBreakdown[2]);
                    break;
                case "Deadline":
                    LocalDate by = LocalDate.parse(taskBreakdown[3]);
                    task = new Deadline(taskBreakdown[2], by);
                    break;
                case "Event":
                    LocalDateTime at = LocalDateTime.parse(taskBreakdown[3]);
                    task = new Event(taskBreakdown[2], at);
                    break;
                default:
                    assert false : "There should not be other cases of tasks!";
                }

                assert (task != null) : "The task should not be null here";

                if (taskBreakdown[1].equals("1")) {
                    task.completeTask();
                }

                // Add the task back to the tasklist
                taskList.add(task);
            }

            return taskList;

        } catch (IOException e) {
            System.out.println("Master something went wrong, I can't load the Tasklist! ☹");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
