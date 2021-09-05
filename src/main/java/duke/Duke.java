package duke;

/**
 * Represents a Duke program, which is a Personal Assistant Chatbot
 * that helps a person to keep track of various tasks.
 */
public class Duke {

    TaskList userList;

    Duke() {
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

    private void run() throws Exception {
        Ui.printDukeLogo();

        boolean isLoadSuccessful = loadData();

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
}
