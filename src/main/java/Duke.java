import java.util.Scanner;
import java.util.ArrayList;

/**
 * Chatbot that helps you form a task list
 */

public class Duke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void addTask(TaskType t, String input, ArrayList<Task> list) {
        try {
            switch (t) {
                case TODO:
                    Task todo = new ToDo(input);
                    list.add(todo);
                    todo.addResponse(list.size());
                    break;
                case DEADLINE:
                    if (input.split("/by ", 2).length < 2) {
                        throw new DukeException("Deadline not specified!");
                    }
                    String desc = input.split("/by ", 2)[0];
                    String dead = input.split("/by ", 2)[1];
                    Task deadline = new Deadline(desc, dead);
                    list.add(deadline);
                    deadline.addResponse(list.size());
                    break;
                case EVENT:
                    if (input.split("/at ", 2).length < 2) {
                        throw new DukeException("Date of event not specified!");
                    }
                    String name = input.split("/at ", 2)[0];
                    String at = input.split("/at ", 2)[1];
                    Task event = new Event(name, at);
                    list.add(event);
                    event.addResponse(list.size());
                    break;
            }
        } catch (DukeException e) {
            System.out.println(e.toString().split(" ", 2)[1]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        boolean loop = true;
        Scanner textInput = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (loop) {
            String input = textInput.nextLine();
            String[] inputSplit = input.split(" ", 2);

            try {
                switch(inputSplit[0]) {
                    case "todo":
                        if (inputSplit.length < 2) {
                            throw new DukeException("Description of task cannot be empty!");
                        }
                        addTask(TaskType.TODO, inputSplit[1], list);
                        break;
                    case "deadline":
                        if (inputSplit.length < 2) {
                            throw new DukeException("Description of task cannot be empty!");
                        }
                        addTask(TaskType.DEADLINE, inputSplit[1], list);
                        break;
                    case "event":
                        if (inputSplit.length < 2) {
                            throw new DukeException("Description of task cannot be empty!");
                        }
                        addTask(TaskType.EVENT, inputSplit[1], list);
                        break;
                    case "list":
                        if (list.size() == 0) {
                            throw new DukeException("The list is empty!!");
                        }
                        System.out.println("Here are your tasks:");
                        for (int i = 1; i <= list.size(); i++) {
                            Task task = list.get(i - 1);
                            System.out.println(i + "."
                                    + task.toString());
                        }
                        break;
                    case "done":
                        if (inputSplit.length < 2) {
                            throw new DukeException("Please enter the number of task to mark as completed!");
                        }
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            if (index > list.size() || index <= 0) {
                                throw new DukeException("That number is not in the list!");
                            }
                            Task task = list.get(index - 1);
                            task.toggleCompleted();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a proper number pls");
                        }
                        break;
                    case "delete":
                        int i = Integer.parseInt(inputSplit[1]);
                        try {
                            Task t = list.get(i - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(t);
                            list.remove(i - 1);
                            System.out.println("Now you have " + list.size() + " tasks in your list.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("That is an invalid index!!");
                        }
                        break;
                    case "bye":
                        loop = false;
                        break;
                    default:
                        throw new DukeException("That is not within my scope of action!");
                }
            } catch (DukeException e) {
                System.out.println(e.toString().split(" ", 2)[1]);
            }
        }

        System.out.println("Bye bye. Duke going to sleep now.");
    }
}
