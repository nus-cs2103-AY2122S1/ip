package duke.parser;

/**
 * Parser class used parse user input.
 * Contains methods that
 * (i)    checks for keywords present in user input and returns the keyword found.
 */
public class Parser {
    /**
     * The checkForKeyword() method in Parser checks user input for keywords and responds
     * accordingly by calling the respective commands.
     *
     * @param des the user input into the Duke chat-box.
     * @return String type object that informs the run() method which
     * command should be called.
     */
    public String checkForKeyword(String des) {
        if (des.equals("help")) {
            return "help";
        } else if (des.equals("bye")) {
            return "bye";
        } else if (des.equals("list")) {
            return "list";
        } else if (des.contains("done") && des.startsWith("done")) {
            try {
                String sNum = des.substring(des.indexOf(' ') + 1);
                Integer.parseInt(sNum);
                return "done";
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (des.contains("delete") && des.startsWith("delete")) {
            try {
                String sNum = des.substring(des.indexOf(' ') + 1);
                Integer.parseInt(sNum);
                return "delete";
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (des.contains("deadline") && des.startsWith("deadline")) {
            return "deadline";
        } else if (des.contains("event") && des.startsWith("event")) {
            return "event";
        } else if (des.contains("todo") && des.startsWith("todo")) {
            return "todo";
        } else if (des.contains("find") && des.startsWith("find")) {
            return "find";
        }
        return null;
    }
}
