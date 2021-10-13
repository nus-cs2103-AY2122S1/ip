package duke;

import javafx.application.Platform;

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
     * Executes command and return response.
     *
     * @return response string from executing command
     */
    public String getResponse(String input) {
        Command command = Parser.getCommand(input);
        return getResponse(command, input);
    }

    private boolean loadData() {
        try {
            userList = TaskList.load();
            return true;
        } catch (Exception exception) {
            userList = new TaskList();
            return false;
        }
    }

    public String getWelcomeMessage() {
        StringBuilder resultString = new StringBuilder();
        boolean isNewUser = !isLoadSuccessful || userList.isEmpty();

        if (isNewUser) {

            resultString.append(Response.newUserWelcomeMessage() + "\n");
        } else {
            resultString.append(Response.existingUserWelcomeMessage() + "\n");
            resultString.append(Response.leftoverTaskMessage(userList));
        }

        return resultString.toString();
    }

    /**
     * Returns response string from executing the user command
     *
     * @return response string from executing the user command
     * */
    private String getResponse(Command command, String userInput) {
        int number;
        switch (command) {
        case HELLO:
            return Response.helloMessage();
        case BYE:
            Platform.exit();
            return Response.byeMessage();
        case LIST:
            if (userList.isEmpty()) {
                return userList.toString();
            }
            return "Here are your current tasks:\n" + userList.toString();
        case DONE:
            try {
                number = Integer.parseInt(userInput.substring(5));
            } catch (Exception exception) {
                return "Please enter your input as: done <number>\n" +
                        "e.g. done 1";
            }
            return userList.markDone(number);
        case DELETE:
            try {
                number = Integer.parseInt(userInput.substring(7));
            } catch (Exception exception) {
                return "Please enter your input as: delete <number>\n" +
                        "e.g. delete 2";
            }
            return userList.remove(number);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                return userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                return exception.getMessage();
            }
        case FIND:
            if (userInput.length() == 4) {
                return "Please enter as: find <keyword>\n" +
                        "e.g. find book";
            }
            return userList.find(userInput.substring(5));
        case CLEAR:
            return userList.clear();
        case UNDO:
            return userList.undo();
        case HELP:
            return Response.helpMessage();
        case INVALID:
            // Fallthrough
        default:
            return Response.invalidCommandMessage();
        }
    }

}
