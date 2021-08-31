package duke;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Deals with making sense of the user commands
 */
public class Parser {
    /**
     * Identifies and returns the command word from the user's input so that the corresponding methods
     * can be called
     * @param command user's input
     * @return the extracted command word from the user's input
     */
    public static String parseCommand(String command) {
        String keyWord = "";
        if (command.equals("list")) {
            keyWord = "list";
        } else if (command.startsWith("done")) {
            keyWord = "done";
        } else if (command.startsWith("delete")) {
            keyWord = "delete";
        } else if (command.startsWith("todo")) {
            keyWord = "todo";
        } else if (command.startsWith("deadline")) {
            keyWord = "deadline";
        } else if (command.startsWith("event")) {
            keyWord = "event";
        } else if (command.startsWith("today")) {
            keyWord = "today";
        } else if (command.startsWith("find")) {
            keyWord = "find";
        } else if (command.startsWith("bye")) {
            keyWord = "bye";
        }
        return keyWord;
    }

    /**
     * Identifies the task that is completed and passes it to markASDoneAndUpdate() method
     * @param instruction User's input that followed the "done" command word
     * @throws DukeException if there is no task that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDone(String instruction) throws DukeException, IOException {
        String result = "";
        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("Hmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");
        } else {
            TaskList.getTaskList().get(taskNum).markAsDoneAndUpdate();
            result += "Good job! I've marked this task as done:\n";
            result += "\t" + TaskList.getTaskList().get(taskNum).toString() + "\n";
        }
        return result;
    }

    /**
     * Identifies the task that is to be deleted and passes it to deleteTaskAndUpdate() method
     * @param instruction User's input that followed the "delete" command word
     * @throws DukeException if there is no task that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDelete(String instruction) throws DukeException, IOException {
        String result = "";
        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("Hmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");
        } else {
            result += "Noted. I've removed this task from your main list:\n";
            result += "\t" + TaskList.getTaskList().get(taskNum).toString() + "\n";
            TaskList.deleteTaskAndUpdate(TaskList.getTaskList().get(taskNum));
            result += "Now you have " + TaskList.getCounter() +
                    " task(s) in the list.\n";
        }
        return result;
    }

    /**
     * Identifies the description of the todo task that is to be added and passes it to
     * addTaskAndUpdate() method
     * @param instruction User's input that followed the "todo" command word
     * @throws DukeException if there is no task description keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseTodo(String instruction) throws DukeException, IOException {
        String result = "";
        if (instruction.length() < 5) {
            throw new DukeException("OOPS!!! The description of a todo " +
                    "cannot be empty.");
        } else {
            String taskDescription = instruction.substring(4);
            Todo newTodo = new Todo(taskDescription);
            TaskList.addTaskAndUpdate(newTodo);
            result += "Got it! I've added this task:\n";
            result += "\t" + newTodo.toString() + "\n";
            result += "Now you have " + TaskList.getCounter() +
                    " task(s) in the list.\n";
        }
        return result;
    }

    /**
     * Identifies the description of the deadline task that is to be added and passes it to
     * addTaskAndUpdate() method
     * @param instruction User's input that followed the "deadline" command word
     * @throws DukeException if there is no task description/no deadline/wrongly formatted deadline
     * keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDeadline(String instruction) throws DukeException, IOException {
        String result = "";
        if (instruction.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline " +
                    "cannot be empty.");
        } else {
            String taskDescription = "";
            int currIndex = 8;
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("I think you forgot to key in your deadline! Please key it" +
                        " in as dd/mm/yyyy hh:mm (in 24 hours format)");
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");
            } else if (instruction.substring(currIndex).length() < 20){
                throw new DukeException("Please include the time in the 24 hour " +
                        "format (e.g. 15:00)");
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newDeadline = new Deadline(taskDescription, by);
                TaskList.addTaskAndUpdate(newDeadline);
                result += "Got it! I've added this task:\n";
                result += "\t" + newDeadline.toString() + "\n";
                result += "Now you have " + TaskList.getCounter() +
                        " task(s) in the list.\n";
                return result;
            }
        }
    }

    /**
     * Identifies the description of the event task that is to be added and passes it to
     * addTaskAndUpdate() method
     * @param instruction User's input that followed the "event" command word
     * @throws DukeException if there is no task description/no deadline/wrongly formatted deadline
     * keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseEvent(String instruction) throws DukeException, IOException {
        String result = "";
        if (instruction.length() < 7) {
            throw new DukeException("\tOOPS!!! The description of a event " +
                    "cannot be empty.");
        } else {
            String taskDescription = "";
            int currIndex = 5;
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("I think you forgot to key in your event timing!");
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");
            } else if (instruction.substring(currIndex).length() < 25){
                throw new DukeException("Please include the start and end times in the 24 hour " +
                        "format (e.g. 15:00-16:00)");
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newEvent = new Event(taskDescription, by);
                TaskList.addTaskAndUpdate(newEvent);
                result += "Got it! I've added this task:\n";
                result += "\t" + newEvent.toString() + "\n";
                result += "Now you have " + TaskList.getCounter() +
                        " task(s) in the list.\n";
                return result;
            }
        }
    }

    /**
     * Prints out the list of tasks saved by Jarvis
     */
    public static String parseList() {
        int num = 1;
        String result = "";
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            result += num + "." + TaskList.getTaskList().get(i).toString() + "\n";
            num++;
        }
        return result;
    }

    /**
     * Prints out the list of tasks set for/due today and all todo tasks
     */
    public static String parseToday() {
        String result = "";
        result += "Tasks scheduled for today are: \n";
        int num = 1;
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (TaskList.getTaskList().get(i) instanceof Todo) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            } else {
                int currYear = LocalDateTime.now().getYear();
                int currMonth = LocalDateTime.now().getMonthValue();
                int currDate = LocalDateTime.now().getDayOfMonth();
                LocalDateTime start = LocalDateTime.of(currYear, currMonth, currDate, 0, 0);
                LocalDateTime end = LocalDateTime.of(currYear, currMonth, currDate, 23, 59);

                if (TaskList.getTaskList().get(i) instanceof Deadline &&
                        (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isAfter(start) &&
                        (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isBefore(end)) {
                    result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                    num++;
                } else if (TaskList.getTaskList().get(i) instanceof Event &&
                        (((Event) TaskList.getTaskList().get(i)).getEventStart()).isAfter(start) &&
                        (((Event) TaskList.getTaskList().get(i)).getEventStart()).isBefore(end) &&
                        (((Event) TaskList.getTaskList().get(i)).getEventEnd()).isAfter(start) &&
                        (((Event) TaskList.getTaskList().get(i)).getEventEnd()).isBefore(end)) {
                    result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                    num++;
                }
            }
        }
        if (num == 1) {
            result += "\tLooks like there is nothing due today!\n";
        }
        return result;
    }

    public static String parseFind(String search) {
        String result = "";
        result += "Here are the matching tasks in your list:\n";
        int num = 1;
        search = search.substring(5);
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (TaskList.getTaskList().get(i).getDescription().contains(search)) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            }
        }
        if (num == 1) {
            result += "\tNo matching results found!\n";
        }
        return result;
    }
}
