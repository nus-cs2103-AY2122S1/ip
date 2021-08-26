import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum TaskType {
        ToDo,
        Event,
        Deadline,
        Invalid
    }
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/duke.txt");
        TaskList taskList;
        taskList = storage.loadTasksFromFile();
        //Parser parser = new Parser();
        ui.displayWelcomeMsg();
        String[] params = sc.nextLine().split(" ", 2);
        String firstParam = params[0];

        while (!firstParam.equals("bye")) {
            try {
                if (firstParam.equals("list")) {
                    taskList.handleDisplayTasks();
                } else if (firstParam.equals("done")) {
                    taskList.handleDoneTask(Integer.parseInt(params[1]) - 1);
                } else if (firstParam.equals("delete")) {
                    taskList.handleDeleteTask(Integer.parseInt(params[1]) - 1);
                } else {
                    taskList.handleAddTask(params);
                }
            } catch (DukeException e) {
                ui.displayErrorMsg(e);
            }
            params = sc.nextLine().split(" ", 2);
            firstParam = params[0];
        }
        storage.writeTasksToFile(taskList.tasks);
        ui.displayGoodbyeMsg();
    }
}