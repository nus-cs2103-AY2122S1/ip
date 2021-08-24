package duke;
import duke.exceptions.*;
import java.time.LocalDate;

public class Parser {

    String command;
    String[] commandWords;

    public Parser(String command) {
        this.command = command;
        this.commandWords = command.split(" ");
    }

    // Gets the command
    public String getFirstWord() {
        return commandWords[0].toLowerCase();
    }

    public String getSecondWord() throws EmptyDescriptionException {
        if (commandWords.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        return commandWords[1].toLowerCase();
    }


    // For DONE
    public int getSecondInteger(int size) throws NotDoneRightException {
        if (command.toLowerCase().split(" ").length == 1
                || Integer.parseInt(command.split(" ")[1]) < 1
                || Integer.parseInt(command.split(" ")[1]) > size) {
            throw new NotDoneRightException("1", String.valueOf(size));
        } else {
            return Integer.parseInt(commandWords[1]);
        }
    }

    // For todo
    public String getTodoInfo() throws EmptyDescriptionException {
        if (commandWords.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
       return this.commandWords[1];
    }


    public String getDeadlineInfo() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }
        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/by", 2)[0];
    }


    public LocalDate getDeadlineDate() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return LocalDate.parse(allDetails.split("/by", 2)[1].strip());
        // TODO add DateNotAcceptedException
    }


    public String getEventInfo() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }
        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/at", 2)[0];
    }


    public String getEventLocation() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }
        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/at", 2)[1];
    }
}
