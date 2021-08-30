package duke;

public class Parser {

    public static Duke.RequestType parse(String userInput) {

        Duke.RequestType userRequest;
        if (userInput.equals("list")) {
            userRequest = Duke.RequestType.DEFAULT;
        } else if (userInput.startsWith("find")) {
            userRequest = Duke.RequestType.FIND;
        } else if (userInput.startsWith("done")) {
            userRequest = Duke.RequestType.DONE;
        } else if (userInput.startsWith("delete")) {
            userRequest = Duke.RequestType.DELETE;
        } else if (userInput.length() > 8 && userInput.startsWith("deadline") && userInput.contains("/by")) {
            userRequest = Duke.RequestType.DEADLINE;
        } else if (userInput.length() > 5 && userInput.startsWith("event") && userInput.contains("/at")) {
            userRequest = Duke.RequestType.EVENT;
        } else if (userInput.startsWith("todo")) {
            userRequest = Duke.RequestType.TODO;
        } else {
            userRequest = Duke.RequestType.UNUSUAL;
        }

        return userRequest;
    }

}
