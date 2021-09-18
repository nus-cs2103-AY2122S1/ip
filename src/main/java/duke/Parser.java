package duke;

/**
 * Class to abstract the Parsing of the Messages taken as input from the Duke
 */
public class Parser {

    /**
     * Default Constructor for the Parse Class
     */
    public Parser() {

    }

    /**
     * Method to return the Type of Command parsed
     *
     * @param command The Command Passed for comparing to the different types of CommandTypes
     * @return The Type of Command Received
     */
    private Command.CommandTypes getCommand(String command) {
        try {
            if (command != null) {
                return Command.CommandTypes.valueOf(command.toUpperCase());
            } else {
                return Command.CommandTypes.UNKNOWN;
            }
        } catch (IllegalArgumentException e) {
            return Command.CommandTypes.UNKNOWN;
        }
    }

    /**
     * Method to return the specific Command processed from the given from the Command String
     *
     * @param taskDescription The Command inputted as a String
     * @return The specific Command in the given Command String
     */
    public Command parse(String taskDescription) {
        if (taskDescription == null || taskDescription.equals("")) {
            return new Command(Command.CommandTypes.UNKNOWN, "");
        } else {
            String taskObjective;
            String taskDetails;
            String[] task = taskDescription.split(" ", 2);
            taskObjective = task[0];
            taskDetails = task.length > 1 ? task[1].trim() : "";
            return new Command(getCommand(taskObjective), taskDetails);
        }
    }
}
