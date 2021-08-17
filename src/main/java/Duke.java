import java.util.Scanner;

public class Duke {

    private TaskList tl;

    public Duke() {
        this.tl = new TaskList();
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private void echo(String t) {
        System.out.println("-----------------------------------------\n" +
                String.format("%s\n", t) +
                "-----------------------------------------\n");
    }

    private void exit() {
        String exitMessage =
                "-----------------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "-----------------------------------------\n";
        System.out.println(exitMessage);
        System.exit(0);
    }

    private void add(String[] t) throws DukeException {
        Task newTask = null;
        switch (t[0]) {
            case "todo":
                if (t[1].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    newTask = new Todo(t);
                }
                break;
            case "deadline":
                if (t[1].equals("") || t[2].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
                } else {
                    newTask = new Deadline(t);
                }
                break;
            case "event":
                if (t[1].equals("") || t[2].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description or scheduled time of an event cannot be empty.");
                } else {
                    newTask = new Event(t);
                }
                break;
        }
        this.tl.addTask(newTask);
        System.out.println("-----------------------------------------\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                String.format("Now you have %s tasks in the list.\n", this.tl.getLength()) +
                "-----------------------------------------\n");
    }

    private Task getTaskByIndex(int index) {
        return this.tl.getTaskByIndex(index);
    }

    private void markDone(int itemNum) {
        this.tl.markDone(itemNum);
        System.out.println("-----------------------------------------\n" +
                "Nice! I've marked this task as done:\n" +
                this.getTaskByIndex(itemNum - 1).toString() +
                "-----------------------------------------\n");
    }

    private void deleteTask(String[] items) throws DukeException {
        if (items[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The task's number cannot be empty");
        } else {
            int itemNum = Integer.parseInt(items[1]);
            Task toBeDeleted = this.getTaskByIndex(itemNum - 1);
            String taskName = toBeDeleted.toString();
            this.tl.deleteTask(itemNum - 1);
            System.out.println("-----------------------------------------\n" +
                    "Noted. I've removed this task:\n" +
                    taskName +
                    String.format("Now you have %s tasks in the list.\n", this.tl.getLength()) +
                    "-----------------------------------------\n");
        }
    }

    private void run() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        String t;
        while (sc.hasNextLine()) {
            t = sc.nextLine();
            String[] items = p.parse(t);

            try {
                switch (items[0]) {
                    case "bye":
                        this.exit();
                        break;
                    case "list":
                        System.out.println(this.tl.toString());
                        break;
                    case "done":
                        this.markDone(Integer.parseInt(items[1]));
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        this.add(items);
                        break;
                    case "delete":
                        this.deleteTask(items);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
