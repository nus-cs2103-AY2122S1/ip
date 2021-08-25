package duke;

import duke.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private enum Commands {
        BYE {
            @Override
            public String asLowerCase() {
                return BYE.toString().toLowerCase();
            }
        },
        LIST {
            @Override
            public String asLowerCase() {
                return LIST.toString().toLowerCase();
            }
        },
        DONE {
            @Override
            public String asLowerCase() {
                return DONE.toString().toLowerCase();
            }
        },
        TODO{
            @Override
            public String asLowerCase() {
                return TODO.toString().toLowerCase();
            }
        },
        EVENT{
            @Override
            public String asLowerCase() {
                return EVENT.toString().toLowerCase();
            }
        },
        DEADLINE{
            @Override
            public String asLowerCase() {
                return DEADLINE.toString().toLowerCase();
            }
        },
        DELETE{
            @Override
            public String asLowerCase() {
                return DELETE.toString().toLowerCase();
            }

        };

        public abstract String asLowerCase();
    }

    public Parser() {
    }

    protected Command parse(String command, ToDoList tdl, Ui ui, Duke chatBot, Storage storage) {
        if (command.equals(Commands.BYE.asLowerCase())) {
            return new ByeCommand(chatBot, ui, tdl, storage);
        } else if (command.equals(Commands.LIST.asLowerCase())) {
            return new ListCommand(tdl);
        } else if (command.startsWith(Commands.DONE.asLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                return new DoneCommand(tdl, ui, index);
            } catch (NumberFormatException e) {
                ui.prettyPrinter("Dude, the format is done <index>");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.TODO.asLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                return new ToDoCommand(tdl, substring);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.EVENT.asLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                return new EventCommand(tdl, item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to "
                        + "be <event> <name> /at <duration>");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DEADLINE.asLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(9);
                String item = substring.substring(0, substring.indexOf("/"));
                String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
                if (deadline.startsWith(" ")) {
                    deadline = deadline.substring(1);
                }
                LocalDateTime dl = LocalDateTime.parse(deadline.replace(' ','T'),
                        DateTimeFormatter.ISO_DATE_TIME);
                return new DeadlineCommand(tdl, item, dl);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to "
                        + "be <deadline> <name> /by <dueDate>");
            } catch (DateTimeParseException e) {
                System.out.println("Please key in the date time as YYYY-MM-dd HH:mm");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DELETE.asLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(7);
                int index = Integer.parseInt(substring);
                return new DeleteCommand(tdl, ui, index);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("And which item do you want to delete...?");
            } catch (NumberFormatException e) {
                ui.prettyPrinter("Dude, the format is delete <index>");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else {
            return new ConfusedCommand(ui);
        }
        return new TryAgainCommand(ui);
    }

    private static void formatChecker(String command) throws DukeException {
        if (command.startsWith("done")) {
            if (!command.substring(4).startsWith(" ")) {
                throw new DukeException("Hey hey hey, the format is done <index>");
            }
        } else if (command.startsWith("delete")) {
            if (!command.substring(6).startsWith(" ")) {
                throw new DukeException("Hey hey hey, the format is delete <index>");
            }
        } else if (command.startsWith("todo")) {
            if (command.substring(4).isBlank()) {
                throw new DukeException("C'mon.. you're gonna do nothing?");
            }
        } else if (command.startsWith("event")) {
            if (!command.substring(command.indexOf("/")).startsWith("/at")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <event> <name> "
                        + "/at <duration>");
            } else if (command.substring(5).isBlank()) {
                throw new DukeException("Really? An event of nothing?");
            }
        } else {
            if (!command.substring(command.indexOf("/")).startsWith("/by")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <deadline> "
                        + "<name> /by <dueDate>");
            } else if (command.substring(8).isBlank()) {
                throw new DukeException("Hold up.. last i checked doing nothing has no deadline");
            }
        }
    }
}
