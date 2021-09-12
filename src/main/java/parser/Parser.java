package parser;

import java.util.ArrayList;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.DueCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.RescheduleCommand;
import commands.ToDoCommand;
import duke.DukeException;
import ui.Ui;

/**
 * The Parser class attempts to make sense of
 * user's input and generates commands accordingly.
 */
public final class Parser {

    /**
     * Generates the command corresponding to user's input.
     *
     * @param userInput input line of words, possibly containing any character
     * @return the command corresponding to keywords or null if none detected
     */
    public Command parse(String userInput) {
        Command command = null;
        try {
            ArrayList<String> words = new ArrayList<>();
            String[] arr = userInput.split("\\s+");
            for (String ss : arr) {
                if (!ss.equals("")) {
                    words.add(ss);
                }
            }
            command = generateCommand(words);
        } catch (DukeException e) {
            new Ui().showHelperMessage();
        }
        return command;
    }

    /**
     * Creates commands specific to user's input.
     *
     * @param s the words detected by parse method
     * @return created executable command
     * @throws DukeException if user's input does not contain correct keywords
     */
    public Command generateCommand(ArrayList<String> s) throws DukeException {
        if (s == null || s.isEmpty()) {
            throw new DukeException("Please chek your input!");
        } else if (s.get(0).equalsIgnoreCase("bye")) {
            return new ByeCommand(s);
        } else if (s.get(0).equalsIgnoreCase("list")) {
            return new ListCommand(s);
        } else if (s.get(0).equalsIgnoreCase("done")) {
            return new DoneCommand(s);
        } else if (s.get(0).equalsIgnoreCase("delete")) {
            return new DeleteCommand(s);
        } else if (s.get(0).equalsIgnoreCase("todo")) {
            return new ToDoCommand(s);
        } else if (s.get(0).equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(s);
        } else if (s.get(0).equalsIgnoreCase("event")) {
            return new EventCommand(s);
        } else if (s.get(0).equalsIgnoreCase("due")) {
            return new DueCommand(s);
        } else if (s.get(0).equalsIgnoreCase("find")) {
            return new FindCommand(s);
        } else if (s.get(0).equalsIgnoreCase("reschedule")) {
            return new RescheduleCommand(s);
        } else {
            throw new DukeException("Not a valid command!");
        }
    }
}
