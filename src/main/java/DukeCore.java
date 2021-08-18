import java.util.Scanner;

public class DukeCore {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";
    static String INVALID_INPUT = space + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private final Scanner scanner;
    private TaskList taskList;
    private final DukeDataHandler dataHandler;

    public DukeCore() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
        this.dataHandler = new DukeDataHandler();
    }

    public void retrieveData() {
        this.taskList = dataHandler.retrieveTaskList();
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
        input = input.trim();  // to remove all leading and trailing space of user's input
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("todo")) {
            try {
                String description = parseTodo(input);
                addTask(new Todo(description));
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                if (errorMessage.equals("wrong input format")) {
                    displayText(INVALID_INPUT);
                } else if (errorMessage.equals("empty todo description")) {
                    displayText(space + "☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] taskDetails = parseDeadline(input);
                addTask(new Deadline(taskDetails[0], taskDetails[1]));
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                switch (errorMessage) {
                case "wrong input format":
                    displayText(INVALID_INPUT);
                    break;
                case "empty deadline description":
                    displayText(space + "☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                case "empty deadline by":
                    displayText(space + "☹ OOPS!!! The deadline of a deadline cannot be empty.");
                    break;
                }
            }
        } else if (input.startsWith("event")) {
            try {
                String[] taskDetails = parseEvent(input);
                addTask(new Event(taskDetails[0], taskDetails[1]));
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                switch (errorMessage) {
                case "wrong input format":
                    displayText(INVALID_INPUT);
                    break;
                case "empty event description":
                    displayText(space + "☹ OOPS!!! The description of an event cannot be empty.");
                    break;
                case "empty event at":
                    displayText(space + "☹ OOPS!!! The start/end time of an event cannot be empty.");
                    break;
                }
            }
        } else if (input.length() >= 6 && input.startsWith("done ")) {
            if (isValidNum(input.substring(5))) {
                doneTask(Integer.parseInt(input.substring(5).trim()));
            } else {
                displayText(INVALID_INPUT);
            }
        } else if (input.length() >= 8 && input.startsWith("delete ")) {
            if (isValidNum(input.substring(7))) {
                deleteTask(Integer.parseInt(input.substring(7).trim()));
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
            Integer.parseInt(s.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String parseTodo(String s) throws DukeException {
        String temp;
        try {
            if (s.charAt(4) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty todo description");
        }
        temp = temp.trim();
        if (temp.equals("")) {
            throw new DukeException("empty todo description");
        }
        return temp;
    }

    public String[] parseDeadline(String s) throws DukeException {
        String temp;
        String description;
        String by;

        try {
            if (s.charAt(8) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(9);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty deadline description");
        }
        temp = temp.trim();

        int m = temp.lastIndexOf("/by");
        if (m == -1) {
            throw new DukeException("wrong input format");
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                throw new DukeException("empty deadline description");
            }

            by = temp.substring(m);
            try {
                if (by.charAt(3) != ' ') {
                    throw new DukeException("wrong input format");
                }
                by = by.substring(4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("empty deadline by");
            }
            by = by.trim();
            if (by.equals("")) {
                throw new DukeException("empty deadline by");
            }
        }
        return new String[]{description, by};
    }

    public String[] parseEvent(String s) {
        String temp;
        String description;
        String at;

        try {
            if (s.charAt(5) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(6);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty event description");
        }
        temp = temp.trim();

        int m = temp.lastIndexOf("/at");
        if (m == -1) {
            throw new DukeException("wrong input format");
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                throw new DukeException("empty event description");
            }

            at = temp.substring(m);
            try {
                if (at.charAt(3) != ' ') {
                    throw new DukeException("wrong input format");
                }
                at = at.substring(4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("empty event at");
            }
            at = at.trim();
            if (at.equals("")) {
                throw new DukeException("empty event at");
            }
        }
        return new String[]{description, at};
    }

    public void addTask(Task t) {
        if (taskList.addTask(t)) {
            displayText(space + "Got it. I've added this task: \n"
                    + space + "  " + t.getDescriptionWithStatus() + "\n"
                    + space + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");
            dataHandler.storeTaskList(taskList);
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
                dataHandler.storeTaskList(taskList);
            }
        } catch (IndexOutOfBoundsException ex) {
            displayText(space + "Oops, the task doesn't seem to exist.");
        }
    }

    public void deleteTask(int taskIndex) {
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            String description = t.getDescriptionWithStatus();
            if (taskList.deleteTask(taskIndex - 1)) {
                displayText(space + "Noted. I've removed this task: \n"
                        + space + "  " + description + "\n"
                        + space + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");
                dataHandler.storeTaskList(taskList);
            } else {
                System.exit(1);
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
