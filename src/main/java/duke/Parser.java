package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String str) throws DukeException {
        if (str.equals("bye")) {
            return new ExitCommand();
        } else if (str.equals("list")) {
            return new ListCommand();
        } else if (str.split("\\s+")[0].equals("done")) {
            int doneNumber = Integer.parseInt(str.substring(5));
            return new DoneCommand(doneNumber);
        } else if (str.split("\\s+")[0].equals("deadline")) {
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
        } else if (str.split("\\s+")[0].equals("event")) {
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
        } else if (str.split("\\s+")[0].equals("todo")) {
            if (str.length() < 5) {
                throw new DukeException("    Oh no! ToDos cannot be empty! Please try again");
            }
            String description = str.substring(5);
            if (description.equals("") || description.equals(" ")) {
                throw new DukeException("    Oh no! ToDos cannot be empty! Please try again");
            }
            return new TodoAddCommand(description);
        } else if (str.split("\\s+")[0].equals("delete")) {
            int deleteNumber = Integer.parseInt(str.substring(7));
            return new DeleteCommand(deleteNumber);
        } else if (str.split("\\s+")[0].equals("help")) {
            return new HelpCommand();
        } else if (str.split("\\s+")[0].equals("find")) {
            return new FindCommand(str.split("\\s+", 2)[1]);
        }
        throw new DukeException("    I'm sorry :( I don't quite seem to understand, try again pls!");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Parser)) {
            return false;
        }
        return true;
    }
}
