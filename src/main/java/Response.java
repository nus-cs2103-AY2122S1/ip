import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Response class contains the logic for processing the commands from Duke.
 * It supports (i) the list command, (ii) the bye command,
 * (iii) adding different types of tasks to the list, (iv) and marking the tasks as done,
 * (v) removing tasks
 */

public class Response {
    private ArrayList<Task> lstOfTasks = new ArrayList<Task>();
    private FileWriter file;

    Response() {
        try {
            file = new FileWriter(Duke.pathAddress, true);
        } catch (IOException e) {
            System.out.println("error occurred");
            e.getStackTrace();
        }
    }

    void writeToFile(String text) {
        try {
            file.write(text + System.lineSeparator());
            file.close();
        } catch (IOException e) {
            System.out.println("error writing to file");
            e.getStackTrace();
        }
    }

    void modifyTaskInFile(ArrayList<Task> task) {
        try {
            Files.deleteIfExists(Paths.get(Duke.pathAddress));
            FileWriter fileWriter = new FileWriter(Duke.pathAddress);
            for (Task t : task) {
                fileWriter.append(t.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error modifying file");
            e.getStackTrace();
        }
    }

    /**
     * Returns a farewell statement to the user.
     * @return A string to bids farewell to the user
     */
    String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a numbered list with the Tasks that were added.
     * @return A numbered list with the Tasks that were added
     */
    String list() {
        String res = "";
        for (int i = 0; i < lstOfTasks.size(); i++) {
            res += (i + 1) + ". " +
                lstOfTasks.get(i).toString() + "\n";
        }
        return "Here are the tasks in your list:\n" + res;
    }

    /**
     * Returns a string that shows the Task that has been completed.
     * @param taskNumber the Task number on the list of Tasks
     * @return A string that shows the Task that has been completed
     */
    String done(int taskNumber) {
        String res = "Nice! I've marked this task as done:\n";
        Task currTask = lstOfTasks.get(taskNumber);
        currTask.markAsDone();
        res += " [" + currTask.getStatusIcon() + "] " + currTask.description;
        modifyTaskInFile(lstOfTasks);
        return res;
    }

    /**
     * Returns a string that show the ToDo task has been added.
     * Unless no description is provided, then an error message is displayed
     * @param task String representation of the task
     * @return A string that shows the ToDo task has been added
     */
    String todo(String task) {
        if (task.length() == 0) {
            return "OOPS!!! The description of a todo cannot be empty.\n";
        }
        Todo taskToDo = new Todo(task);
        writeToFile(taskToDo.toString());
        lstOfTasks.add(taskToDo);
        return "Got it. I've added this task:\n" + taskToDo.toString() + "\n"
            + "Now you have " + lstOfTasks.size() + " tasks in the list\n";
    }

    /**
     * Returns a string that show the Deadline task has been added.
     * Unless no description is provided, then an error message is displayed
     * @param task String representation of the task
     * @return A string that shows the Deadline task has been added
     */
    String deadline(String task) {
        if (task.length() == 0) {
            return "OOPS!!! The description of a deadline cannot be empty.\n";
        }
        char[] data = task.toCharArray();
        String taskWithDeadLine = "";
        String by = "";
        int index = 0;
        while (index < data.length) {
            if (data[index] == '/') {
                break;
            } else {
                taskWithDeadLine += data[index];
            }
            index++;
        }
        // Add 3 to index to avoid "by "
        index += 3;
        while (index < data.length) {
            by += data[index];
            index++;
        }
        DeadLine deadLine = new DeadLine(taskWithDeadLine, by);
        writeToFile(deadLine.toString());
        lstOfTasks.add(deadLine);
        return "Got it. I've added this task:\n" + deadLine.toString() + "\n"
            + "Now you have " + lstOfTasks.size() + " tasks in the list\n";
    }

    /**
     * Returns a string that show the Event task has been added.
     * Unless no description is provided, then an error message is displayed
     * @param task String representation of the task
     * @return A string that shows the Event task has been added
     */
    String event(String task) {
        if (task.length() == 0) {
            return "OOPS!!! The description of an event cannot be empty.\n";
        }
        char[] data = task.toCharArray();
        String eventTask = "";
        String by = "";
        int index = 0;
        while (index < data.length) {
            if (data[index] == '/') {
                break;
            } else {
                eventTask += data[index];
            }
            index++;
        }
        // Add 3 to index to avoid "by "
        index += 3;
        while (index < data.length) {
            by += data[index];
            index++;
        }
        Event event = new Event(eventTask, by);
        writeToFile(event.toString());
        lstOfTasks.add(event);
        return "Got it. I've added this task:\n" + event.toString() + "\n"
            + "Now you have " + lstOfTasks.size() + " tasks in the list\n";
    }

    /**
     * Returns a string notifying the user that a task has been deleted.
     * @param taskNum the task number to be removed
     * @return a string notifying the user that a task has been removed
     */

    String delete(int taskNum) {
        Task removed = lstOfTasks.remove(taskNum);
        modifyTaskInFile(lstOfTasks); 
        return "Noted. I've removed this task:\n" + removed.toString() + "\n"
            + "Now you have " + lstOfTasks.size() + " in the list.";
    }

    /**
     * Handles the input commands from Duke.
     * Unless an invalid command is provided, then an error message is displayed
     * @param string the command input from Duke
     * @return a numbered list, a farewell, or an added message 
     */
    String output(String string) {
        if (string.equals("list")) {
            return list();

        } else if (string.equals("bye")) {
            return bye();

        } else if (string.contains("todo")) {
            int startIndex = "todo".length();
            int endIndex = string.length();
            String task = string.substring(startIndex, endIndex);
            return todo(task);

        } else if (string.contains("deadline")) {
            int startIndex = "deadline".length();
            int endIndex = string.length();
            String task = string.substring(startIndex, endIndex);
            return deadline(task);

        } else if (string.contains("event")) {
            int startIndex = "event".length();
            int endIndex = string.length();
            String task = string.substring(startIndex, endIndex);
            return event(task);

        } else if (string.contains("done")) {
            int i = Integer.parseInt(
                    String.valueOf(
                        string.toCharArray()[string.length() - 1])) - 1;
            return done(i);

        } else if (string.contains("delete")) {
            int i = Integer.parseInt(
                    String.valueOf(
                        string.toCharArray()[string.length() - 1])) - 1;
            return delete(i);
        }
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }
}
