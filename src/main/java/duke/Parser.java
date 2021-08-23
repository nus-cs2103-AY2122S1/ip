package duke;

import duke.command.*;

import duke.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final String userInput;
    private final TaskList tasks;

    public Parser(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    public final Command checkOperation() throws DukeException {
        // if it is "bye", we return false to indicate operation stoppage
        if (checkBye()) {
            return new ExitCommand();
        } else if (checkList()) {
            return new ListCommand(this.tasks);
        } else if (checkDone()) {
            return new DoneCommand(userInput, tasks);
        } else if (checkDelete()) {
            return new DeleteCommand(userInput, tasks);
        } else if (checkFind()) {
            return new FindCommand(userInput, tasks);
        } else if (checkTodo()) {
            return new AddCommand(AddCommandType.todo, userInput, tasks);
        } else if (checkEvent()) {
            return new AddCommand(AddCommandType.event, userInput, tasks);
        } else if (checkDeadline()) {
            return new AddCommand(AddCommandType.deadline, userInput, tasks);
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

    private boolean checkFind() {
        Pattern findPattern = Pattern.compile("^find\\h\\w.*");
        Matcher findMatcher = findPattern.matcher(this.userInput);
        return findMatcher.find();
    }

}
