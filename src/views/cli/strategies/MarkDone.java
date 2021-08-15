package views.cli.strategies;

import java.util.ArrayList;
import java.util.List;

import domain.Task;

// Level-3
public class MarkDone extends RespondWith {
    private List<Task> userTasks;

    public MarkDone() {
        userTasks = new ArrayList<>();
        commands.put("list", (_query) -> listString());
        commands.put("done", this::markDone);
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
