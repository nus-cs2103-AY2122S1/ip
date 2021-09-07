package duke;

/**
 * Deals with making sense of the user's command.
 * It takes in user inputs and filters it to its respective command.
 */
public class Parser {

    static final int DATE_PARSE_INDEX = 4;

    /**
     * @param command Command is the user input.
     */
    public Command parse(String command) {
        String[] commandArr = command.split(" ");
        int commandArrLength = commandArr.length;

        
        if (command.equals("save")) {
            return new SaveCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (commandArr[0].equals("done")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter the task number after \"done\"");
            }
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            return new DoneCommand(taskArrRef);
        } else if (commandArr[0].equals("delete")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter the task number after \"delete\"");
            }
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            return new DeleteCommand(taskArrRef);
        } else if (commandArr[0].equals("find")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter a keyword after \"find\"");
            }
            String keyword = commandArr[1];
            return new FindCommand(keyword);
        }
        
        
        boolean wrongArrayLength = commandArrLength <= 1;
        switch (commandArr[0]) {
        case "todo":
            if (wrongArrayLength) {
                return new ErrorCommand("The description of a todo cannot be empty!");
            } else {
                int spaceIndex = command.indexOf(" ");
                String description = command.substring(spaceIndex + 1);
                int descriptionLength = description.length();
                assert descriptionLength > 0 : "Todo Description shouldn't be empty";
                Task task = new Todo(description);
                return new TodoCommand(task);
            }

        case "deadline":
            if (wrongArrayLength) {
                return new ErrorCommand("The description of a deadline cannot be empty!");
            } else if (!command.contains("/by ")) {
                return new ErrorCommand("Remember to type input in this format:\""
                        + "[deadline] [task] /by [date]\"");
            } else {
                int spaceIndex = command.indexOf(" ");
                int byIndex = command.indexOf("/by ");
                String description = command.substring(spaceIndex + 1, byIndex - 1);
                int descriptionLength = description.length();
                assert descriptionLength > 0 : "Deadline Description shouldn't be empty";
                assert DATE_PARSE_INDEX == 4 : "DATE_PARSE_INDEX should be 4";
                String date = command.substring(byIndex + DATE_PARSE_INDEX);
                Task task = new Deadline(description, date);
                return new DeadlineCommand(task);
            }

        case "event":
            if (wrongArrayLength) {
                return new ErrorCommand("The description of an event cannot be empty!");
            } else if (!command.contains("/at ")) {
                return new ErrorCommand("Remember to enter event in this format:\""
                        + "[event] [task] /at [date]\"");
            } else {
                int spaceIndex = command.indexOf(" ");
                int atIndex = command.indexOf("/at ");
                String description = command.substring(spaceIndex + 1, atIndex - 1);
                int descriptionLength = description.length();
                assert descriptionLength > 0 : "Event Description shouldn't be empty";
                assert DATE_PARSE_INDEX == 4 : "DATE_PARSE_INDEX should be 4";
                String date = command.substring(atIndex + DATE_PARSE_INDEX);
                Task task = new Event(description, date);
                return new EventCommand(task);
            }

        default:
            return new ErrorCommand("I'm sorry, but I don't know what that means!");
        }


    }


}
