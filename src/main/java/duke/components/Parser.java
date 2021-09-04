package duke.components;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Parser {
    private final String end;
    private final String display;
    private final String markDone;
    private final String deleteTask;
    private final String taskTodo;
    private final String taskDdl;
    private final String taskEve;
    private final DateTimeFormatter formatter;

    public Parser() {
        this.end = "bye";
        this.display = "list";
        this.markDone = "done";
        this.deleteTask = "delete";
        this.taskTodo = "todo";
        this.taskDdl = "deadline";
        this.taskEve = "event";
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm", Locale.ENGLISH);
    }

    public boolean isEnd(String input) {
        return input.equals(end);
    }

    public boolean isDisplay(String input) {
        return input.equals(display);
    }

    public boolean isDone(String input) {
        return input.equals(markDone);
    }

    public boolean isDelete(String input) {
        return input.equals(deleteTask);
    }

    public boolean isTodo(String input) {
        return input.equals(taskTodo);
    }

    public boolean isDeadline(String input) {
        return input.equals(taskDdl);
    }

    public boolean isEvent(String input) {
        return input.equals(taskEve);
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }

    public boolean isMarkDoneCommand(String input) {
        Pattern pattern = Pattern.compile("done\\s\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean isDeleteTaskCommand(String input) {
        Pattern patternDelete = Pattern.compile("delete\\s\\d+");
        Matcher matcherDelete = patternDelete.matcher(input);
        return matcherDelete.find();
    }
}
