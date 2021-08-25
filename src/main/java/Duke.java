import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showStartUpError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        
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
                
                ui.showLine();

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
                    storage.save(tasks.getListData());
                } else {
                    throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /** checks if input is a valid task number and returns task number if valid */
    private Integer validateTaskNumber(String input) throws DukeException {
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
