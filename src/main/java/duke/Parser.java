package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String str) throws DukeException {
        if (str.equals("bye")) {
            return new ExitCommand();
        } else if (str.equals("list")) {
            return new ListCommand();
        } else if (str.contains("done")) {
            int doneNumber = Integer.parseInt(str.substring(5));
            return new DoneCommand(doneNumber);
        } else if (str.contains("deadline")) {
            if (!str.contains("/")) {
                throw new DukeException("    Sorry please indicate your deadline time with a '/by' after your deadline title!");
            }
            int endDescription = str.indexOf("/");
            if (endDescription <= 9) {
                throw new DukeException("    Oh no! Deadlines cannot be empty! Please try again");
            }
            String description = str.substring(9, endDescription - 1);
            if (description.equals("") || description.equals(" ")) {
                throw new DukeException("    Oh no! Deadlines cannot be empty! Please try again");
            }
            String endTime = str.substring(endDescription + 4);
            return new DeadlineAddCommand(description, endTime);
        } else if (str.contains("event")) {
            if (!str.contains("/")) {
                throw new DukeException("    Sorry please indicate a time your event begins with a '/on' after your event title!");
            }
            int endDescription = str.indexOf("/");
            if (endDescription < 6) {
                throw new DukeException("    Oh no! Events cannot be empty! Please try again");
            }
            String description = str.substring(6, endDescription - 1);
            if (description.equals("") || description.equals(" ")) {
                throw new DukeException("    Oh no! Events cannot be empty! Please try again");
            }
            String startTime = str.substring(endDescription + 4);
            return new EventAddCommand(description, startTime);
        } else if (str.contains("todo")) {
            if (str.length() < 5) {
                throw new DukeException("    Oh no! ToDos cannot be empty! Please try again");
            }
            String description = str.substring(5);
            if (description.equals("") || description.equals(" ")) {
                throw new DukeException("    Oh no! ToDos cannot be empty! Please try again");
            }
            return new TodoAddCommand(description);
        } else if (str.contains("delete")) {
            int deleteNumber = Integer.parseInt(str.substring(7));
            return new DeleteCommand(deleteNumber);
        } else if (str.contains("help")) {
            return new HelpCommand();
        }
        throw new DukeException("    I'm sorry :( I don't quite seem to understand, try again pls!");
    }

}
