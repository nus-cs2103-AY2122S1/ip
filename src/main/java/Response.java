/**
 * Response class contains the logic for processing the commands from Duke.
 * It supports (i) the list command, (ii) the bye command,
 * (iii) adding different types of tasks to the list, (iv) and marking the tasks as done
 */

public class Response {
    private Task[] lstOfTasks = new Task[100];
    private int itemCount = 0;

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
        for (int i = 0; i < itemCount; i++) {
            res += (i + 1) + ". " +
                    lstOfTasks[i].toString() + "\n";
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
        Task currTask = lstOfTasks[taskNumber];
        currTask.markAsDone();
        res += " [" + currTask.getStatusIcon() + "] " + currTask.description;
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
        lstOfTasks[itemCount] = taskToDo;
        itemCount++;
        return "Got it. I've added this task:\n" + taskToDo.toString() + "\n"
                + "Now you have " + itemCount + " tasks in the list\n";
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
        lstOfTasks[itemCount] = deadLine;
        itemCount++;
        return "Got it. I've added this task:\n" + deadLine.toString() + "\n"
                + "Now you have " + itemCount + " tasks in the list\n";
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
        lstOfTasks[itemCount] = event;
        itemCount++;
        return "Got it. I've added this task:\n" + event.toString() + "\n"
                + "Now you have " + itemCount + " tasks in the list\n";
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
        } else if (string.contains("done")) {
            // getting the task number
            int taskNum = Integer.parseInt(
                    String.valueOf(
                            string.toCharArray()[string.length() - 1])) - 1;
            return done(taskNum);
        } else {
            if (string.contains("todo")) {
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

            }
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }
}
