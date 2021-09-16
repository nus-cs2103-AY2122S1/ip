package duke;

import duke.commands.CommandBye;
import duke.commands.CommandDeadline;
import duke.commands.CommandDelete;
import duke.commands.CommandDone;
import duke.commands.CommandEvent;
import duke.commands.CommandFind;
import duke.commands.CommandHelp;
import duke.commands.CommandList;
import duke.commands.CommandTodo;
import duke.exceptions.DukeException;

/**
 * Parser to parse user commands.
 */
public class Parser {
    private TaskArrayList taskList;

    /**
     * Constructor for Parser.
     *
     * @param taskList TaskArrayList object to work with
     */
    Parser(TaskArrayList taskList) {
        this.taskList = taskList;
    }

    /**
     * Runs a user input string.
     *
     * @param userInput String keyed in by user.
     * @return isExit, true if duke should exit after this command.
     * @throws DukeException if unable to run argument.
     */
    public String run(String userInput) throws DukeException {
        String[] cmdArgsArr = userInput.split(" ", 2);

        // switch case to split by command
        switch (cmdArgsArr[0]) {

        case ("bye"):
            return new CommandBye(cmdArgsArr, taskList).run();
        case ("deadline"):
            return new CommandDeadline(cmdArgsArr, taskList).run();
        case ("delete"):
            return new CommandDelete(cmdArgsArr, taskList).run();
        case ("done"):
            return new CommandDone(cmdArgsArr, taskList).run();
        case ("event"):
            return new CommandEvent(cmdArgsArr, taskList).run();
        case ("find"):
            return new CommandFind(cmdArgsArr, taskList).run();
        case ("help"):
            return new CommandHelp(cmdArgsArr, taskList).run();
        case ("list"):
            return new CommandList(cmdArgsArr, taskList).run();
        case ("todo"):
            return new CommandTodo(cmdArgsArr, taskList).run();
        default:
            throw new DukeException("Unrecognised Command\n"
                    + new CommandHelp(cmdArgsArr, taskList).run());
        }
    }

}
