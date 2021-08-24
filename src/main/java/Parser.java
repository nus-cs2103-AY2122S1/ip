import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    enum Commands {
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

    public void parse(String command, ToDoList tdl, Ui ui, Duke chatBot) {
        if (command.equals(Commands.BYE.toString().toLowerCase())) {
            chatBot.exit(tdl);
        } else if (command.equals(Commands.LIST.toString().toLowerCase())) {
            tdl.displayList();
        } else if (command.startsWith(Commands.DONE.toString().toLowerCase())) {
            try {
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                tdl.markAsDone(index);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("And I'm supposed to guess which item you're done with?");
            } catch (IndexOutOfBoundsException e) {
                ui.prettyPrinter("Where's this item? It's not even on the list!");
            }
        } else if (command.startsWith(Commands.TODO.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                tdl.addToDo(substring);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.EVENT.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addEvent(item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <event> <name> /at <duration>");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
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
                tdl.addDeadline(item, dl);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <deadline> <name> /by <dueDate>");
            } catch (DateTimeParseException e) {
                System.out.println("Please key in the date time as YYYY-MM-dd HH:mm");
            } catch (DukeException e) {
                ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DELETE.toString().toLowerCase())) {
            try {
                String substring = command.substring(7);
                int index = Integer.parseInt(substring);
                tdl.delete(index);
            } catch (StringIndexOutOfBoundsException e) {
                ui.prettyPrinter("And which item do you want to delete...? Try again :/");
            } catch (IndexOutOfBoundsException e) {
                ui.prettyPrinter("You're trying to delete something non-existent? Damn who is this guy?");
            }
        } else {
            ui.prettyPrinter("I'm confused... I need a raise...");
        }
    }

    private static void formatChecker(String command) throws DukeException {
        if (command.startsWith("todo")) {
            if (command.substring(4).isBlank()) {
                throw new DukeException("C'mon.. you're gonna do nothing?");
            }
        } else if (command.startsWith("event")) {
            if (!command.substring(command.indexOf("/")).startsWith("/at")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <event> <name> /at <duration>");
            } else if (command.substring(5).isBlank()) {
                throw new DukeException("Really? An event of nothing?");
            }
        } else {
            if (!command.substring(command.indexOf("/")).startsWith("/by")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <deadline> <name> /by <dueDate>");
            } else if (command.substring(8).isBlank()) {
                throw new DukeException("Hold up.. last i checked doing nothing has no deadline");
            }
        }
    }
}
