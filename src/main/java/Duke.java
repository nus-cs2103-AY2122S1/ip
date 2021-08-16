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

    private void add(String t) {
        System.out.println("-----------------------------------------\n" +
                String.format("added: %s\n", t) +
                "-----------------------------------------\n");
        Task newTask = new Task(t, false);
        this.tl.addTask(newTask);
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
        String t;
        while (sc.hasNextLine()) {
            t = sc.nextLine();
            String[] items = t.split(" ");

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
                default:
                    this.add(t);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
