package views.cli.strategies;

import java.util.ArrayList;
import java.util.List;

import domain.Deadline;
import domain.Event;
import domain.Task;
import domain.Todo;

// Level-4
public class MultiType extends RespondWith {
    private final String list = "list";
    private final String done = "done";
    private final String todo = "todo";
    private final String deadline = "deadline";
    private final String event = "event";
    private List<Task> userTasks;

    public MultiType() {
        userTasks = new ArrayList<>();
        commands.put(list, (_query) -> listString());
        commands.put(done, this::markDone);
        commands.put(todo, this::addTodo);
        commands.put(deadline, this::addDeadline);
        commands.put(event, this::addEvent);
    }

    private String listString() {
        String result = "";

        for (int i = 0; i < userTasks.size(); i++) {
            result += String.format("%d. %s%s", (i + 1), userTasks.get(i), System.lineSeparator());
        }
        return result;
    }

    private String markDone(String query) {
        int index = Integer.parseInt(query.split(" ")[1]) - 1;
        Task task = userTasks.get(index);
        task.finish();
        return String.format("Nice! I've marked this task as done:%s\t%s%s", System.lineSeparator(), task,
                System.lineSeparator());
    }

    private String formatAdd(Task t) {
        String s = "";
        if (userTasks.size() != 1) {
            s = "s";
        }
        return String.format("Got it. I've added this task:%s%s%sNow you have %d task%s in the list.%s",
                System.lineSeparator(), t, System.lineSeparator(), userTasks.size(), s, System.lineSeparator());
    }

    private String addTodo(String query) {
        Task task = new Todo(query.substring(todo.length()).strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addDeadline(String query) throws IndexOutOfBoundsException {
        String[] queries = query.substring(deadline.length()).split("/by");
        Task task = new Deadline(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addEvent(String query) throws IndexOutOfBoundsException {
        String[] queries = query.substring(event.length()).split("/at");
        Task task = new Event(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    @Override
    public String respond(String query) {
        String specialResponse = super.respond(query);
        if (specialResponse != null) {
            return specialResponse;
        }
        userTasks.add(new Task(query));
        return "added: " + query + System.lineSeparator();
    }
}
