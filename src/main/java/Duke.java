import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static TaskList taskList;
    static Storage storage;
    static Ui ui;

    static String byeMsg = "Bye. Hope to see you again soon!";
    static String listMsg = "Here are the tasks in your list:";
    static String doneMsg = "Nice! I've marked this task as done:";
    static String todoMsg = "Got it. I've added this task:";
    static String deleteMsg = "Noted. I've removed this task:";

    static void init() {
        storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
        ui = new Ui();
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
                    System.out.println(taskList.get(taskList.getSize() - 1));
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                    storage.write(taskList);
                    break;
                case "list":
                    System.out.println(listMsg);
                    List<String> enumerate = taskList.enumerate();
                    enumerate.forEach(System.out::println);
                    System.out.println("There are currently " + taskList.getSize() + " tasks in your list.");
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
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
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
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
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
                    intParam = Integer.parseInt(param) - 1;
                    System.out.println(taskList.get(intParam));
                    taskList.remove(intParam);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
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
            } finally {
                storage.write(taskList);
            }
        }
    }

    public static void main(String[] args) {
        init();
        ui.greet();
        reply();
    }
}
