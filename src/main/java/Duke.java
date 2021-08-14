import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Task[] storedTasks = new Task[100];
        int idx = 0;
        String echoInput = sc.nextLine();
        while (!echoInput.equals("bye")) {
            if (echoInput.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    System.out.println(String.format("%d.%s", i, storedTasks[i].toString()));
                }
            } else if (echoInput.startsWith("done")) {
                try {
                    markTask(storedTasks, echoInput);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else {
                try {
                    idx = addTask(storedTasks, echoInput, idx, '/');
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }
            echoInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //returns -1 if character not found in String
    private static int findIndex(String s, Character c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private static void markTask(Task[] storage, String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException("An index must be provided for done to mark task at the index as done.");
        } else {
            try {
                int num = Integer.parseInt(userInput.substring(5));
                storage[num - 1].markAsDone();
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index for done must be an integer.");
            } catch (ArrayIndexOutOfBoundsException ae) {
                throw new DukeException("Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.");
            }
        }
    }

    private static int addTask(Task[] storage, String userInput, int idx, Character separator) throws DukeException {
        String type;

        if (userInput.startsWith("todo")) {
            type = "todo";
        } else if (userInput.startsWith("deadline")) {
            type = "deadline";
        } else if (userInput.startsWith("event")) {
            type = "event";
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        if (userInput.length() <= type.length() + 1) {
            throw new DukeException("The description of " + type + " cannot be empty.");
        }

        String description = userInput.substring(type.length() + 1);

        if (type.equals("todo")) {
            storage[idx] = new Todo(description);
        } else {
            int separatorIdx = findIndex(description, separator);

            if (type.equals("deadline")) {
                if (separatorIdx == -1 || description.length() <= separatorIdx + 3) throw new DukeException("/by must be provided and not empty for a deadline.");
                String by = description.substring(separatorIdx + 4);
                storage[idx] = new Deadline(description.substring(0, separatorIdx), by);
            } else {
                if (separatorIdx == -1 || description.length() <= separatorIdx + 3) throw new DukeException("/at must be provided and not empty for an event.");
                String at = description.substring(separatorIdx + 4);
                storage[idx] = new Event(description.substring(0, separatorIdx), at);
            }
        }
        System.out.println("Got it. I have added this task:");
        System.out.println(storage[idx].toString());
        idx++;
        System.out.println("Now you have " + idx + " tasks in the list.");
        return idx;
    }
}
