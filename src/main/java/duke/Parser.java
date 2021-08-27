package duke;

import java.util.regex.Pattern;
import java.util.List;

public class Parser {
    private String DONE_REGEX = "done [0-9]+";
    private String DELETE_REGEX = "delete [0-9]+";
    private String TODO_REGEX = "todo [\\w\\s-]+";
    private String DEADLINE_REGEX = "deadline [\\w\\s-]+ \\/by [\\w\\s-]+";
    private String EVENT_REGEX = "event [\\w\\s-]+ \\/at [\\w\\s-]+";
    /** Regex for a Find command. */
    private String REGEX_FIND = "find [\\w\\s-]+";

    public boolean parse(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.equals("list")) {
            String[] response = tasks.getStringArr();
            response[0] = "Here are the tasks in your list:";
            ui.printResponse(response);
        } else if (command.equals("bye")) {
            ui.printResponse("Bye. Hope to see you again soon!");
            return false;
        } else if (Pattern.matches(DONE_REGEX, command)) {
            String indexStr = command.substring(5);
            int index = Integer.parseInt(indexStr) - 1;
            String res = tasks.markAsDone(index);
            ui.printResponse("Nice! I've marked this task as done:", res);
        } else if (Pattern.matches(DELETE_REGEX, command)) {
            String indexStr = command.substring(7);
            int index = Integer.parseInt(indexStr) - 1;
            String removedTask = tasks.removeTask(index);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Noted. I've removed this task:", removedTask, numTasksLeft);
        } else if (command.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (Pattern.matches(TODO_REGEX, command)) {
            String name = command.substring(5);
            String taskAdded = tasks.addToDo(name);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else if (Pattern.matches(DEADLINE_REGEX, command)) {
            int breakPos = command.indexOf("/by");
            String name = command.substring(9, breakPos - 1);
            String due = command.substring(breakPos + 4);
            String taskAdded = tasks.addDeadline(name, due);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else if (Pattern.matches(EVENT_REGEX, command)) {
            int breakPos = command.indexOf("/at");
            String name = command.substring(6, breakPos - 1);
            String time = command.substring(breakPos + 4);
            String taskAdded = tasks.addEvent(name, time);
            String numTasksLeft = tasks.numTasks();
            ui.printResponse("Got it. I've added this task:", taskAdded, numTasksLeft);
        } else if (Pattern.matches(REGEX_FIND, command)) {
            String textToSearch = command.substring(5);
            List<String> tasksFoundList = tasks.searchTasks(textToSearch);
            String[] tasksFoundArr = new String[tasksFoundList.size() + 1];
            tasksFoundArr[0] = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasksFoundList.size(); i++) {
                int tempIndex = i + 1;
                tasksFoundArr[tempIndex] = tempIndex + ". " + tasksFoundList.get(i);
            }
            ui.printResponse(tasksFoundArr);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means ):");
        }

        return true;
    }
}
