package duke;

import java.time.LocalDate;

/** Class that handles the input provided by user */
public class Parser {

    /**
     * Parses the input entered by the user and takes the appropriate
     * action to deal with the input
     *
     * @param input The string that contains the user input
     * @param t The TaskList that contains the tasks to be added
     * @param s The Storage that handles the reading and writing to a text file
     */
    public static String parse(String input, TaskList t, Storage s) {
        if (input.equals("list")) {
            return returnListString(t);
        } else if (input.startsWith("done") && checkDoneStatementConditions(input)) {
            return returnDoneString(input, t, s);
        } else if (input.startsWith("todo")) {
            return returnTodoString(input, t, s);
        } else if (input.startsWith("deadline")) {
            return returnDeadlineString(input, t, s);
        } else if (input.startsWith("event")) {
            return returnEventString(input, t, s);
        } else if (input.startsWith("delete") && input.length() < 11) {
            return returnDeleteString(input, t, s);
        } else if (input.startsWith("find")) {
            return returnFindString(input, t);
        } else {
            DukeException e = new NonExistentKeyword();
            return e.getMsg();
        }
    }

    /**
     * Returns the intended output for the 'list' input entered
     *
     * @param t The task list that gets read
     * @return String The output string that contains all the elements
     * in the task list
     */
    public static String returnListString(TaskList t) {
        String returnString = "";
        for (int i = 0; i < t.size(); i++) {
            returnString = returnString + (i + 1) + ". " + t.get(i).toString() + "\n";
        }
        return returnString;
    }

    /**
     * Returns the intended output for the 'done' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for
     * the 'done' command
     */
    public static String returnDoneString(String input, TaskList t, Storage s) {
        String returnString = "";
        int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        t.get(value - 1).markAsDone();
        s.appendListToFile(t);
        returnString = returnString + "Nice! I've marked this task as done: " + "\n";
        returnString = returnString + "[X] " + t.get(value - 1).description + "\n";
        return returnString;
    }

    /**
     * Returns the intended output for the 'to-do' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'to-do' command
     */
    public static String returnTodoString(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 6) {
            return new NullTaskError().getMsg("todo");
        } else {
            String firstTodo = input.substring(5);
            t.addTodo(firstTodo);
            s.appendListToFile(t);
            returnString = returnString + "Got it. I've added this task: " + "\n";
            returnString = returnString + new Todo(firstTodo).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'deadline' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'deadline' command
     */
    public static String returnDeadlineString(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 10) {
            return new NullTaskError().getMsg("deadline");
        } else {
            String[] temp = input.split("/by");
            String firstDeadline = temp[0].substring(9);
            LocalDate date1 = getDate(temp);
            t.addDeadline(firstDeadline, date1);
            s.appendListToFile(t);
            returnString = returnString + "Got it. I've added this task: " + "\n";
            returnString = returnString + new Deadline(firstDeadline, date1).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'event' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'event' command
     */
    public static String returnEventString(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 7) {
            return new NullTaskError().getMsg("event");
        } else {
            String[] tempEvent = input.split("/at");
            String firstEvent = tempEvent[0].substring(6);
            LocalDate date1 = getDate(tempEvent);
            t.addEvent(firstEvent, date1);
            returnString = returnString + "Got it. I've added this task: " + "\n";
            returnString = returnString + new Event(firstEvent, date1).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
            s.appendListToFile(t);
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'delete' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'delete' command
     */
    public static String returnDeleteString(String input, TaskList t, Storage s) {
        String returnString = "";
        int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        Task removedTask = t.get(value - 1);
        t.delete(value - 1);
        returnString = returnString + "Noted. I've removed this task: " + "\n";
        returnString = returnString + removedTask.toString() + "\n";
        returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
        s.appendListToFile(t);
        return returnString;
    }

    /**
     * Returns the intended output for the 'find' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'find' command
     */
    public static String returnFindString(String input, TaskList t) {
        String keyword = input.substring(5);
        String returnString = "";
        int count = 0;
        for (Task task : t.getTaskList()) {
            String desc = task.description.substring(0, task.description.length() - 4);
            String[] splitDesc = desc.split(" ");
            System.out.println(splitDesc[1]);
            for (String str : splitDesc) {
                if (str.equals(keyword)) {
                    returnString = returnString + task.toString() + "\n";
                    count = count + 1;
                }
            }
        }
        if (count == 0) {
            System.out.println("OOPS! The task does not exist");
            return returnString + "\n" + "OOPS! The task does not exist";
        } else {
            return returnString;
        }
    }

    /**
     * Returns whether the done statement fits the correct format
     *
     * @param input The input of the user
     * @return Boolean Whether the input matches the required format
     */
    public static Boolean checkDoneStatementConditions(String input) {
        return Character.isDigit(input.charAt(input.length() - 1))
                && input.length() <= 8 && !Character.isAlphabetic(input.charAt(input.length() - 2))
                && Character.isDigit(input.charAt(5));
    }

    /**
     * Returns the date of the task (event/deadline)
     *
     * @param tempEvent An array of string that contains the userInput
     * split into two parts
     * @return LocalDate The date of the task
     */
    public static LocalDate getDate(String[] tempEvent) {
        String[] splitDate = tempEvent[1].split(" ");
        String date = splitDate[1];
        String[] breakingDate = date.split("/");
        String year = breakingDate[2];
        String month = breakingDate[1];
        String currentDate = breakingDate[0];
        int i = Integer.parseInt(currentDate);
        if (i < 10) {
            currentDate = "0" + currentDate;
        }
        String finalDateFormat = year + "-" + month + "-" + currentDate;
        LocalDate date1 = LocalDate.parse(finalDateFormat);
        return date1;
    }
}
