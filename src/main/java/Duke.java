import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation for Duke.
 *
 * @author Wong Yun Rui Chris
 */
public class Duke {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * A private method to add the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @param isDone The boolean if the task is done
     * @throws DukeException Exceptions specific to Duke's input
     * @return The String of the reply after adding a task
     */
    private String addTask(Task.TaskName type, String input, Boolean isDone) throws DukeException {
        Task task;
        String[] inputArray;
        switch (type) {
        case TODO:
            task = new Todo(input, isDone);
            break;
        case EVENT:
            // Fallthrough
        case DEADLINE:
            inputArray = input.split(type.getSplit());
            if (inputArray.length < 2) {
                throw new DukeException("The format for " + type +" is wrong.");
            } else if(inputArray[0].isBlank()) {
                throw new DukeException("The description of " + type + " cannot be empty.");
            } else if(inputArray[1].isBlank()) {
                throw new DukeException("The date/time is missing from " + type +".");
            }
            task = type == Task.TaskName.EVENT ? new Event(inputArray[0], inputArray[1], isDone)
                    : new Deadline(inputArray[0], inputArray[1], isDone);
            break;
        default:
            throw new DukeException("Unexpected value: " + type);
        }

        this.list.add(task);
        return "\tGot it. I've added this task:\n\t\t " + task
                + "\n\tNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * A private method to add the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @throws DukeException Exceptions specific to Duke's input
     * @return The String of the reply after adding a task
     */
    private String addTask(Task.TaskName type, String input) throws DukeException {
        return addTask(type, input, false);
    }

    /**
     * A private method to return the list of all the Tasks.
     *
     * @return The String of the list of all the tasks formatted
     */
    private String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: list) {
            String temp = "\t" + i + "." + task.toString() + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }

    /**
     * A private method to mark the Task at the index of the list to be done.
     *
     * @param input The index of the Task in the list that is to be mark as done
     * @throws DukeException Exceptions specific to Duke's input
     * @return The corresponding String reply after marking a task done
     */
    private String markTask(String input) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.list.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        return "\tNice! I've marked this task as done:\n\t\t" + list.get(index - 1).markDone();
    }

    /**
     * Deletes the task at the specific index.
     *
     * @param input The index of the task in the list that is to be deleted
     * @throws DukeException Exceptions specific to Duke's input
     * @return The corresponding String reply after deleting a task
     */
    private String deleteTask(String input) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.list.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        Task deleted = this.list.remove(index - 1);
        return "\tNoted. I've removed this task:\n\t\t" + deleted.toString()
                + "\nNow you have " + this.list.size() + " tasks in the list.";
    }

    /**
     * Handles all the text input to call the correct corresponding method
     *
     * @param input The text input from the user to Duke
     * @throws DukeException Exceptions specific to Duke's input
     * @return The corresponding String reply to the user's input
     */
    public String handleInput(String input) throws DukeException {
        String[] inputArray = input.split(" ",2);
        String reply = "";

        switch (inputArray[0]) {
        case "list":
            reply = this.displayTask();
            break;

        case "done":
            // Fallthrough
        case "delete":
            if (inputArray.length == 1) {
                throw new DukeException("The index is missing.");
            }
            reply = inputArray[0].equals("done") ? this.markTask(inputArray[1]) : this.deleteTask(inputArray[1]);
            saveData();
            break;

        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            if (inputArray.length < 2 || inputArray[1].isBlank()) {
                throw new DukeException("The description of " + inputArray[0] + " cannot be empty.");
            }
            reply = this.addTask(Task.TaskName.getTaskType(inputArray[0]), inputArray[1]);
            saveData();
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        return reply;
    }

    /**
     * Saves the data of the task in the list into a text file formatted
     */
    private void saveData() {
        try {
            String path = new File("").getAbsoluteFile() + "/data";
            File file = new File(path);

            // create directory if not yet created
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            FileWriter fw = new FileWriter(file + "/duke.txt");

            for (Task task: this.list) {
                fw.write(task.toData() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from the saved file if it exists to update Duke on running
     */
    private void readData() {
        try {
            String path = new File("").getAbsoluteFile() + "/data/duke.txt";
            File file = new File(path);

            if (!file.isFile()) {
                return;
            }

            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] inputArray = s.nextLine().split(" \\| ");
                Task.TaskName type = Task.TaskName.getTaskType(inputArray[0]);

                addTask(type, inputArray[2] + type.getSplit()
                        + (type != Task.TaskName.TODO ? inputArray[3] : "")
                        , inputArray[1].equals("1"));
            }
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you\n");

        Duke duke = new Duke();
        duke.readData();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                System.out.println(duke.handleInput(input));
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
