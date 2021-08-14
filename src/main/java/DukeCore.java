import java.util.Scanner;

public class DukeCore {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";

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
        } else if (input.length() >= 6 && input.startsWith("done ")) {
            if (isValidNum(input.substring(5))) {
                doneTask(Integer.parseInt(input.substring(5)));
            } else {
                this.addTask(new Task(input));
            }
        } else {
            this.addTask(new Task(input));
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

    public void addTask(Task t) {
        if (taskList.addTask(t)) {
            displayText(space + "added: " + t.getDescription());
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
