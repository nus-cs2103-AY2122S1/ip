import commands.Command;
import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import utils.Util;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                // Command c = Parser.parse(fullCommand);
                // c.execute(tasks, ui, storage);
                // isExit = c.isExit();
                System.out.println(fullCommand);
            } catch (Exception e) {
//            } catch (DukeException e) {
                // ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

//    public static void main(String[] args) throws IOException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("What can I do for you?");
//
//        Scanner sc = new Scanner(System.in);
//        TaskList taskList = Util.loadDataBase("./data/duke.txt");
//
//        while (true) {
//            String userInput = sc.nextLine();
//            Command command = Command.of(userInput);
//            command.updateLogAndTaskList(taskList);
//            if (command.isExit()) {
//                break;
//            }
//            taskList = command.getTaskList();
//            System.out.println(command.getLog());
//        }
//        System.out.println("Bye. Hope to see you again soon!");
//        sc.close();
//    }
}