package pika.ui;

import pika.command.AddCommand;
import pika.command.Command;
import pika.command.DeleteCommand;
import pika.command.DoneCommand;
import pika.command.ExitCommand;
import pika.command.FindCommand;
import pika.command.ListCommand;
import pika.command.TagCommand;
import pika.exception.PikaException;

public class Parser { //The Parser to handle the inputs from the terminal

    /**
     * Converts the input to the proper command and returns an error if the input is not valid.
     *
     * @param input User input
     * @return the new Command
     * @throws PikaException If the input is not of the right format
     */
    public static Command parse(String input) throws PikaException {
        String command;
        String details;
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            command = input;
            details = null;
        } else {
            command = input.substring(0, spaceIndex).trim();
            details = input.substring(spaceIndex).trim();
        }
        switch (command) {
        case "bye":
            return new ExitCommand(details);

        case "todo":
            return new AddCommand(command, details);

        case "deadline":
            return new AddCommand(command, details);

        case "event":
            return new AddCommand(command, details);

        case "done":
            return new DoneCommand(details);

        case "list":
            return new ListCommand(details);

        case "delete":
            return new DeleteCommand(details);

        case "find":
            return new FindCommand(details);

        case "tag":
            return new TagCommand(details);

        default:
            throw new PikaException("Pika pi!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
