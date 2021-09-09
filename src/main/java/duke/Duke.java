package duke;

import duke.task.TaskList;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        taskList = new TaskList();
        storage = new Storage("./duke.txt");
        storage.loadTaskListData(taskList);
        ui = new Ui(taskList, storage);
    }
    
    public void runCLI() {
        this.ui.showWelcome();
        Ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.getCommand();
            Parser parser = new Parser(command);
            if (parser.isExit()) {
                break;
            }
            Command c = parser.parse();
            c.execute(taskList, storage);
            isExit = parser.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().runCLI();
    } 
    
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        if (parser.isExit()) {
            String response = Ui.sayBye();
            return response;
        } 
        Command c = parser.parse();
        String response = c.execute(this.taskList, this.storage);
        return response;
    }
}
