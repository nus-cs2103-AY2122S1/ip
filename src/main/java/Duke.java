import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> storedTasks = new ArrayList<>();
        String echoInput = sc.nextLine();
        while (!echoInput.equals("bye")) {
            if (echoInput.equals("list")) {
                enumerateList(storedTasks);
            } else if (echoInput.startsWith("done")) {
                try {
                    markTask(storedTasks, echoInput);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (echoInput.startsWith("delete")) {
                try {
                    deleteTask(storedTasks, echoInput);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else {
                try {
                    addTask(storedTasks, echoInput, '/');
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
    
    private static void deleteTask(ArrayList<Task> storage, String userInput) throws DukeException {
        if (userInput.length() <= 7) {
            throw new DukeException("An index must be provided to delete task at index.");
        } else {
            try {
                int num = Integer.parseInt(userInput.substring(7));
                num--;
                if (num >= storage.size() || num < 0) {
                    throw new DukeException("Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.");
                }
                Task task = storage.get(num);
                storage.remove(num);
                System.out.println("Noted. I've removed this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + storage.size() + " tasks in the list.");
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index for delete must be an integer.");
            }
        }

    }

    private static void enumerateList(ArrayList<Task> storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, storage.get(i).toString()));
        }
    }

    private static void markTask(ArrayList<Task> storage, String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException("An index must be provided to mark task at the index as done.");
        } else {
            try {
                int num = Integer.parseInt(userInput.substring(5));
                num--;
                if (num >= storage.size() || num < 0) {
                    throw new DukeException("Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.");
                }
                storage.get(num).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storage.get(num).toString());
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index for done must be an integer.");
            }
        }
    }

    private static void addTask(ArrayList<Task> storage, String userInput, Character separator) throws DukeException {
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
            storage.add(new Todo(description));
        } else {
            int separatorIdx = findIndex(description, separator);

            if (type.equals("deadline")) {
                if (separatorIdx == -1 || description.length() <= separatorIdx + 3) throw new DukeException("/by must be provided and not empty for a deadline.");
                String by = description.substring(separatorIdx + 4);
                storage.add(new Deadline(description.substring(0, separatorIdx), by));
            } else {
                if (separatorIdx == -1 || description.length() <= separatorIdx + 3) throw new DukeException("/at must be provided and not empty for an event.");
                String at = description.substring(separatorIdx + 4);
                storage.add(new Event(description.substring(0, separatorIdx), at));
            }
        }
        System.out.println("Got it. I have added this task:");
        System.out.println(storage.get(storage.size() - 1).toString());
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
    }
}
