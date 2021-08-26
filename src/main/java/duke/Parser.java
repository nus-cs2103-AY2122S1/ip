package duke;

import java.util.regex.Pattern;

public class Parser {
    private String REGEX_DONE = "done [0-9]+";
    private String REGEX_DELETE = "delete [0-9]+";
    private String REGEX_TODO = "todo [\\w\\s-]+";
    private String REGEX_DEADLINE = "deadline [\\w\\s-]+ \\/by [\\w\\s-]+";
    private String REGEX_EVENT = "event [\\w\\s-]+ \\/at [\\w\\s-]+";

    public boolean parse(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.equals("list")) {
            String[] response = tasks.getStringArr();
            response[0] = "Here are the tasks in your list:";
            ui.printResponse(response);
        } else if (command.equals("bye")) {
            ui.printResponse("Bye. Hope to see you again soon!");
            return false;
        } else if (Pattern.matches(REGEX_DONE, command)) {
            String indexStr = command.substring(5);
            int index = Integer.parseInt(indexStr) - 1;
            String res = tasks.markAsDone(index);
            ui.printResponse("Nice! I've marked this task as done:", res);
        } else if (Pattern.matches(REGEX_DELETE, command)) {
            String indexStr = command.substring(7);
            int index = Integer.parseInt(indexStr) - 1;
            String removedTask = tasks.removeTask(index);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Noted. I've removed this task:", removedTask, numTasksLeft);
        } else if (command.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (Pattern.matches(REGEX_TODO, command)) {
            String name = command.substring(5);
            String taskAdded = tasks.addToDo(name);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else if (Pattern.matches(REGEX_DEADLINE, command)) {
            int breakPos = command.indexOf("/by");
            String name = command.substring(9, breakPos - 1);
            String due = command.substring(breakPos + 4);
            String taskAdded = tasks.addDeadline(name, due);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else if (Pattern.matches(REGEX_EVENT, command)) {
            int breakPos = command.indexOf("/at");
            String name = command.substring(6, breakPos - 1);
            String time = command.substring(breakPos + 4);
            String taskAdded = tasks.addEvent(name, time);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means ):");
        }

        return true;
    }
}
