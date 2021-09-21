package duke.parser;

/**
 * Parser class used parse user input.
 * Contains method that
 * (i)    checks for keywords present in user input and returns the keyword found.
 */
public class Parser {
    /**
     * Returns String type object that informs the run() method which command should be called.
     * If no keyword is found, null is returned.
     *
     * @param des User input into the Duke chat-box.
     * @return keyword present in user input
     */
    public String checkForKeyword(String des) {
        if (des.equals("help")) {
            return "help";
        } else if (des.equals("bye")) {
            return "bye";
        } else if (des.equals("list")) {
            return "list";
        } else if (des.contains("done") && des.startsWith("done")) {
            return checkDoneDescription(des);
        } else if (des.contains("delete") && des.startsWith("delete")) {
            return checkDeleteDescription(des);
        } else if (des.contains("deadline") && des.startsWith("deadline")) {
            return "deadline";
        } else if (des.contains("event") && des.startsWith("event")) {
            return "event";
        } else if (des.contains("todo") && des.startsWith("todo")) {
            return "todo";
        } else if (des.contains("doafter") && des.startsWith("doafter")) {
            return checkDoAfterDescription(des);
        } else if (des.contains("find") && des.startsWith("find")) {
            return "find";
        } else {
            return null;
        }
    }

    /**
     * Returns "done" keyword if user input is valid.
     * If user input not valid, null is returned.
     *
     * @param des User input into the Duke chat-box.
     * @return String object representing done keyword.
     */
    private String checkDoneDescription(String des) {
        try {
            String stringNumber = des.substring(des.indexOf(' ') + 1);
            Integer.parseInt(stringNumber);
            return "done";
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns "delete" keyword if user input is valid.
     * If user input not valid, null is returned.
     *
     * @param des User input into the Duke chat-box.
     * @return String object representing delete keyword.
     */
    private String checkDeleteDescription(String des) {
        try {
            String stringNumber = des.substring(des.indexOf(' ') + 1);
            Integer.parseInt(stringNumber);
            return "delete";
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns "doafter" keyword if user input is valid.
     * If user input not valid, null is returned.
     *
     * @param des User input into the Duke chat-box.
     * @return String object representing doafter keyword.
     */
    private String checkDoAfterDescription(String des) {
        try {
            String stringNumber = des.substring(des.lastIndexOf(' ') + 1);
            Integer.parseInt(stringNumber);
            return "doafter";
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
