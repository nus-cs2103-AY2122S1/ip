import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private static final String LINE = "     ________________________________________\n"; // 5 spaces, 40 dashes
    private static final String INDENT = "     "; // 5 spaces
    private List<Task> taskList;

    private enum taskKind {
        TODOS,
        DEADLINES,
        EVENTS
    }

    private boolean isRunning;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greet();
        duke.converse();
    }

    public Duke() {
        taskList = new ArrayList<>(100);
        isRunning = true;
    }

    public void greet() {
        String greeting = LINE
            + INDENT + "Hello! I'm Duke\n"
            + INDENT + "What can I do for you?\n"
            + LINE;
        System.out.println(greeting);
    }

    public void converse() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String userInput = scanner.nextLine();
            String[] slashSplitInput = userInput.split("/", 2);
            String[] spaceSplitInput = slashSplitInput[0].split(" ", 2); // Everything before the slash, split again by space

            try {
                if (userInput.equals("bye")) { // Exit
                    System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                    isRunning = false;
                } else if (userInput.equals("list")) {
                    listTask(taskList);
                } else if (spaceSplitInput[0].equals("done")) {
                    doneTask(spaceSplitInput, taskList);
                } else if (spaceSplitInput[0].equals("delete")) {
                    deleteTask(spaceSplitInput, taskList);
                } else if (spaceSplitInput[0].equals("todo")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.TODOS);
                } else if (spaceSplitInput[0].equals("deadline")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.DEADLINES);
                } else if (spaceSplitInput[0].equals("event")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.EVENTS);
                } else {
                    // User inputs unrecognized commands
                    throw new DukeException(LINE + INDENT + "My intelligence has not evolved to understand this command :(\n" + LINE);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println(LINE + INDENT + "Please enter a valid integer for task number!\n" + LINE);
                continue;
            } catch (DateTimeParseException e) {
                System.out.println(LINE
                    + INDENT + "Please enter date in the valid format:\n"
                    + INDENT + "Deadlines: yyyy-mm-dd hh:mm\n"
                    + INDENT + "Events: yyyy-mm-dd hh:mm-hh:mm\n"
                    + LINE);
                continue;
            }
        }
    }

    private void listTask(List<Task> taskList) {
        System.out.println(LINE + INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println(INDENT + (i + 1) + "." + currTask.toString());
        }
        System.out.println(LINE);
    }

    private void doneTask(String[] spaceSplitInput, List<Task> taskList) throws DukeException, NumberFormatException {
        if (spaceSplitInput.length < 2) {
            // No task number entered
            throw new DukeException(LINE + INDENT + "Done Task number is missing!\n" + LINE);
        }
        int doneTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int
        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException(LINE + INDENT + "Task number entered out of range!\n" + LINE);
        }

        Task doneTask = taskList.get(doneTaskNo - 1);
        doneTask.markAsDone();
        System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
            + INDENT + INDENT + doneTask.toString() + "\n"
            + LINE);
    }

    private void deleteTask(String[] spaceSplitInput, List<Task> taskList) throws DukeException, NumberFormatException {
        if (spaceSplitInput.length < 2) {
            // No task number entered
            throw new DukeException(LINE + INDENT + "Delete task number is missing!\n" + LINE);
        }
        int deleteTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int
        if (deleteTaskNo < 1 || deleteTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException(LINE + INDENT + "Task number entered out of range!\n" + LINE);
        }

        Task deleteTask = taskList.get(deleteTaskNo - 1);
        taskList.remove(deleteTaskNo - 1);
        System.out.println(LINE + INDENT + "Noted. I've removed this task:\n"
            + INDENT + INDENT + deleteTask.toString() + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
            + LINE);
    }

    // Input format for deadline: /by yyyy-mm-dd hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME)
    // Input format for event: /at yyyy-mm-dd hh:mm-hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME, dash, ISO_LOCAL_TIME)
    private void addTask(String[] spaceSplitInput, String[] slashSplitInput, List<Task> taskList, taskKind currTaskKind) throws DukeException, DateTimeParseException {
        Task currTask = null;

        switch (currTaskKind) {
            case TODOS:
                if (spaceSplitInput.length < 2) {
                    // Tudo has no description. If has, spaceSplitInput has length 2.
                    throw new DukeException(LINE + INDENT + "Todo description cannot be empty!\n" + LINE);
                }
                currTask = new ToDos(spaceSplitInput[1].trim()); // description trimmed of trailing white space behind
                break;
            case DEADLINES:
                if (spaceSplitInput.length < 2) {
                    // Deadline has no description
                    throw new DukeException(LINE + INDENT + "Deadline description cannot be empty!\n" + LINE);
                }
                if (slashSplitInput.length < 2) {
                    // Deadline has no slash (thus no date)
                    throw new DukeException(LINE + INDENT + "Deadline must have a date and time written after a slash!\n" + LINE);
                }

                String[] DdlDateTimeArr = slashSplitInput[1].split(" ", 3);
                if (DdlDateTimeArr.length < 3) {
                    // If there're less than 3 words behind slash e.g. No "by", nothing behind "by", etc.
                    throw new DukeException(LINE + INDENT + "Deadline date must be in the format of 'by date time'!" + "\n" + LINE);
                }
                LocalDate DdlDate = LocalDate.parse(DdlDateTimeArr[1]); // Throws DateTimeParseException if date cannot be parsed
                LocalTime DdlTime = LocalTime.parse(DdlDateTimeArr[2]);
                currTask = new Deadlines(spaceSplitInput[1].trim(), DdlDate, DdlTime);
                break;
            case EVENTS:
                if (spaceSplitInput.length < 2) {
                    // Event has no description
                    throw new DukeException(LINE + INDENT + "Event description cannot be empty!\n" + LINE);
                }
                if (slashSplitInput.length < 2) {
                    // Event has no slash (thus no date)
                    throw new DukeException(LINE + INDENT + "Event must has a date written after a slash!\n" + LINE);
                }

                String[] eventDateTimeArr = slashSplitInput[1].split(" ", 3);
                if (eventDateTimeArr.length < 3) {
                    // If there're less than 3 words behind slash e.g. No "at", nothing behind "at", etc.
                    throw new DukeException(LINE + INDENT + "Event date must be in the format of 'by date time-time'!" + "\n" + LINE);
                }
                LocalDate eventDate = LocalDate.parse(eventDateTimeArr[1]); // Throws DateTimeParseException if date cannot be parsed

                String[] eventTimes = eventDateTimeArr[2].split("-", 2); // split the start and end time
                if (eventTimes.length < 2) {
                    // There's no dash for start and end time
                    throw new DukeException(LINE + INDENT + "Event date must have a start and end time separated by a dash" + "\n" + LINE);
                }

                LocalTime eventStartTime = LocalTime.parse(eventTimes[0]);
                LocalTime eventEndTime = LocalTime.parse(eventTimes[1]);
                currTask = new Events(spaceSplitInput[1].trim(), eventDate, eventStartTime, eventEndTime);
                break;
        }

        assert currTask != null;
        taskList.add(currTask);
        System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
            + INDENT + INDENT + currTask.toString() + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
            + LINE);
    }
}



