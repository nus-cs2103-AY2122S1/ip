import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private static Storage storage;

    private static TaskList tasks;
    
    public static void main(String[] args) {
        
        try {
            String filePath = "data/tasks.txt";
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.toString());
            return;
        }

        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);
        
        String endCmd = "bye";
        String listCmd = "list";
        String doneCmd = "done";
        String todoCmd = "todo";
        String deadlineCmd = "deadline";
        String eventCmd = "event";
        String deleteCmd = "delete";

        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String cmd = inputs[0]; // get first word as command
                String description = inputs.length > 1 ? inputs[1] : "";
                System.out.println(separator);

                if (cmd.equals(endCmd)) {
                    System.out.println("Bye bye! See you again soon!");
                    end = true;
                } else if (cmd.equals(listCmd)) {
                    tasks.displayList();
                } else if (cmd.equals(doneCmd)) {
                    Integer taskNum = validateTaskNumber(description);
                    tasks.markTaskDone(taskNum);
                    storage.save(tasks.getListData());
                } else if (cmd.equals(todoCmd)) {
                    tasks.addTask(TaskList.TaskType.TODO, description);
                    storage.save(tasks.getListData());
                } else if (cmd.equals(deadlineCmd)) {
                    tasks.addTask(TaskList.TaskType.DEADLINE, description);
                    storage.save(tasks.getListData());
                } else if (cmd.equals(eventCmd)) {
                    tasks.addTask(TaskList.TaskType.EVENT, description);
                    storage.save(tasks.getListData());
                } else if (cmd.equals(deleteCmd)) {
                    Integer taskNum = validateTaskNumber(description);
                    tasks.deleteTask(taskNum);
                } else {
                    throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                System.out.println(separator);
            }
        }
    }

    /** checks if input is a valid task number and returns task number if valid */
    private static Integer validateTaskNumber(String input) throws DukeException {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("You did not specify the task number.");
        }
        int listLength = tasks.getListSize();
        int taskIndex = taskNum - 1;
        if (listLength == 0) {
            throw new DukeException("The operation cannot be performed as you have 0 tasks in your list.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getListSize()) {
            throw new DukeException("The task number must be from 1 to " + listLength + ".");
        }
        return taskNum;
    }
}
