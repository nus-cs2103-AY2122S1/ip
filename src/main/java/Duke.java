import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        ToDoList lst = new ToDoList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            try {
                respond(input, lst);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (MissingToDoDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (MissingDeadlineDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (MissingEventDescriptionException e) {
                System.out.println(e.getMessage());
            }

            if (input.equals("bye")) break;
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void respond(String input, ToDoList lst) throws InvalidCommandException,
            MissingToDoDescriptionException, MissingDeadlineDescriptionException,
            MissingEventDescriptionException {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")){
            lst.showList();
        } else if (input.contains("done")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            lst.markAsDone(taskNum - 1);
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")){
            if (input.contains("todo") && input.length() == 4) {
                throw new MissingToDoDescriptionException();
            } else if (input.contains("deadline") && input.length() == 8) {
                throw new MissingDeadlineDescriptionException();
            } else if (input.contains("event") && input.length() == 5) {
                throw new MissingEventDescriptionException();
            } else {
                lst.addItem(input);
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
