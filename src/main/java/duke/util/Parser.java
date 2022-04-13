package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ConfusedCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TipCommand;
import duke.command.ToDoCommand;

/**
 * Parser is a class that encapsulates the behaviour of an interpreter for commands.
 *
 * @author leezhixuan
 */
public class Parser {

    private enum Commands {
        BYE {
            @Override
            public String asLowerCase() {
                return BYE.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 0;
            }
        },
        LIST {
            @Override
            public String asLowerCase() {
                return LIST.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 0;
            }
        },
        DONE {
            @Override
            public String asLowerCase() {
                return DONE.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 5;
            }
        },
        TODO {
            @Override
            public String asLowerCase() {
                return TODO.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 5;
            }
        },
        EVENT {
            @Override
            public String asLowerCase() {
                return EVENT.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 6;
            }
        },
        DEADLINE {
            @Override
            public String asLowerCase() {
                return DEADLINE.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 9;
            }
        },
        DELETE {
            @Override
            public String asLowerCase() {
                return DELETE.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 7;
            }
        },
        FIND {
            @Override
            public String asLowerCase() {
                return FIND.toString().toLowerCase();
            }

            @Override
            public int offsetValue() {
                return 5;
            }
        };

        public abstract String asLowerCase();
        public abstract int offsetValue();
    }

    /**
     * Creates an instance of Parser.
     */
    public Parser() {
    }

    /**
     * Returns the appropriate command to be executed after interpreting the user's inputted command.
     *
     * @param command Input commands from the user.
     * @param tdl The ToDoList used by the chat bot to track tasks.
     * @param chatBot The instance of the chat bot itself.
     * @param storage The Storage that the chat bot uses.
     * @return The type of Command to be executed.
     */
    public Command parse(String command, ToDoList tdl, Duke chatBot, Storage storage) {
        if (command.equals(Commands.BYE.asLowerCase())) {
            return new ByeCommand(chatBot, tdl, storage);

        } else if (command.equals(Commands.LIST.asLowerCase())) {
            return new ListCommand(tdl);

        } else if (command.startsWith(Commands.DONE.asLowerCase())) {
            return generateDoneCommand(command, tdl);

        } else if (command.startsWith(Commands.TODO.asLowerCase())) {
            return generateToDoCommand(command, tdl);

        } else if (command.startsWith(Commands.EVENT.asLowerCase())) {
            return generateEventCommand(command, tdl);

        } else if (command.startsWith(Commands.DEADLINE.asLowerCase())) {
            return generateDeadlineCommand(command, tdl);

        } else if (command.startsWith(Commands.DELETE.asLowerCase())) {
            return generateDeleteCommand(command, tdl);

        } else if (command.startsWith(Commands.FIND.asLowerCase())) {
            return generateFindCommand(command, tdl);

        } else {
            return new ConfusedCommand();
        }
    }

    private static void formatChecker(String command) throws DukeException {
        if (command.startsWith("done")) {
            if (!command.substring(Commands.DONE.offsetValue() - 1).startsWith(" ")) {
                throw new DukeException("Hey hey hey, the format is done <index>");
            }

        } else if (command.startsWith("delete")) {
            if (!command.substring(Commands.DELETE.offsetValue() - 1).startsWith(" ")) {
                throw new DukeException("Hey hey hey, the format is delete <index>");
            }

        } else if (command.startsWith("find")) {
            if (!command.substring(Commands.FIND.offsetValue() - 1).startsWith(" ")) {
                throw new DukeException("Hey hey hey, the format is find <target>");
            }

        } else if (command.startsWith("todo")) {
            if (command.substring(Commands.TODO.offsetValue() - 1).isBlank()) {
                throw new DukeException("C'mon.. you're gonna do nothing?");
            }

        } else if (command.startsWith("event")) {
            if (!command.substring(command.indexOf("/")).startsWith("/at")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <event> "
                        + "<name> /at <duration>");
            } else if (command.substring(5).isBlank()) {
                throw new DukeException("Really? An event of nothing?");
            }

        } else {
            if (!command.substring(command.indexOf("/")).startsWith("/by")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <deadline> "
                        + "<name> /by <dueDate>");
            } else if (command.substring(8).isBlank()) {
                throw new DukeException("Y'know.. last i checked, doing nothing has no deadline");
            }
        }
    }

    private Command generateDoneCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.DONE.offsetValue());
            int index = Integer.parseInt(substring);
            return new DoneCommand(tdl, index);
        } catch (NumberFormatException e) {
            return new TipCommand("Dude, the format is done <index>");
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }

    private Command generateToDoCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.TODO.offsetValue());
            return new ToDoCommand(tdl, substring);
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }

    private Command generateEventCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.EVENT.offsetValue());
            String item = substring.substring(0, substring.indexOf("/"));
            String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
            return new EventCommand(tdl, item, duration);
        } catch (StringIndexOutOfBoundsException e) {
            return new TipCommand("Hold up... You got the format all wrong! It's supposed to "
                    + "be <event> <name> /at <duration>");
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }

    private Command generateDeadlineCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.DEADLINE.offsetValue());
            String item = substring.substring(0, substring.indexOf("/"));
            String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
            if (deadline.startsWith(" ")) {
                deadline = deadline.substring(1);
            }
            LocalDateTime dl = LocalDateTime.parse(deadline.replace(' ', 'T'),
                    DateTimeFormatter.ISO_DATE_TIME);
            return new DeadlineCommand(tdl, item, dl);

        } catch (StringIndexOutOfBoundsException e) {
            return new TipCommand("Hold up... You got the format all wrong! It's supposed to "
                    + "be <deadline> <name> /by <dueDate>");
        } catch (DateTimeParseException e) {
            return new TipCommand("Doesn't hurt to key in the date time as YYYY-MM-dd HH:mm right..?");
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }

    private Command generateDeleteCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.DELETE.offsetValue());
            int index = Integer.parseInt(substring);
            return new DeleteCommand(tdl, index);
        } catch (StringIndexOutOfBoundsException e) {
            return new TipCommand("And which item do you want to delete...?");
        } catch (NumberFormatException e) {
            return new TipCommand("Dude, the format is delete <index>");
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }

    private Command generateFindCommand(String command, ToDoList tdl) {
        try {
            formatChecker(command);
            String substring = command.substring(Commands.FIND.offsetValue());
            return new FindCommand(tdl, substring);
        } catch (DukeException e) {
            return new TipCommand(e.getMessage());
        }
    }
}
