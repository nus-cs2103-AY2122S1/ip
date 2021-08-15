package views.cli.strategies;

import java.util.ArrayList;
import java.util.List;

import domain.Deadline;
import domain.Event;
import domain.Task;
import domain.Todo;
import shared.DukeException;

public class MultiType extends RespondWith {
    private final String list = "list";
    private final String done = "done";
    private final String todo = "todo";
    private final String deadline = "deadline";
    private final String event = "event";
    private final String delete = "delete";
    private List<Task> userTasks;

    public MultiType() {
        userTasks = new ArrayList<>();
        commands.put(list, this::listString);
        commands.put(done, this::markDone);
        commands.put(todo, this::addTodo);
        commands.put(deadline, this::addDeadline);
        commands.put(event, this::addEvent);
        commands.put(delete, this::deleteTask);
    }

    private String listString(String _query) {
        if (userTasks.size() == 0) {
            return "You're clear (for now)" + System.lineSeparator();
        }
        String result = "Here are the tasks in your list:" + System.lineSeparator();

        for (int i = 0; i < userTasks.size(); i++) {
            result += String.format("%d. %s%s", (i + 1), userTasks.get(i), System.lineSeparator());
        }
        return result;
    }

    private String markDone(String query) throws DukeException {
        try {
            int index = Integer.parseInt(query.substring(done.length()).strip()) - 1;
            if (index >= userTasks.size() || index < 0) {
                throw DukeException.createIndexOutOfBoundsException(userTasks.size(), index + 1);
            }
            Task task = userTasks.get(index);
            task.finish();
            return String.format("Nice! I've marked this task as done:%s\t%s%s", System.lineSeparator(), task,
                    System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.ExceptionCode.INCORRECT_ARGS, "Please give a number");
        }
    }

    private String formatTaskCount() {
        String s = "";
        if (userTasks.size() != 1) {
            s = "s";
        }
        return String.format("Now you have %d task%s in the list.", userTasks.size(), s);
    }

    private String formatAdd(Task t) {
        return String.format("Got it. I've added this task:%s%s%s%s%s", System.lineSeparator(), t,
                System.lineSeparator(), formatTaskCount(), System.lineSeparator());
    }

    private String addTodo(String query) {
        Task task = new Todo(query.substring(todo.length()).strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addDeadline(String query) throws DukeException {
        String[] queries = query.substring(deadline.length()).split("/by");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Deadline(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addEvent(String query) throws DukeException {
        String[] queries = query.substring(event.length()).split("/at");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Event(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String deleteTask(String query) throws DukeException {
        try {
            int index = Integer.parseInt(query.substring(delete.length()).strip()) - 1;
            if (index >= userTasks.size() || index < 0) {
                throw DukeException.createIndexOutOfBoundsException(userTasks.size(), index + 1);
            }
            Task removedTask = userTasks.remove(index);
            return String.format("Noted. I've removed this task:%s%s%s%s%s", System.lineSeparator(), removedTask,
                    System.lineSeparator(), formatTaskCount(), System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.ExceptionCode.INCORRECT_ARGS, "Please give a number");
        }
    }

    @Override
    public String respond(String query) {
        try {
            String specialResponse = super.respond(query);
            if (specialResponse != null) {
                return specialResponse;
            }
            throw new DukeException(DukeException.ExceptionCode.UNPROCESSABLE_ENTITY);
        } catch (DukeException e) {
            return e.getMessage() + System.lineSeparator();
        }
    }
}
