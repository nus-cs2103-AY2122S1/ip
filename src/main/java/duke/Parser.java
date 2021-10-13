package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.PriorityCommand;

/**
 * This class encapsulates the mechanism to parse user commands.
 *
 * @author Kleon Ang
 */
public class Parser {
    private final TaskList tasks;
    private boolean toRewriteData;

    /**
     * Constructor for a Parser class.
     *
     * @param tasks A TaskList for the Parser to refer to.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.toRewriteData = false;
    }

    /**
     * Getter for the rewrite status of the last parsed command.
     *
     * @return True if data in Storage needs to be rewritten.
     */
    public boolean needsToRewrite() {
        return this.toRewriteData;
    }

    /**
     * Parses the user input and runs the corresponding command.
     *
     * @param userInput A string containing the user input.
     * @return A string containing the output to the user.
     * @throws DukeException A Duke-specific exception that occurs when user input is parsed.
     */
    public String parse(String userInput) throws DukeException {
        Command command;
        if (userInput.equals("bye")) {
            this.toRewriteData = false;
            return "Bye. Hope to see you again soon!";
        } else if (userInput.equals("list")) {
            command = new ListCommand(this.tasks);
            this.toRewriteData = false;
            return command.getReply(userInput);
        }
        String[] commandArguments = userInput.split(" ", 2);
        String commandWord = commandArguments[0];
        String arguments = "";
        if (commandArguments.length == 2) {
            arguments = commandArguments[1];
        }
        switch (commandWord) {
        case "done":
            command = new DoneCommand(this.tasks);
            this.toRewriteData = true;
            break;
        case "deadline":
            // Fallthrough
        case "event":
            // Fallthrough
        case "todo":
            command = new AddCommand(this.tasks, commandWord);
            this.toRewriteData = true;
            break;
        case "delete":
            command = new DeleteCommand(this.tasks);
            this.toRewriteData = true;
            break;
        case "find":
            command = new FindCommand(this.tasks);
            this.toRewriteData = false;
            break;
        case "priority":
            command = new PriorityCommand(this.tasks);
            this.toRewriteData = true;
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command.getReply(arguments);
    }
}
