import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    enum Commands {
        BYE, LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    private static String name = "Duke";
    private boolean isRunning = false;
    private ToDoList tdl;
    private Storage storage;
    private Ui ui;

    public Duke() {
        this.isRunning = true;
        this.ui = new Ui(Duke.name);
        this.ui.greeting();
        this.tdl = new ToDoList(Duke.name);
        this.storage = new Storage(this.tdl);
        this.storage.reloadTask();

    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        Scanner input = new Scanner(System.in);
        while (chatBot.isRunning()) {
            String command = input.nextLine();
            chatBot.startBot(command);
        }
    }

    private void startBot(String command) {
        if (command.equals(Commands.BYE.toString().toLowerCase())) {
            exit(this.tdl);
        } else if (command.equals(Commands.LIST.toString().toLowerCase())) {
            this.tdl.displayList();
        } else if (command.startsWith(Commands.DONE.toString().toLowerCase())) {
            try {
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                tdl.markAsDone(index);
            } catch (StringIndexOutOfBoundsException e) {
                this.ui.prettyPrinter("And I'm supposed to guess which item you're done with?");
            } catch (IndexOutOfBoundsException e) {
                this.ui.prettyPrinter("Where's this item? It's not even on the list!");
            }
        } else if (command.startsWith(Commands.TODO.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                tdl.addToDo(substring);
            } catch (StringIndexOutOfBoundsException e) {
                this.ui.prettyPrinter("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                this.ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.EVENT.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addEvent(item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                this.ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <event> <name> /at <duration>");
            } catch (DukeException e) {
                this.ui.prettyPrinter(e.getMessage());
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
                this.ui.prettyPrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <deadline> <name> /by <dueDate>");
            } catch (DateTimeParseException e) {
                System.out.println("Please key in the date time as YYYY-MM-dd HH:mm");
            } catch (DukeException e) {
                this.ui.prettyPrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DELETE.toString().toLowerCase())) {
            try {
                String substring = command.substring(7);
                int index = Integer.parseInt(substring);
                this.tdl.delete(index);
            } catch (StringIndexOutOfBoundsException e) {
                this.ui.prettyPrinter("And which item do you want to delete...? Try again :/");
            } catch (IndexOutOfBoundsException e) {
                this.ui.prettyPrinter("You're trying to delete something non-existent? Damn who is this guy?");
            }
        } else {
           this.ui.prettyPrinter("I'm confused... I need a raise...");
        }
    }

    private void exit(ToDoList tdl) {
        this.isRunning = false;
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Wow! I can get off work now :D");
        this.storage.save();
        System.out.println("Saved your work by the way!");
        System.out.println("========== " + Duke.name + " ===========\n");
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

    public boolean isRunning() {
        return isRunning;
    }
}