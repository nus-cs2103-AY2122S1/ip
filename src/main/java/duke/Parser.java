package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Parser() {
    }

    public boolean isDoneCmd(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("done");
    }

    public boolean isValidTodo(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("todo");
    }

    public String getTodoDescription(String input) {
        return input.substring(5);
    }

    public boolean isValidDeadline(String input) {
        return input.length() >= 8 && input.substring(0, 8).equals("deadline");
    }

    public String getDeadlineDescription(String input) {
        return input.substring(9, input.indexOf("/")-1);
    }

    public boolean isValidEvent(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("event");
    }

    public String getEventDescription(String input) {
        return input.substring(6,  input.indexOf("/")-1);
    }

    public boolean isDeleteCmd(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("delete");
    }

    public String getDeadlineTime(String input) {
        return input.substring(input.indexOf("/") + 4);
    }

    public String getEventTime(String input) {
        return input.substring(input.indexOf("/") + 4);
    }

    public int getDeleteIdx(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }

    public boolean isInteger(String input) {
        return input.substring(5).matches("[0-9]+");
    }

    public LocalDateTime parseLocalDateTime(String time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (time.substring(0, 1).matches("[0-9]+")) {
            DateTimeFormatter anotherDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(time, anotherDTF);
        } else {
            return LocalDateTime.parse(time, dtf);
        }
    }

    public boolean isFindCmd(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("find");
    }
}
