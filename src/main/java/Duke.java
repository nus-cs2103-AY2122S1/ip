import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList;
    static int numItems = 0;
    static Storage storage;

    static String introMsg = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";
    static String byeMsg = "Bye. Hope to see you again soon!";
    static String listMsg = "Here are the tasks in your list:\n";
    static String doneMsg = "Nice! I've marked this task as done: \n";
    static String todoMsg = "Got it. I've added this task: \n";
    static String deleteMsg = "Noted. I've removed this task:\n";

    static void introduce() {
        System.out.println(introMsg);
    }

    static void read() {
        storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new ArrayList<>();
        }
    }

    static void reply() {
        Scanner sc = new Scanner(System.in);
        outerLoop:
        while (true) {
            String userInput = sc.nextLine();
            String[] tokens = userInput.split("\\s+", 2);
            String command = tokens[0];
            String param = tokens.length == 1 ? null : tokens[1].strip();

            try {
                switch (command) {
                case "todo":
                    if (param == null) {
                        throw DukeException.emptyDescription();
                    }

                    System.out.println(todoMsg);
                    taskList.add(new Todo(param));
                    System.out.println(taskList.get(numItems));
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    storage.write(taskList);
                    break;
                case "list":
                    System.out.println(listMsg);
                    taskList.forEach(task -> System.out.println(taskList.indexOf(task) + 1 + "." + task));
                    break;
                case "deadline":
                    System.out.println(todoMsg);

                    String[] taskItems = param.split(" /by ", 2);
                    String desc = taskItems[0].strip();
                    if (taskItems.length == 1) {
                        taskList.add(new Deadline(desc));
                    } else {
                        taskList.add(new Deadline(desc, taskItems[1].strip()));
                    }
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    storage.write(taskList);
                    break;
                case "event":
                    System.out.println(todoMsg);
                    taskItems = param.split(" /at ", 2);
                    desc = taskItems[0].strip();
                    if (taskItems.length == 1) {
                        taskList.add(new Event(desc));
                    } else {
                        taskList.add(new Event(desc, taskItems[1].strip()));
                    }
                    numItems++;
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    storage.write(taskList);
                    break;
                case "done":
                    System.out.println(doneMsg);
                    int intParam = Integer.parseInt(param) - 1;
                    taskList.get(intParam).markAsDone();
                    System.out.println(taskList.get(intParam));
                    storage.write(taskList);
                    break;
                case "delete":
                    System.out.println(deleteMsg);
                    --numItems;
                    intParam = Integer.parseInt(param) - 1;
                    taskList.remove(intParam);
                    System.out.printf("Now you have %d tasks in the list.\n", numItems);
                    storage.write(taskList);
                    break;
                case "bye":
                    System.out.println(byeMsg);
                    break outerLoop;
                default: // Adds task
                    throw DukeException.invalidInput();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        read();
        introduce();
        reply();
    }
}
