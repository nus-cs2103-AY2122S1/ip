import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void printLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }


    private void checkInput() {
        while (true) {
            String userInput = this.input.nextLine();

            if (userInput.equals("bye")) {
                this.exit();
                break;
            }
            if (userInput.equals("list")) {
                this.showList();
                continue;
            }

            int index = userInput.indexOf(" ");
            String command = userInput.substring(0, index);
            String arg = userInput.substring(index + 1);

            if (command.equals("todo")) {
                this.addTodo(arg);
                continue;
            }
            if (command.equals("event")) {
                String[] splits = arg.split(" /at ");
                this.addEvent(splits[0], splits[1]);
                continue;
            }
            if (command.equals("deadline")) {
                String[] splits = arg.split(" /by ");
                this.addDeadline(splits[0], splits[1]);
                continue;
            }
            if (command.equals("done")) {
                int taskNum = Integer.parseInt(userInput.substring(5));
                this.completeTask(taskNum);
                continue;
            }
        }
    }


    private void greet() {
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
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


    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }


    private void completeTask(int taskNum) {
        this.list.get(taskNum - 1).markAsDone();
        System.out.println("\tI've marked this task as done!");
        System.out.printf("\t\t%s\n\n", this.list.get(taskNum - 1));
    }


    private void showList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLogo();
        duke.greet();
        duke.checkInput();
    }
}