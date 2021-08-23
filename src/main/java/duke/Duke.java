package duke;

import duke.command.DukeCommand;
import duke.exception.DukeFileException;
import duke.task.TaskList;

public class Duke {
    private static TaskList tList = new TaskList();

    private static void exit() {
        try {
            DukeStorage.saveTaskList(tList);
        } catch (DukeFileException e) {
            DukeUi.printError(e.getMessage());
        }
        DukeUi.sayBye();
    }

    public static void runDuck() {
        DukeStorage.loadTasks(tList);
        DukeUi.greet();

        String userInput;
        boolean isExit = false;
        DukeCommand command;

        while (!isExit) {
            userInput = DukeUi.readCommand();
            command = DukeCommandParser.parseCommand(userInput);
            command.execute(tList);
            isExit = command.isExit();
        }

    }

    public static void main(String[] args) {
        runDuck();
    }
}
