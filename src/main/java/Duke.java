import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Starting Message
        String[] startMessage = {" ____        _        ", 
                    "|  _ \\ _   _| | _____ ",
                    "| | | | | | | |/ / _ \\",
                    "| |_| | |_| |   <  __/",
                    "|____/ \\__,_|_|\\_\\___|",
                    "Hello! I'm Duke",
                    "What can I do for you?\n"};
        System.out.println(StringFormat.formatString(startMessage));

        // Storage for tasks
        TaskStorage storage = new TaskStorage();

        // Taking in user input
        Scanner sc = new Scanner(System.in);
        
        // While there's user input
        while(sc.hasNext()) {
            String input = sc.nextLine();

            String[] inputs = input.split(" ", 2);
            
            try {
                // if it is "bye", we exit the loop
                if (inputs[0].equals("bye")) {
                    System.out.println(
                        StringFormat.formatString("Bye. Hope to see you again soon!")
                    );
                    break;
                // if it is "list", we list the stored inputs
                } else if (inputs[0].equals("list")) {
                    System.out.println(
                        StringFormat.tabAndFormat(storage.toString())
                    );
                // if we are marking a task as done
                } else if (inputs[0].equals("done")) {
                    String[] result = input.split(" ");
                    int ind = Integer.valueOf(result[1]) - 1;

                    System.out.println(
                        StringFormat.tabAndFormat(storage.markDone(ind))
                    );
                // if we are making a task
                } else {
                    taskHandler(storage, inputs, input);
                }
            } catch (DukeException | IllegalArgumentException | NoSuchElementException e){
                System.out.println(StringFormat.tabAndFormat(e.getMessage()));
            }
        }
        sc.close();
    }

    public static void taskHandler(TaskStorage storage, String[] inputs, String input) throws DukeException {
        // if we are creating a todo task
        if (inputs[0].equals("todo")) {
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(new Todo(inputs[1]))
                )
            );   
        // if we are creating a deadline task 
        } else if (inputs[0].equals("deadline")) {
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! The information for a deadline cannot be empty.");
            }
            String[] result = inputs[1].split(" /by ");
            if (result.length < 2) {
                throw new DukeException("☹ OOPS!!! The information for a deadline must have:\n\t1. Brief description\n\t2. Deadline");
            }
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Deadline(result[0], result[1])
                    )
                )
            );
        // if we are creating an event task
        } else if (inputs[0].equals("event")) {
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! The information for an event cannot be empty.");
            }
            String[] result = inputs[1].split(" /at ");
            if (result.length < 2) {
                throw new DukeException("☹ OOPS!!! The information for an event must have:\n\t1. Brief description\n\t2. Date/Interval");
            }
            System.out.println(
                StringFormat.tabAndFormat(
                    storage.add(
                        new Event(result[0], result[1])
                    )
                )
            );
        // any other unrecognised command
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
