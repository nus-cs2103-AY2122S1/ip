package duke;

import duke.command.*;
import duke.exception.DukeParseException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    public static Command parse(String fullCommand) throws DukeParseException {
        Scanner sc = new Scanner(fullCommand);
        String input = sc.next();
        try {
            switch (input) {
                case "list": {
                    return new ListCommand();
                }
                case "done": {
                    return new DoneCommand(sc.nextInt());
                }
                case "remove": {
                    return new DeleteCommand(sc.nextInt());
                }
                case "todo": {
                    return new TodoCommand(sc.nextLine().trim());
                }
                case "deadline": {
                    return new DeadlineCommand(sc.nextLine().trim());
                }
                case "event": {
                    return new EventCommand(sc.nextLine().trim());
                }
                case "bye": {
                    return new ExitCommand();
                }
                default: {
                    throw new DukeParseException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (NoSuchElementException e) {
            throw new DukeParseException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } finally {
            sc.close();
        }
    }
}
