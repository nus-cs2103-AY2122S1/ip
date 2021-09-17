package duke;

import javafx.application.Platform;

import java.util.Scanner;

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

    private boolean loadData() {
        // Load data and list task
        try {
            userList = TaskList.load();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // Deprecated as program has moved to GUI
    private void run() throws Exception {

        System.out.print(getWelcomeMessage());

        while (true) {
            String userInput = getUserInput();
            Command command = Parser.getCommand(userInput);
            System.out.println(getResponse(command, userInput));
        }
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String getWelcomeMessage() {
        StringBuilder resultString = new StringBuilder();

        if (isLoadSuccessful) {
            resultString.append(Response.existingUserWelcomeMessage() + "\n");
            resultString.append(Response.leftoverTaskMessage(userList));
        } else {
            userList = new TaskList();
            resultString.append(Response.newUserWelcomeMessage() + "\n");
        }

        //resultString.append(Response.inputRequestMessage() + "\n");
        return resultString.toString();
    }

    private String getResponse(Command command, String userInput) {
        switch (command) {
        case HELLO:
            return Response.helloMessage();
        case BYE:
            Platform.exit();
            return Response.byeMessage();
        case LIST:
            return userList.toString();
        case DONE:
            int number = Integer.parseInt(userInput.substring(5));
            return userList.markDone(number);
        case DELETE:
            number = Integer.parseInt(userInput.substring(7));
            return userList.remove(number);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                return userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                return "Error: " + exception;
            }
        case FIND:
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
