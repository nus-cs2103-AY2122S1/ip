import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public ToDo toDoFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7);
        ToDo ans = new ToDo(desc);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public Deadline deadlineFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(by: ") - 1);
        String by = s.substring(s.indexOf("(by: ") + 5, s.length() - 1);
        Deadline ans = new Deadline(desc, by);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public Event eventFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(at: ") - 1);
        String at = s.substring(s.indexOf("(at: ") + 5, s.length() - 1);
        Event ans = new Event(desc, at);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public Task taskFromString(String s) {
        String cat = s.substring(1, 2);
        return cat.equals("T")
                ? toDoFromString(s)
                : cat.equals("D")
                ? deadlineFromString(s)
                : eventFromString(s);
    }

    public boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDone(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("done");
    }

    public boolean isDeadline(String input) {
        return input.length() >= 8 && input.substring(0, 8).equals("deadline");
    }

    public boolean isEvent(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("event");
    }

    public boolean isToDo(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("todo");
    }

    public boolean isDelete(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("delete");
    }

    public boolean isBye(String input) {
        return input.equals("bye");
    }

    public boolean isList(String input) {
        return input.equals("list");
    }
}
