import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeInputProcessor {

    private final String userInput;

    public DukeInputProcessor(String userInput) {
        this.userInput = userInput;
    }

    public final Operation checkOperation() throws DukeException {
        // if it is "bye", we return false to indicate operation stoppage
        if (checkBye()) {
            return Operation.bye;
        } else if (checkList()) {
            return Operation.list;
        } else if (checkDone()) {
            return Operation.done;
        } else if (checkDelete()) {
            return Operation.delete;
        } else if (checkTodo()) {
            return Operation.todo;
        } else if (checkEvent()) {
            return Operation.event;
        } else if (checkDeadline()) {
            return Operation.deadline;
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        
    }

    private boolean checkBye() {
        return this.userInput.equals("bye");
    }

    private boolean checkList() {
        return this.userInput.equals("list");
    }

    private boolean checkDone() {
        Pattern donePattern = Pattern.compile("^done\\h\\d+$");
        Matcher doneMatcher = donePattern.matcher(this.userInput);
        return doneMatcher.find();
    }

    private boolean checkDelete() {
        Pattern deletePattern = Pattern.compile("^delete\\h\\d+$");
        Matcher deleteMatcher = deletePattern.matcher(this.userInput);
        return deleteMatcher.find();
    }

    private boolean checkTodo() {
        Pattern todoPattern = Pattern.compile("^todo\\h\\w.*");
        Matcher todoMatcher = todoPattern.matcher(this.userInput);
        return todoMatcher.find();
    }

    private boolean checkEvent() {
        Pattern eventPattern = Pattern.compile("^event\\h\\w.*/at\\h\\w.*");
        Matcher eventMatcher = eventPattern.matcher(this.userInput);
        return eventMatcher.find();
    }

    private boolean checkDeadline() {
        Pattern deadlinePattern = Pattern.compile("^deadline\\h\\w.*/by\\h\\w.*");
        Matcher deadlineMatcher = deadlinePattern.matcher(this.userInput);
        return deadlineMatcher.find();
    }

}
