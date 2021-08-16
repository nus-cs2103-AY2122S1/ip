import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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
            String[] spaceSplitInput = slashSplitInput[0].split(" ", 2); // Contains everything before the slash, split by space

            try {
                if (userInput.equals("bye")) {
                    // Exit
                    System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                    isRunning = false;
                } else if (userInput.equals("list")) {
                    // List all existing tasks
                    System.out.println(LINE + INDENT + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task currTask = taskList.get(i);
                        System.out.println(INDENT + (i + 1) + "." + currTask.toString());
                    }
                    System.out.println(LINE);
                } else if (spaceSplitInput[0].equals("done")) {
                    // Mark task as done
                    try {
                        if (spaceSplitInput.length < 2) {
                            // No task number entered
                            throw new MissingTaskNoException(LINE + INDENT + "Done Task number is missing!\n" + LINE);
                        }
                        int doneTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int
                        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
                            // Task No entered out of range
                            throw new TaskNoOutOfRangeException(LINE + INDENT + "Task number entered out of range!\n" + LINE);
                        }
                        Task doneTask = taskList.get(doneTaskNo - 1);
                        doneTask.markAsDone();
                        System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
                            + INDENT + INDENT + doneTask.toString() + "\n"
                            + LINE);
                    } catch (NumberFormatException e) {
                        System.out.println(LINE + INDENT + "Please enter a valid integer for task number!\n" + LINE);
                        continue;
                    } catch (TaskNoOutOfRangeException | MissingTaskNoException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else if (spaceSplitInput[0].equals("todo")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.TODOS);
                } else if (spaceSplitInput[0].equals("deadline")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.DEADLINES);
                } else if (spaceSplitInput[0].equals("event")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.EVENTS);
                } else {
                    // User inputs unrecognized commands
                    throw new UnrecognizedInputException(LINE + INDENT + "My intelligence has not evolved to understand this command :(\n" + LINE);
                }
            } catch (UnrecognizedInputException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private void addTask(String[] spaceSplitInput, String[] slashSplitInput, List<Task> taskList, taskKind currTaskKind) {
        try {
            Task currTask = null;

            switch (currTaskKind) {
                case TODOS:
                    if (spaceSplitInput.length < 2) {
                        // Tudo has no description. If has, spaceSplitInput has length 2.
                        throw new EmptyDescriptionException(LINE + INDENT + "Todo description cannot be empty!\n" + LINE);
                    }
                    // Only if no exception is thrown
                    currTask = new ToDos(spaceSplitInput[1]);
                    break;
                case DEADLINES:
                    if (spaceSplitInput.length < 2) {
                        // Deadline has no description
                        throw new EmptyDescriptionException(LINE + INDENT + "Deadline description cannot be empty!\n" + LINE);
                    }
                    if (slashSplitInput.length < 2) {
                        // Deadline has no slash (thus no date)
                        throw new MissingDateException(LINE + INDENT + "Deadline must has a date written after a slash!\n" + LINE);
                    }
                    if (slashSplitInput[1].split(" ", 2).length < 2) {
                        // If there're less than 2 words behind slash i.e. No "by" or nothing behind "by"
                        throw new InvalidDateException(LINE + INDENT + "Deadline date must be in the format of 'by date'!" + "\n" + LINE);
                    }
                    // Only if no exceptions is thrown:
                    String taskTimeDDL = slashSplitInput[1].split(" ", 2)[1]; // Extract taskTime by discarding the "by"
                    currTask = new Deadlines(spaceSplitInput[1], taskTimeDDL);
                    break;
                case EVENTS:
                    if (spaceSplitInput.length < 2) {
                        // Event has no description
                        throw new EmptyDescriptionException(LINE + INDENT + "Event description cannot be empty!\n" + LINE);
                    }
                    if (slashSplitInput.length < 2) {
                        // Event has no slash (thus no date)
                        throw new MissingDateException(LINE + INDENT + "Event must has a date written after a slash!\n" + LINE);
                    }
                    if (slashSplitInput[1].split(" ", 2).length < 2) {
                        // If there're less than 2 words behind slash i.e. No "at" or nothing behind "at"
                        throw new InvalidDateException(LINE + INDENT + "Event date must be in the format of 'at date'!" + "\n" + LINE);
                    }
                    // Only if no exception is thrown
                    String taskTimeEvent = slashSplitInput[1].split(" ", 2)[1];
                    currTask = new Events(spaceSplitInput[1], taskTimeEvent);
                    break;
            }

            assert currTask != null;
            taskList.add(currTask);
            System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
                + INDENT + INDENT + currTask.toString() + "\n"
                + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
                + LINE);

        } catch (EmptyDescriptionException | MissingDateException | InvalidDateException e) {
            System.out.println(e.getMessage());
            // while loop in converse continues
        }
    }
}
