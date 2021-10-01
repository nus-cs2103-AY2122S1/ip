package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class that handles the input provided by user */
public class Parser {

    /**
     * Parses the input entered by the user and takes the appropriate
     * action to deal with the input
     *
     * @param input The string that contains the user input
     * @param t     The TaskList that contains the tasks to be added
     * @param s     The Storage that handles the reading and writing to a text file
     */
    public static String parse(String input, TaskList t, Storage s) {
        if (input.equals("list")) {
            return returnListContents(t);
        } else if (input.startsWith("done") && checkDoneStatementConditions(input)) {
            return parseDoneCommand(input, t, s);
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input, t, s);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input, t, s);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input, t, s);
        } else if (input.startsWith("delete") && input.length() < 11) {
            return parseDeleteCommand(input, t, s);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input, t);
        } else if (input.startsWith("schedule on")) {
            return parseScheduleOnCommand(input, t);
        } else if (input.startsWith("schedule until")) {
            return parseScheduleUntilCommand(input, t);
        } else if (input.startsWith("schedule after")) {
            return parseScheduleAfterCommand(input, t);
        } else if (input.startsWith("help")) {
            return parseHelpCommand();
        } else {
            DukeException e = new NonExistentKeyword();
            return e.getErrorMessage();
        }
    }

    /**
     * Returns the intended output for the 'list' input entered
     *
     * @param t The task list that gets read
     * @return String The output string that contains all the elements
     * in the task list
     */
    public static String returnListContents(TaskList t) {
        String returnString = " Here are the tasks in your list: " + "\n";
        if (t.size() == 0) {
            return "There are no existing tasks! Type 'help' to see list of commands available to add tasks";
        } else {
            for (int i = 0; i < t.size(); i++) {
                returnString = returnString + (i + 1) + ". " + t.get(i).toString() + "\n";
            }
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'done' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for
     * the 'done' command
     */
    public static String parseDoneCommand(String input, TaskList t, Storage s) {
        if (t.size() == 0) {
            return "There are no existing tasks! Type 'help' to see list of commands available to add tasks";
        }
        String returnString = "";
        int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        if (index > t.size()) {
            return "You have entered an invalid index. You only have " + Integer.valueOf(t.size()) + " task(s) in your list";
        }
        assert index > 0: "index of task to be marked done must be more than 0";
        t.get(index - 1).setDone();
        s.appendListToFile(t);
        returnString = returnString + "Nice! I've marked this task as done: " + "\n";
        returnString = returnString + "[X] " + t.get(index - 1).description + "\n";
        return returnString;
    }

    /**
     * Returns the intended output for the 'to-do' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'to-do' command
     */
    public static String parseTodoCommand(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 6) {
            return new NullTaskError().getErrorMessage("todo");
        } else {
            String firstTodo = input.substring(5);
            t.addTodo(firstTodo);
            s.appendListToFile(t);
            returnString = returnString + "Got it! I've added this task: " + "\n";
            returnString = returnString + new Todo(firstTodo).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " task(s) in the list" + "\n";
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'deadline' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'deadline' command
     */
    public static String parseDeadlineCommand(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 10) {
            return new NullTaskError().getErrorMessage("deadline");
        } else {
            String[] wordsInTaskDescription = input.split("/by");
            String timeString = input.substring(input.length()-4);
            String deadlineDescription = wordsInTaskDescription[0].substring(9);
            LocalDateTime deadlineDate = getDate(wordsInTaskDescription, timeString);
            t.addDeadline(deadlineDescription, deadlineDate);
            s.appendListToFile(t);
            returnString = returnString + "Got it! I've added this task: " + "\n";
            returnString = returnString + new Deadline(deadlineDescription, deadlineDate).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " task(s) in the list" + "\n";
            return returnString;
        }
    }

    /**
     * Returns the intended output for the 'event' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'event' command
     */
    public static String parseEventCommand(String input, TaskList t, Storage s) {
        String returnString = "";
        if (input.length() < 7) {
            return new NullTaskError().getErrorMessage("event");
        } else {
            String[] wordsInEventDescription = input.split("/at");
            String timeString = input.substring(input.length()-4);
            String eventDescription = wordsInEventDescription[0].substring(6);
            LocalDateTime eventDate = getDate(wordsInEventDescription, timeString);
            t.addEvent(eventDescription, eventDate);
            returnString = returnString + "Got it! I've added this task: " + "\n";
            returnString = returnString + new Event(eventDescription, eventDate).toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " task(s) in the list" + "\n";
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
    public static String parseDeleteCommand(String input, TaskList t, Storage s) {
        if (t.size() == 0) {
            return "There are no existing tasks! To add tasks, use the 'todo', 'event' or 'deadline' keywords";
        }
        String returnString = "";
        int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        if (taskIndex > t.size()) {
            return "You have entered an invalid index. You only have " + Integer.valueOf(t.size()) + " task(s) in your list";
        }
        Task removedTask = t.get(taskIndex - 1);
        t.delete(taskIndex - 1);
        returnString = returnString + "Noted. I've removed this task: " + "\n";
        returnString = returnString + removedTask.toString() + "\n";
        returnString = returnString + "Now you have " + t.size() + " task(s) in the list" + "\n";
        s.appendListToFile(t);
        return returnString;
    }

    /**
     * Returns the intended output for the 'find' input entered
     *
     * @param t The task list that gets read
     * @return String The output message that contains the intended output for the 'find' command
     */
    public static String parseFindCommand(String input, TaskList t) {
        String keyword = input.substring(5);
        String returnString = "";
        int count = 0;
        for (Task task : t.getTaskList()) {
            String taskDescription = task.description.substring(0, task.description.length());
            String[] wordsInTaskDescription = taskDescription.split(" ");
            for (String str : wordsInTaskDescription) {
                if (str.equals(keyword)) {
                    returnString = returnString + task.toString() + "\n";
                    count = count + 1;
                }
            }
        }
        if (count == 0) {
            return "OOPS! The task does not exist";
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
        return input.length() <= 8 && Character.isDigit(input.charAt(5));
    }

    /**
     * Returns the date of the task (event/deadline)
     *
     * @param tempEvent An array of string that contains the userInput
     *                  split into two parts
     * @return LocalDate The date of the task
     */
    public static LocalDateTime getDate(String[] tempEvent, String time) {
        String[] splitDateAndDescription = tempEvent[1].split(" ");
        String date = splitDateAndDescription[1];
        String[] splitDateComponents = date.split("/");
        String year = splitDateComponents[2];
        String month = splitDateComponents[1];
        String currentDate = splitDateComponents[0];
        if (currentDate.length() == 1) {
            currentDate = "0" + currentDate;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        String finalDateFormat = year + "-" + month + "-" + currentDate;
        String finalDateTimeFormat = finalDateFormat + " " + time;
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime taskDateTime = LocalDateTime.parse(finalDateTimeFormat, datePattern);
        return taskDateTime;
    }

    /**
     * Returns a string that should be output when the user types the
     * 'schedule on' command
     *
     * @param input The input entered by the user
     * @param t The task list that stores the current tasks
     * @return the string that should be output to the user
     */
    public static String parseScheduleOnCommand(String input, TaskList t) {
        if (t.size() == 0) {
            return "There are no existing tasks!";
        } else {
            String stringOfDate = input.substring(12);
            LocalDate scheduleOnDate = getScheduleDate(stringOfDate);
            String returnString = "Here are the list of tasks scheduled on " + stringOfDate + ":" + "\n";
            int counter = 0;
            for (Task task : t.getTaskList()) {
                if (task.getType().equals("E")) {
                    String[] splitDescriptionAndDate = task.toString().split("at:");
                    String evenDateAndTime = splitDescriptionAndDate[1].substring(1);
                    String eventDateWithoutTime = evenDateAndTime.substring(0, evenDateAndTime.length() - 6);
                    LocalDate eventDate = LocalDate.parse(eventDateWithoutTime);
                    if (eventDate.compareTo(scheduleOnDate) == 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else if (task.getType().equals("D")) {
                    String[] splitEventDescriptionAndDate = task.toString().split("by:");
                    String deadlineDateAndTime = splitEventDescriptionAndDate[1].substring(1);
                    String deadlineDateWithoutTime = deadlineDateAndTime.substring(0, deadlineDateAndTime.length() - 6);
                    LocalDate deadlineDate = LocalDate.parse(deadlineDateWithoutTime);
                    if (deadlineDate.compareTo(scheduleOnDate) == 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else {
                    continue;
                }
            }
            return returnString;
        }
    }

    /**
     * Returns a string that should be output when the user types the
     * 'schedule until' command
     *
     * @param input The input entered by the user
     * @param t The task list that stores the current tasks
     * @return the string that should be output to the user
     */
    public static String parseScheduleUntilCommand(String input, TaskList t) {
        if (t.size() == 0) {
            return "There are no existing tasks!";
        } else {
            String stringOfDate = input.substring(15);
            LocalDate scheduleOnDate = getScheduleDate(stringOfDate);
            String returnString = "Here are the list of tasks scheduled until " + stringOfDate + ":" + "\n";
            int counter = 0;
            for (Task task : t.getTaskList()) {
                if (task.getType().equals("E")) {
                    String[] splitDescriptionAndDate = task.toString().split("at:");
                    String dateAndTime = splitDescriptionAndDate[1].substring(1);
                    String eventDateString = dateAndTime.substring(0, dateAndTime.length() - 6);
                    LocalDate eventOnDate = LocalDate.parse(eventDateString);
                    if (eventOnDate.compareTo(scheduleOnDate) < 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else if (task.getType().equals("D")) {
                    String[] split = task.toString().split("by:");
                    String dateAndTime = split[1].substring(1);
                    String deadlineDateString = dateAndTime.substring(0, dateAndTime.length() - 6);
                    LocalDate deadlineByDate = LocalDate.parse(deadlineDateString);
                    if (deadlineByDate.compareTo(scheduleOnDate) < 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else {
                    continue;
                }
            }
            return returnString;
        }
    }

    /**
     * Returns a string that should be output when the user types the
     * 'schedule after' command
     *
     * @param input The input entered by the user
     * @param t The task list that stores the current tasks
     * @return the string that should be output to the user
     */
    public static String parseScheduleAfterCommand(String input, TaskList t) {
        if (t.size() == 0) {
            return "There are no existing tasks!";
        } else {
            String stringOfDate = input.substring(15);
            LocalDate scheduleAfterDate = getScheduleDate(stringOfDate);
            String returnString = "Here are the list of tasks scheduled after " + stringOfDate + ":" + "\n";
            int counter = 0;
            for (Task task : t.getTaskList()) {
                if (task.getType().equals("E")) {
                    String[] splitDescriptionAndDate = task.toString().split("at:");
                    String eventDateAndTime = splitDescriptionAndDate[1].substring(1);
                    String eventDateWithoutTime = eventDateAndTime.substring(0, eventDateAndTime.length() - 6);
                    LocalDate eventDate = LocalDate.parse(eventDateWithoutTime);
                    if (eventDate.compareTo(scheduleAfterDate) > 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else if (task.getType().equals("D")) {
                    String[] splitDeadlineDescriptionAndDate = task.toString().split("by:");
                    String deadlineDateAndTime = splitDeadlineDescriptionAndDate[1].substring(1);
                    String deadlineDateWithoutTime = deadlineDateAndTime.substring(0, deadlineDateAndTime.length() - 6);
                    LocalDate deadlineByDate = LocalDate.parse(deadlineDateWithoutTime);
                    if (deadlineByDate.compareTo(scheduleAfterDate) > 0) {
                        returnString = returnString + Integer.valueOf(counter + 1) + ". " + task.toString() + "\n";
                        counter += 1;
                    }
                } else {
                    continue;
                }
            }
            return returnString;
        }
    }

    /**
     * Returns the date entered by the user as a LocalDate
     *
     * @param stringOfDate The date that has been entered by the user
     * @return The date entered by the user after being converted to a
     * LocalDate object
     */
    public static LocalDate getScheduleDate(String stringOfDate) {
        String[] dateComponents = stringOfDate.split("/");
        String year = dateComponents[2];
        String month = dateComponents[1];
        String currentDate = dateComponents[0];
        if (currentDate.length() == 1) {
            currentDate = "0" + currentDate;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        String finalDateFormat = year + "-" + month + "-" + currentDate;
        LocalDate scheduleOnDate = LocalDate.parse(finalDateFormat);
        return scheduleOnDate;
    }

    public static String parseHelpCommand() {
        return "Here are the list of commands that you can type: " + "\n" +
                "'list' - to view current tasks" + "\n" +
                "'done <index>' - to mark a task as done" + "\n" +
                "'delete <index>' - to delete a task from list" + "\n" +
                "'todo <description of task>' - add a todo task" + "\n" +
                "deadline <description of task> /by <date in dd/mm/yyyy> <time in hhmm> - add a deadline task" + "\n" +
                "event <description of task> /at <date in dd/mm/yyyy> <time in hhmm> - add an event task" + "\n" +
                "'find <keyword>' - find any task(s) that have keyword in their description" + "\n" +
                "'schedule on <date in dd/mm/yyyy>' - view the schedule on a particular date" + "\n" +
                "'schedule until <date in dd/mm/yyyy>' - view the schedule until a particular date" + "\n" +
                "'schedule after <date in dd/mm/yyyy>' - view the schedule after a particular date" + "\n" +
                "'bye' - to exit Duke";
    }
}

