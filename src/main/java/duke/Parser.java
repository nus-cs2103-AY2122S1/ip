package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Parser {
    private TaskList tasks;

    Parser(TaskList taskList) {
        tasks = taskList;
    }

    boolean isExit(String input) {
        return input.equals("bye");
    }

    String[] parse(String input) throws DukeException {
        if (input.equals("list")) {
            return list();
        } else if (input.startsWith("done")) {
            return finishTask(input);
        } else if (input.startsWith("delete")) {
            return delete(input);
        } else {
            return addTask(input);
        }
    }

    private String[] list() {
        List<String> out = new ArrayList<>();
        out.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            out.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        return out.toArray(new String[0]);
    }

    private String[] finishTask(String input) {
        int ind = Integer.parseInt(input.split(" ", 2)[1]);
        Task task = tasks.get(ind - 1);
        task.setDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + task};
    }

    private String[] addTask(String input) throws DukeException {
        Task task = makeTask(input);
        tasks.add(task);
        return new String[]{
                "Got it. I've added this task:", "  " + task, getTasksLeftMsg()};
    }

    private Task makeTask(String input) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String[] parts = input.split(" ", 2);
        String type = parts[0];
        switch (type) {
        case "todo":
            if (parts.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(parts[1]);
        case "deadline": {
            String[] subparts = parts[1].split(" /by ", 2);
            return new Deadline(subparts[0], LocalDateTime.parse(subparts[1], formatter));
        }
        case "event": {
            String[] subparts = parts[1].split(" /at ", 2);
            return new Event(subparts[0], LocalDateTime.parse(subparts[1], formatter));
        }
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String[] delete(String input) {
        int ind = Integer.parseInt(input.split(" ", 2)[1]);
        Task task = tasks.remove(ind - 1);
        return new String[]{"Noted. I've removed this task:", "  " + task, getTasksLeftMsg()};
    }

    private String getTasksLeftMsg() {
        return String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }
}
