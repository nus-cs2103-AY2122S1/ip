import java.util.Scanner;

public class DukeCore {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";
    static String INVALID_INPUT = space + "Invalid input. Please follow the given format!";

    private final Scanner scanner;
    private final TaskList taskList;

    public DukeCore() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public void greet() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(space + "Hello! I'm Duke\n" + space + "What can I do for you?");
        System.out.println(divider + "\n");
    }

    public void dismiss() {
        displayText(space + "Bye. Hope to see you again soon!");
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public boolean parseLine(String input) {
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("todo ")) {
            String description = parseTodo(input);
            if (description != null) {
                addTask(new Todo(description));
            } else {
                displayText(INVALID_INPUT);
            }
        } else if (input.startsWith("deadline ")) {
            String[] taskDetails = parseDeadline(input);
            if (taskDetails != null) {
                addTask(new Deadline(taskDetails[0], taskDetails[1]));
            } else {
                displayText(INVALID_INPUT);
            }
        } else if (input.startsWith("event ")) {
            String[] taskDetails = parseEvent(input);
            if (taskDetails != null) {
                addTask(new Event(taskDetails[0], taskDetails[1]));
            } else {
                displayText(INVALID_INPUT);
            }
        } else if (input.length() >= 6 && input.startsWith("done ")) {
            if (isValidNum(input.substring(5))) {
                doneTask(Integer.parseInt(input.substring(5)));
            } else {
                displayText(INVALID_INPUT);
            }
        } else {
            displayText(INVALID_INPUT);
        }
        return true;
    }

    public boolean isValidNum(String s) {
        try {
            int index = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String parseTodo(String s) {
        String temp = s.substring(5);
        temp = temp.trim();
        return temp.equals("") ? null : temp;
    }

    public String[] parseDeadline(String s) {
        String temp = s.substring(9);
        temp = temp.trim();
        String description;
        String by;
        int m = temp.lastIndexOf("/by ");
        if (m == -1) {
            return null;
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                return null;
            }
            by = temp.substring(m);
            by = by.substring(4);
            by = by.trim();
            if (by.equals("")) {
                return null;
            }
        }
        return new String[]{description, by};
    }

    public String[] parseEvent(String s) {
        String temp = s.substring(6);
        temp = temp.trim();
        String description;
        String at;
        int m = temp.lastIndexOf("/at ");
        if (m == -1) {
            return null;
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                return null;
            }
            at = temp.substring(m);
            at = at.substring(4);
            at = at.trim();
            if (at.equals("")) {
                return null;
            }
        }
        return new String[]{description, at};
    }

    public void addTask(Task t) {
        if (taskList.addTask(t)) {
            displayText(space + "Got it. I've added this task: \n"
                    + space + "  " + t.getDescriptionWithStatus() + "\n"
                    + space + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");
        } else {
            System.exit(1);
        }
    }

    public void doneTask(int taskIndex) {
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            if (t.getIsDone()) {
                displayText(space + "You have already done this task!");
            } else {
                t.markAsDone();
                displayText(space + "Nice! I've marked this task as done: \n"
                        + space + "  " + t.getDescriptionWithStatus());
            }
        } catch (IndexOutOfBoundsException ex) {
            displayText(space + "Oops, the task doesn't seem to exist.");
        }
    }

    public void listTasks() {
        displayText(taskList.printList());
    }

    public void displayText(String text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider + "\n");
    }

}
