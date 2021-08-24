package duke;

import duke.command.*;
import duke.dukeexception.EmptyDescriptionException;
import duke.dukeexception.IllegalCommandException;

public class Parser {

    public static Command parse(String input) throws IllegalCommandException, EmptyDescriptionException {
        if (input.equals("bye")) {
            return new Exit();
        } else if (input.equals("list")) {
            return new List();
        } else if (input.startsWith("done")) {
            int value = Character.getNumericValue(input.charAt(5));
            return new Done(value);
        }  else if (input.startsWith("delete")) {
            int value = Character.getNumericValue(input.charAt(7));
            return new Delete(value);
        } else if (input.startsWith("find")) {
            String searchTerm = input.substring(4);
            return new Find(searchTerm);
        } else {
            try {
                return new Add(input);
            } catch (IllegalCommandException | EmptyDescriptionException e) {
                throw e;
            }
        }
    }
}