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
        return input.length() >= 6 && input.substring(0, 7).equals("delete ");
    }
}
