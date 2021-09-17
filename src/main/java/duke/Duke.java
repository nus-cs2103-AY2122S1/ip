package duke;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.Ui;
import duke.components.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(this.storage, this.ui, this.parser);
    }

    public void run() {
        storage.loadInto(taskList);
        ui.displayWelcomeMessage();
        Scanner myObj = new Scanner(System.in);
        // String input
        while(true) {
            String input = myObj.nextLine();
            Task newTask = new Task(input);
            if (parser.isMarkDoneCommand(input)) {
                taskList.markDone(input);
                continue;
            }
            if (parser.isDeleteTaskCommand(input)) {
                taskList.deleteTask(input);
                continue;
            }
            if (!parser.isEnd(input) && !parser.isDisplay(input)) {
                taskList.addTaskFromInput(input);
                continue;
            }
            if (parser.isDisplay(input)) {
                taskList.displayAllTasks();
                continue;
            }
            if (parser.isEnd(input)) {
                ui.displayExitMessage();
                break;
            }
            ui.displayOtherInputsMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }
}
