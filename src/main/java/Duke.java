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
        String greetings =
                "-----------------------------------------\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "-----------------------------------------\n";
        System.out.println("Hello from\n" + logo + greetings);
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

    private void add(String[] t) {
        Task newTask = null;
        switch (t[0]) {
            case "todo":
//                System.out.println("in todo");
                newTask = new Todo(t[1]);
                break;
            case "deadline":
//                System.out.println("in dl");
                newTask = new Deadline(t[1], t[2]);
                break;
            case "event":
//                System.out.println("in event");
                newTask = new Event(t[1], t[2]);
                break;
        }
        this.tl.addTask(newTask);
        System.out.println("-----------------------------------------\n" +
                "Got it. I've added this task: \n" +
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

    private void run() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        String t;
        while (sc.hasNextLine()) {
            t = sc.nextLine();
            String[] items = p.parse(t);

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
                default:
                    System.out.println("out of bounds mate");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
