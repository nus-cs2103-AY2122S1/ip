import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void getCommand() {
        System.out.println("\t What would you like me to do?\n");
        String command = this.scanner.nextLine();
        String first = command.split(" ")[0];

        try {
            for (DukeCommands d : DukeCommands.values()) {
                if (d.command.equals(first)) {
                    d.action.execute(command);
                    break;
                } else if (d.command.equals("invalid")) {
                    DukeCommands.INVALID.action.execute(command);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            getCommand();
        }
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void greet() {
        System.out.println("\t Hello! I'm Duke");
    }

    public void displayError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void invalidCommand() throws DukeException {
        String errorMessage = "\t Sorry I do not understand this command \n";
        errorMessage += "\t Please use one of the following commands: \n";
        errorMessage += "\t \t list - To list the added tasks so far\n";
        errorMessage += "\t \t todo {description} - To add a ToDo task\n";
        errorMessage += "\t \t deadline {description} /by {time} - To add a Deadline task\n";
        errorMessage += "\t \t event {description} /at {time} - To add an Event task\n";
        errorMessage += "\t \t done {number} - To mark the indicated task as done\n";
        errorMessage += "\t \t delete {number} - To delete the indicated task\n";
        errorMessage += "\t \t bye (To exit programme)\n";
        throw new DukeException(errorMessage);
    }
}
