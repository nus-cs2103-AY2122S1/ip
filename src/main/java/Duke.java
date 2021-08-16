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
                    "What can I do for you?"};
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
                if (inputs[0].equals("bye") && inputs.length == 1) {
                    System.out.println(
                        StringFormat.formatString("Bye. Hope to see you again soon!")
                    );
                    break;
                // if it is "list", we list the stored inputs
                } else if (inputs[0].equals("list") && inputs.length == 1) {
                    System.out.println(
                        StringFormat.tabAndFormat(storage.toString())
                    );
                // if we are editing tasks
                } else if (inputs[0].equals("done") || inputs[0].equals("delete")) {
                    taskEditor(storage, inputs);
                                    
                // if we are creating a task
                } else {
                    taskCreator(storage, inputs);
                }
            } catch (DukeException | IllegalArgumentException e){
                System.out.println(StringFormat.tabAndFormat(e.getMessage()));
            }
        }
        sc.close();
    }

    // function to help in editing tasks
    private static void taskEditor(TaskStorage storage, String[] inputs) throws DukeException {
        // if we are marking a task as done
        if (inputs[0].equals("done")) {
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! Enter proper task number to be marked as done.");
            }
            // checking following string is a valid number
            if (checkStringIfInteger(inputs[1])) {
                int ind = Integer.valueOf(inputs[1]) - 1;
                System.out.println(
                    StringFormat.tabAndFormat(storage.markDone(ind))
                );
            } else {
                System.out.println(StringFormat.formatString("☹ OOPS!!! Enter proper task number to be deleted."));
            }

        // if we are deleting a task from storage
        } else if (inputs[0].equals("delete")) {
            if (inputs.length != 2) {
                throw new DukeException("☹ OOPS!!! Enter proper task number to be deleted.");
            }
            // checking following string is a valid number
            if (checkStringIfInteger(inputs[1])) {
                // if it is, we can go ahead with the deletion
                int ind = Integer.valueOf(inputs[1]) - 1;
                System.out.println(
                    StringFormat.tabAndFormat(storage.delete(ind))
                );
                // 
            } else {
                System.out.println(StringFormat.formatString("☹ OOPS!!! Enter proper task number to be deleted."));
            }
        }
    }

    // function to check if a String is an integer
    private static boolean checkStringIfInteger(String test) {
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // function to help in creating tasks
    private static void taskCreator(TaskStorage storage, String[] inputs) throws DukeException {
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
                throw new DukeException("☹ OOPS!!! The information for a deadline must have:\n1. Brief description\n2. Deadline\nWhere the two fields are separated by \"/by\"");
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
                throw new DukeException("☹ OOPS!!! The information for an event must have:\n1. Brief description\n2. Date/Interval\nWhere the two fields are separated by \"/at\"");
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
