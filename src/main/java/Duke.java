import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String welcomeLine = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbyeLine = "Bye. Hope to see you again soon!";
        System.out.println(welcomeLine);
        boolean isRunning = true;
        String input;
        ArrayList<Task> listArray = new ArrayList<Task>(100);
        int count = 0;
        int index;
        String[] split;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                input = sc.next(); //Reads the next input
                switch (input) {
                    case "bye":  //Shuts down the bot when it gets a "bye" prompt
                        isRunning = false;
                        System.out.println(goodbyeLine);
                        break;
                    case "list":  //Returns the list when it gets a "list" prompt
                        if (count == 0) {
                            System.out.println("The list is empty!"); //When the list is empty
                        } else {
                            for (int i = 0; i < count; i++) {
                                System.out.println(i + 1 + ". " + listArray.get(i));
                            }
                        }
                        break;
                    case "done":  //Marks the task as done when it gets the "done x" prompt and updates the task as well.
                        input = sc.nextLine();
                        if (input.isEmpty()) { //Catch if there is no number after the command
                            throw new DukeException("☹ OOPS!!! done will require a task number to update.");
                        }
                        index = Integer.parseInt(input.trim());
                        if (index > count || index <= 0) { //Catches if the number is > than the number of task or if its negative
                            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + listArray.get(index - 1).done());
                        break;
                    case "todo":  //Inputs a todo task when given the "todo" prompt
                        input = sc.nextLine();
                        if (input.isEmpty()) { //Catch if todo description is empty
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        listArray.add(count++, new Todo(input.trim()));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + listArray.get(count - 1));
                        System.out.println("Now you have " + count + " in the list.");
                        break;
                    case "deadline": //Inputs a Deadline task when given the "deadline" prompt
                        input = sc.nextLine();
                        if (input.isEmpty()) { //Catch if deadline description is empty
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        split = input.split(" /by ");
                        if (split.length <= 1) {
                            throw new DukeException("☹ OOPS!!! Your deadline input format is not valid!");
                        }
                        listArray.add(count++, new Deadline(split[0].trim(), split[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + listArray.get(count - 1));
                        System.out.println("Now you have " + count + " in the list.");
                        break;
                    case "event": //Inputs an Event task when given the "event" prompt
                        input = sc.nextLine();
                        if (input.isEmpty()) { //Catch if event description is empty
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        split = input.split(" /at ");
                        if (split.length <= 1) {
                            throw new DukeException("☹ OOPS!!! Your event input format is not valid!");
                        }
                        listArray.add(count++, new Event(split[0].trim() , split[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + listArray.get(count - 1));
                        System.out.println("Now you have " + count + " in the list.");
                        break;
                        case "delete":  //deletes the task when it gets the "delete x" prompt and updates the task as well.
                        input = sc.nextLine();
                        if (input.isEmpty()) { //Catch if there is no number after the command
                            throw new DukeException("☹ OOPS!!! delete will require a task number to update.");
                        }
                        index = Integer.parseInt(input.trim());
                        if (index > count || index <= 0) { //Catches if the number is > than the number of task or if its negative
                            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + listArray.remove(index - 1));
                        System.out.println("Now you have " + --count + " in the list.");
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e2) { //For catching any invalid number after the done command
                System.out.println("☹ OOPS!!! done will require a valid integer value input to update.");
            }
        } while (isRunning);
    }
}
