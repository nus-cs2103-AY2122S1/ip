import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum DukeAction {
        EXIT, PRINT_LIST, MARK_DONE, TODO, DEADLINE, EVENT
    }

    // Constant Strings
    private static final String HorizontalLine = "____________________________________________________________";
    private static final String LIndent = "    ";

    private static final String ExitWord = "bye";
    private static final String ListWord = "list";
    private static final String MarkWord = "done ";
    private static final String ToDoWord = "todo ";
    private static final String DeadlineWord = "deadline ";
    private static final String DeadlineWord_By = " /by ";
    private static final String EventWord = "event ";
    private static final String EventWord_At = " /at ";

    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<Task>();

        // print logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print welcome words
        PrintWithIndent(HorizontalLine);
        PrintWithIndent("Hello! I'm Duke");
        PrintWithIndent("What can I do for you?");
        PrintWithIndent(HorizontalLine);

        // Echo loop till exit word is entered
        for (;;) {
            String userInput = sc.nextLine();
            try {
                PrintWithIndent(HorizontalLine);
                DukeAction dukeAction = stringToDukeAction(userInput);

                switch (dukeAction) {
                    case EXIT:
                        PrintWithIndent("Bye. Hope to see you again soon!");
                        break;
                    case PRINT_LIST:
                        PrintList();
                        break;
                    case MARK_DONE:
                        Task t = taskList.get(Integer.parseInt(userInput.substring(MarkWord.length())) - 1);
                        t.isDone = true;
                        PrintWithIndent("Nice! I've marked this task as done: ");
                        PrintWithIndent("  " + t);
                        break;
                    case TODO:
                        ToDo toDo = new ToDo(userInput);
                        taskList.add(toDo);
                        PrintWithIndent("Got it. I've added this task:");
                        PrintWithIndent("  " + toDo);
                        PrintWithIndent("Now you have " + taskList.size() + " tasks in the list.");
                        break;
                    case DEADLINE:
                        String[] strArr = ParseDeadlineString(userInput);
                        if (strArr != null) {
                            Deadline deadline = new Deadline(strArr[0], strArr[1]);
                            taskList.add(deadline);
                            PrintWithIndent("Got it. I've added this task:");
                            PrintWithIndent("  " + deadline);
                            PrintWithIndent("Now you have " + taskList.size() + " tasks in the list.");
                            break;
                        }
                    case EVENT:
                        String[] strArr_ = ParseEventString(userInput);
                        if (strArr_ != null) {
                            Event event = new Event(strArr_[0], strArr_[1]);
                            taskList.add(event);
                            PrintWithIndent("Got it. I've added this task:");
                            PrintWithIndent("  " + event);
                            PrintWithIndent("Now you have " + taskList.size() + " tasks in the list.");
                            break;
                        }
                }
                PrintWithIndent(HorizontalLine);

                if (dukeAction.equals(DukeAction.EXIT)) {
                    // Exit the loop
                    break;
                }
            } catch (DukeException e) {
                PrintWithIndent(e.getMessage());
                PrintWithIndent(HorizontalLine);
            }
        }
    }

    private static void PrintWithIndent(String s) {
        System.out.println(LIndent + s);
    }

    private static void PrintList() {
        for (int i = 0; i < taskList.size(); i++)
            PrintWithIndent((i+1) + "." + taskList.get(i));
    }

    public static DukeAction stringToDukeAction(String s) throws DukeException {
        // Remove all leading whitespaces
        s = s.stripLeading();
        if (s.equals(ExitWord)) {
            return DukeAction.EXIT;
        }
        else if (s.equals(ListWord)) {
            return DukeAction.PRINT_LIST;
        }
        else if (IsMarkDown(s)) {
            return DukeAction.MARK_DONE;
        }
        else if (s.startsWith(ToDoWord)) {
            if (s.length() == ToDoWord.length())
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            else
                return DukeAction.TODO;
        }
        else if (s.startsWith(DeadlineWord)) {
            return DukeAction.DEADLINE;
        }
        else if (s.startsWith(EventWord)) {
            return DukeAction.EVENT;
        }
        else if (s.equals("todo") || s.equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a " + s + " cannot be empty.");
        }
        else if (s.equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean IsMarkDown(String s) throws DukeException {
        if (s.length() > MarkWord.length() && s.startsWith(MarkWord)) {
            try {
                int taskIndex = Integer.parseInt(s.substring(MarkWord.length()));
                if (taskIndex >= 1 && taskIndex <= taskList.size())
                    return true;
                else
                    throw new DukeException("OOPS!!! The task index is out of range.\n" + LIndent +
                            "Now you have " + taskList.size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! done should be followed with a number.");
            }
        }
        return false;
    }

    private static String[] ParseDeadlineString(String s) throws DukeException {
        if (s.length() <= DeadlineWord.length())
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        else if (!s.substring(DeadlineWord.length()).contains(DeadlineWord_By))
            throw new DukeException("OOPS!!! Wrong Deadline format. Correct format is:\n" + LIndent +
                    "deadline {task description} /by {due time}\n" + LIndent +
                    "Example: deadline return book /by Sunday");

        s = s.substring(DeadlineWord.length());
        int index = s.indexOf(DeadlineWord_By, 0);
        return index == -1
                ? null
                : new String[] {s.substring(0, index), s.substring(index + DeadlineWord_By.length())};
    }

    private static String[] ParseEventString(String s) throws DukeException {
        if (s.length() <= EventWord.length())
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        else if (!s.substring(EventWord.length()).contains(EventWord_At))
            throw new DukeException("OOPS!!! Wrong Event format. Correct format is:\n" + LIndent +
                    "event {task description} /at {time period}\n" + LIndent +
                    "Example: event project meeting /at Mon 2-4pm");

        s = s.substring(EventWord.length());
        int index = s.indexOf(EventWord_At, 0);
        return index == -1
                ? null
                : new String[] {s.substring(0, index), s.substring(index + EventWord_At.length())};
    }
}
