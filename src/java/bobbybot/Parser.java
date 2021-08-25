package bobbybot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    /**
     * Performs command based on String user input
     * @param userInput string command for chatbot
     * @throws InvalidArgumentException Invalid or no arguments given
     * @throws TooManyArgumentsException Too many /by or /at connectors
     */
    public void parseCommand(String userInput, TaskList tasks, Ui ui) throws InvalidArgumentException,
            TooManyArgumentsException {
        List<String> userInputList = new LinkedList<>(Arrays.asList(userInput.split(" ")));
        BotCommand command = BotCommand.valueOf(userInputList.get(0).toUpperCase());
        String description;
        String[] userInputArgs;
        switch (command) {
        case BYE:
            ui.sayBye();
            break;
        case LIST:
            tasks.printList();
            break;
        case DONE:
            tasks.markAsDone(Integer.parseInt(userInputList.get(1)));
            break;
        case DELETE:
            // check delete argument
            if (!isNumeric(userInputList.get(1))) {
                throw new InvalidArgumentException("Delete argument is not numeric");
            }
            tasks.deleteTask(Integer.parseInt(userInputList.get(1)));
            break;
        case TODO:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for todo");
            }
            description = String.join(" ", userInputList);
            tasks.createToDo(description);
            break;
        case DEADLINE:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for deadline");
            }
            //split description and by
            userInputArgs = String.join(" ", userInputList).split("/by ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given for deadline");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /by ");
            }
            description = userInputArgs[0];
            String by = userInputArgs[1];
            tasks.createDeadline(description, by);
            break;
        case EVENT:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for event");
            }
            //split description and at
            userInputArgs = String.join(" ", userInputList).split("/at ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /at");
            }
            description = userInputArgs[0];
            String at = userInputArgs[1];
            tasks.createEvent(description, at);
            break;
        case FIND:
            System.out.println("Placeholder for find command");
            break;
        }
    }

    /**
     * Helper function to check if string is numeric
     * @param str string to test if numeric
     * @return boolean
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
