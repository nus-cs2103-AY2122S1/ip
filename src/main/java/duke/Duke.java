package duke;

/**
 * Represents a Duke program, which is a Personal Assistant Chatbot
 * that helps a person to keep track of various tasks.
 */
public class Duke {

    TaskList userList;
    boolean isLoadSuccessful;

    public Duke() {
        isLoadSuccessful = loadData();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.getCommand(input);
        return getResponse(command, input);
    }

    public static void main(String[] args) {
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (Exception exception) {
            System.out.println("Unexpected error: " + exception);
        }
    }

    private boolean loadData() {
        // Load data and list task
        try {
            userList = TaskList.load();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // Deprecated
    private void run() throws Exception {
        Ui.printDukeLogo();

        if (isLoadSuccessful) {
            Ui.printExistingUserWelcomeMessage();
            Ui.printLeftoverTaskMessage(userList);
        } else {
            userList = new TaskList();
            Ui.printNewUserWelcomeMessage();
        }

        Ui.printInputRequestMessage();

        while (true) {
            String userInput = Ui.getUserInput();
            Command command = Parser.getCommand(userInput);
            doCommand(command, userInput);
        }
    }

    private String getResponse(Command command, String userInput) {
        switch (command) {
        case BYE:
            return Response.byeMessage();
        case LIST:
            return userList.toString();
        case DONE:
            int number = Integer.parseInt(userInput.substring(5));
            userList.markDone(number);
            return Response.markTaskDoneMessage();
        case DELETE:
            number = Integer.parseInt(userInput.substring(7));
            userList.remove(number);
            return Response.removedTaskMessage();
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                System.out.println("Error: " + exception);
            }
            return Response.addedTaskMessage();
        case FIND:
            return userList.find(userInput.substring(5));
        case CLEAR:
            userList.clear();
            return Response.TaskListClearedMessage();
        case INVALID:
            return Response.invalidCommandMessage();
        default:
            return Response.invalidCommandMessage();
        }
    }

    // Deprecated
    private void doCommand(Command command, String userInput) throws Exception {
        switch (command) {
        case BYE:
            Ui.printByeMessage();
            break;
        case LIST:
            userList.list();
            break;
        case DONE:
            int number = Integer.parseInt(userInput.substring(5));
            userList.markDone(number);
            break;
        case DELETE:
            number = Integer.parseInt(userInput.substring(7));
            userList.remove(number);
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                System.out.println("Error: " + exception);
            }
            break;
        case FIND:
            userList.find(userInput.substring(5));
            break;
        case CLEAR:
            userList.clear();
            break;
        case INVALID:
            Ui.printInvalidCommandMessage();
            break;
        default:
            throw new Exception();
        }
    }
}
