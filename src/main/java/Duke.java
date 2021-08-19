import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static void printList() {
        System.out.println("Here are the tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ". " + list.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + logo);
        while(true) {
            System.out.println("____________________________________________________________");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (command.equals("list")) {
                printList();
                continue;
            }
            String[] commandSplit = command.split("\\s", 2);
            try {
                switch (commandSplit[0]) {
                    case "done":
                        //Marks tasks as done
                        int index = Integer.valueOf(commandSplit[1]) - 1;
                        list.get(index).setDone();
                        System.out.println("I've marked this task as done: \n" + list.get(index));
                        break;

                    case "todo":
                        //Adds a new Todo to the list
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a todo cannot be empty");
                        }
                        Todo newT = new Todo(commandSplit[1]);
                        list.add(newT);
                        System.out.println("I have added this task: \n" + newT);
                        break;
                    case "deadline":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a deadline cannot be empty");
                        }
                        Task newD = Deadline.parseCommand(commandSplit[1]);
                        list.add(newD);
                        System.out.println("added: " + newD);
                        break;
                    case "event":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of an event cannot be empty");
                        }
                        Task newE = Event.parseCommand(commandSplit[1]);
                        list.add(newE);
                        System.out.println("added: " + newE);
                        break;

                    default:
                        throw new DukeException();


                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (TaskException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Now you have " + list.size() + " task" + (list.size() > 1 ? "s " : " ") + "in the list");

//            if (commandSplit[0].equals("todo")) {
//                Todo newTask = new Todo(commandSplit[1]);
//                list.add(newTask);
//
//            }
//            Task newTask = new Task(command);

//            list.add(newTask);

        }
    }
}
