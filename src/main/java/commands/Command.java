import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected List<String> userInputList;

    public Command(String userInput) {
        this.userInputList = Arrays.asList(userInput.split(" "));
    }

    public abstract CommandLambda getCommandLambda();

    public enum ValidCommand {
        DONE,
        DELETE,
        LIST,
        DEADLINE,
        TODO,
        EVENT,
        BYE,
        INVALID
    }

    public static Command of(String userInput) {
        List<String> userInputList = Arrays.asList(userInput.split(" "));
        String userCommandString = userInputList.get(0);

        ValidCommand command;

        try {
            command = ValidCommand.valueOf(userCommandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = ValidCommand.INVALID;
        }

        if (command == ValidCommand.DONE) {
            return new DoneCommand(userInput);
        } else if (command == ValidCommand.DELETE) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.LIST) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.DEADLINE) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.TODO) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.EVENT) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.BYE) {
            return new DeadlineCommand(userInput);
        } else {
            return new InvalidCommand();
        }
    }
}