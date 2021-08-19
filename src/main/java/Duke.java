import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void printLogo() {
        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }


    private void greet() {
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    private void run() {
        while (true) {
            String[] userInput = this.input.nextLine().split(" ", 2);
            String command = userInput[0];

            if (command.equals("bye")) {
                this.exit();
                break;
            } else if (command.equals("list")) {
                this.showList();
            } else if (command.equals("todo")) {
                this.addTodo(userInput[1]);
            } else if (command.equals("event")) {
                String[] splits = userInput[1].split(" /at ");
                this.addEvent(splits[0], splits[1]);
            } else if (command.equals("deadline")) {
                String[] splits = userInput[1].split(" /by ");
                this.addDeadline(splits[0], splits[1]);
            } else if (command.equals("done")) {
                int taskNum = Integer.parseInt(userInput[1]);
                this.completeTask(taskNum);
            } else {
                this.unknownCommand();
            }
        }
    }



    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }


    private void showList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }


    private void addTodo(String description) {
        this.list.add(new Todo(description));
        System.out.println("\tadded todo: " + description);
        System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
    }


    private void addDeadline(String description, String time) {
        this.list.add(new Deadline(description, time));
        System.out.println("\tadded deadline: " + description);
        System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
    }


    private void addEvent(String description, String duration) {
        this.list.add(new Event(description, duration));
        System.out.println("\tadded event: " + description);
        System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
    }


    private void completeTask(int taskNum) {
        this.list.get(taskNum - 1).markAsDone();
        System.out.println("\tI've marked this task as done!");
        System.out.printf("\t\t%s\n\n", this.list.get(taskNum - 1));
    }


    private void unknownCommand() {
        System.out.println("\tPlease check that you have keyed in a correct command!\n");
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLogo();
        duke.greet();
        duke.run();
    }
}