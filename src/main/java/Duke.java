import java.util.Scanner;

public class Duke {
    private static String name = "Duke";
    private static boolean isRunning = false;

    public static void main(String[] args) {
        Duke.isRunning = true;
        ToDoList tdl = new ToDoList(Duke.name);
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
                dukePrinter("And I'm supposed to guess which item you're done with?");
            } catch (IndexOutOfBoundsException e) {
                dukePrinter("Where's this item? It's not even on the list!");
            }
        } else if (command.startsWith("todo")) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                tdl.addToDo(substring);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith("event")) {
            try {
                formatChecker(command);
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addEvent(item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <event> <name> /at <duration>");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith("deadline")) {
            try {
                formatChecker(command);
                String substring = command.substring(9);
                String item = substring.substring(0, substring.indexOf("/"));
                String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addDeadline(item, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <deadline> <name> /by <dueDate>");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith("delete")) {
            try {
                String substring = command.substring(7);
                int index = Integer.parseInt(substring);
                tdl.delete(index);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("And which item do you want to delete...? Try again :/");
            } catch (IndexOutOfBoundsException e) {
                dukePrinter("You're trying to delete something non-existent? Damn who is this guy?");
            }
        } else {
           dukePrinter("I'm confused... I need a raise...");
        }
    }

    private static void greeting() {
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Hello... I'm " + Duke.name + ":/");
        System.out.println("And how can I help you? Make it snappy!");
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    private static void exit() {
        Duke.isRunning = false;
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Wow! I can get off work now :D");
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    private static void formatChecker(String command) throws DukeException {
        if (command.startsWith("todo")) {
            if (command.substring(4).isBlank()) {
                throw new DukeException("C'mon.. you're gonna do nothing?");
            }
        } else if (command.startsWith("event")) {
            if (!command.substring(command.indexOf("/")).startsWith("/at")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <event> <name> /at <duration>");
            } else if (command.substring(5).isBlank()) {
                throw new DukeException("Really? An event of nothing?");
            }
        } else {
            if (!command.substring(command.indexOf("/")).startsWith("/by")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <deadline> <name> /by <dueDate>");
            } else if (command.substring(8).isBlank()) {
                throw new DukeException("Hold up.. last i checked doing nothing has no deadline");
            }
        }
    }

    private static void dukePrinter(String message) {
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println(message);
        System.out.println("========== " + Duke.name + " ===========\n");
    }
}