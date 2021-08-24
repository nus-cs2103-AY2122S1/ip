package duke;

import duke.command.*;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private InputTypes t;

    public Duke(String filePath) {
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(filePath, taskList, ui);
    }

    public void run() {
        //Read events stored in the input List
        storage.loadSavedTasks();

        ui.showLogo();

        Scanner inputScanner = new Scanner(System.in);
        String task = inputScanner.nextLine();
        while (!Parser.isBye(task)) {
            try {
                switch (Parser.judgeType(task)) {
                case TODO:
                    storage.addNewTodo(task);
                    break;
                case EVENT:
                    storage.addNewEvent(task);
                    break;
                case DEADLINE:
                    storage.addNewDeadline(task);
                    break;
                case DONE:
                    storage.setTaskDone(task);
                    break;
                case LIST:
                    ui.showList();
                    break;
                case DELETE:
                    storage.deleteEvent(task);
                    break;
                case UNKNOWN:
                    throw new Exception("Cannot Understand");
                }
            } catch (Exception e) {
                if (e.getMessage().equals("todoEmpty")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (e.getMessage().equals("Cannot Understand")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                e.printStackTrace();
            } finally {
                task = inputScanner.nextLine();
            }
        }
        ui.showGoodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("duke.txt");
        duke.run();
    }
}
