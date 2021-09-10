package duke;

public class Parser {

    public Parser() {

    }

    public boolean parseDone(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("done ");
    }

    public boolean parseToDo(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("todo ");
    }

    public boolean parseDeadline(String input) {
        return input.length() >= 9 && input.substring(0, 9).equals("deadline ");
    }

    public boolean parseEvent(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("event ");
    }

    public boolean parseDelete(String input) {
        return input.length() >= 7 && input.substring(0, 7).equals("delete ");
    }

    public String[] storageDeadline(String input) {
        int indexOfOpenBracketD = input.indexOf("(");
        int indexOfCloseBracketD = input.indexOf(")");
        String deadlineDate = input.substring(indexOfOpenBracketD + 4, indexOfCloseBracketD);
        String deadlineTitle = input.substring(9, indexOfOpenBracketD);
        String[] deadlineWords = {deadlineDate, deadlineTitle};
        return deadlineWords;
    }

    public String[] storageEvent(String input) {
        int indexOfOpenBracketE = input.indexOf("(");
        int indexOfCloseBracketE = input.indexOf(")");
        String eventDate = input.substring(indexOfOpenBracketE + 4, indexOfCloseBracketE);
        String eventTitle = input.substring(9, indexOfOpenBracketE);
        String[] eventWords = {eventDate, eventTitle};
        return eventWords;
    }
}
