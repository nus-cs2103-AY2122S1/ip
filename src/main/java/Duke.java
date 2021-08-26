import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static TaskList list = new TaskList();

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
                list.printList();
                continue;
            }
            String[] commandSplit = command.split("\\s", 2);

            try {
                switch (commandSplit[0]) {
                    case "done":
                        //Marks tasks as done
                        int index = Integer.valueOf(commandSplit[1]) - 1;
                        list.setDone(index);
                        System.out.println("I've marked this task as done: \n" + list.get(index));
                        break;

                    case "delete":
                        //Deletes tasks
                        int indexD = Integer.valueOf(commandSplit[1]) - 1;
                        Task deleted = list.deleteTask(indexD);
                        System.out.println("Noted. I have removed this task: \n" + deleted);
                        break;

                    case "todo":
                        //Adds a new Todo to the list
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a todo cannot be empty");
                        }
                        Todo newT = new Todo(commandSplit[1]);
                        list.addTask(newT);
                        System.out.println("I have added this task: \n" + newT);
                        break;
                    case "deadline":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of a deadline cannot be empty");
                        }
                        Task newD = Deadline.parseCommand(commandSplit[1]);
                        list.addTask(newD);
                        System.out.println("added: " + newD);
                        break;
                    case "event":
                        if (commandSplit.length == 1) {
                            throw new TaskException("The description of an event cannot be empty");
                        }
                        Task newE = Event.parseCommand(commandSplit[1]);
                        list.addTask(newE);
                        System.out.println("added: " + newE);
                        break;

                    default:
                        throw new DukeException();


                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (TaskException e){
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Your time for the deadline is not in the correct format of dd-MM-yyyy HH:mm");
            }
            System.out.println("Now you have " + list.getNumTask() + " task" + (list.getNumTask() > 1 ? "s " : " ") + "in the list");
        }
    }
}
