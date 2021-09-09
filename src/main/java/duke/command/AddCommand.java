package duke.command;

import duke.exceptions.DeadlineFormatException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EventFormatException;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.tasks.*;

public class AddCommand extends Command {
    private boolean isExit;

    public AddCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String addTodo() throws EmptyDescriptionException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getTodoDescription();
        Todo todo = new Todo(taskDescription, false);
        String addedTask = tasks.addTask(todo);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have " +
                tasks.getSize() + " tasks in the list.";
    }

    public String addDeadline() throws EmptyDescriptionException, DeadlineFormatException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getDeadlineDescription();
        String date = parser.getDeadlineDate();
        Deadline deadline = new Deadline(taskDescription, date, false);
        String addedTask = tasks.addTask(deadline);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have " +
                tasks.getSize() + " tasks in the list.";
    }

    public String addEvent() throws EmptyDescriptionException, EventFormatException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getEventDescription();
        String date = parser.getEventDate();
        Event event = new Event(taskDescription, date, false);
        String addedTask = tasks.addTask(event);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have " +
                tasks.getSize() + " tasks in the list.";
    }
}
