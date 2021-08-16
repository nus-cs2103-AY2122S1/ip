import java.util.Scanner;

public class Duke {
    private static boolean isRunning = false;

    public static void main(String[] args) {
        Duke.isRunning = true;
        ToDoList tdl = new ToDoList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine();
            startBot(command, tdl);
        }
    }

    private static void startBot(String command, ToDoList tdl) {
        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            tdl.displayList();
        } else if (command.startsWith("done")) {
            try {
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                tdl.markAsDone(index);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("------------------");
                System.out.println("OOPS!!! The description of a done cannot be empty.");
                System.out.println("------------------\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------");
                System.out.println("OOPS!!! Index is out of bound.");
                System.out.println("------------------\n");
            }
        } else if (command.startsWith("todo")) {
            try {
                String substring = command.substring(5);
                tdl.addToDo(substring);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("------------------");
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println("------------------\n");
            }
        } else if (command.startsWith("event")) {
            try {
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addEvent(item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("------------------");
                System.out.println("OOPS!!! The description of a event cannot be empty.");
                System.out.println("------------------\n");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String substring = command.substring(9);
                String item = substring.substring(0, substring.indexOf("/"));
                String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addDeadline(item, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("------------------");
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("------------------\n");
            }
        } else {
            System.out.println("Damn... I'm confused...");
        }
    }

    private static void greeting() {
        System.out.println("------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------\n");
    }

    private static void exit() {
        Duke.isRunning = false;
        System.out.println("------------------");
        System.out.println("Bye. Hope to see you soon!\n");
        System.out.println("------------------\n");
    }
}