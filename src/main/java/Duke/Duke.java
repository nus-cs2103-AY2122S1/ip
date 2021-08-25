package Duke;

import Duke.Commands.AddCommand;
import Duke.Commands.Command;
import Duke.Commands.EditCommand;
import Duke.Commands.ExitCommand;
import Duke.Errors.DukeError;
import Duke.Parser.CustomDateFormatter;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Task.ToDo;
import Duke.Ui.Ui;



public class Duke {

    public static boolean byeCheck(String response) {
        return response.equals("bye") || response.startsWith("bye ");
    }

    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }



    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                break;
            }

        }

    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

//    public static void main(String[] args) {
//        Scanner myScanner = new Scanner(System.in);
//        ArrayList<Task> userTaskList = initialiseArrayList();
//        int currentCapacity = userTaskList.size();
//        System.out.println("Henlo, Duke here! How can I be of assistance?");
//        while (true) {
//            String response = myScanner.nextLine();
//            if (byeCheck(response)) {
//                break;
//            } else {
//                String cleanInput = inputCleaner(response, currentCapacity);
//                if (cleanInput.startsWith("error ")) {
//                    errorHandler(cleanInput);
//                    continue;
//                } else if (cleanInput.startsWith("list")) {
//                    listPrinter(userTaskList);
//                } else {
//                    userTaskList = inputParser(cleanInput, userTaskList);
//                    currentCapacity = userTaskList.size();
//                    continue;
//                }
//            }
//        }
//        System.out.println("Goodbye!");
//
//    }
}
