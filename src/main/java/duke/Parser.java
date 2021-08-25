package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final TaskList tasks;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private boolean toRewriteData;
    private boolean isExit;
    private boolean isFind;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.toRewriteData = false;
        this.isExit = false;
        this.isFind = false;
    }

    private void list(TaskList tasks) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }
        String matching = this.isFind ? "matching " : "";
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append("Here are the ").append(matching).append("tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            Task currentTask = tasks.get(i);
            tasksBuilder.append(counter).append(".").append(currentTask);
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        Ui.printReply(tasksBuilder.toString());
    }

    private void done(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task doneTask = tasks.get(counter - 1);
        doneTask.markAsDone();
        Ui.printReply("Nice! I've marked this task as done:\n  " + doneTask);
    }

    public boolean toRewrite() {
        return this.toRewriteData;
    }

    public boolean toExit() {
        return this.isExit;
    }

    public TaskList parse(String readIn) throws DukeException {
        if (readIn.equals("bye")) {
            Ui.printReply("Bye. Hope to see you again soon!");
            this.isExit = true;
        } else if (readIn.equals("list")) {
            list(this.tasks);
        } else {
            String[] commandArguments = readIn.split(" ", 2);
            String command = commandArguments[0];
            String arguments = "";
            if (commandArguments.length == 2) {
                arguments = commandArguments[1];
            }

            switch (command) {
            case "done": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                int counter = Integer.parseInt(arguments);
                done(counter);
                break;
            }
            case "deadline": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                String[] splitTask = arguments.split(" /by ");
                if (splitTask.length < 2) {
                    throw new DukeException("Please indicate a deadline using '/by'.");
                }
                String description = splitTask[0];
                String byString = splitTask[1];
                try {
                    LocalDateTime by = LocalDateTime.parse(byString, FORMATTER);
                    this.tasks.addTask(new Deadline(description, by));
                } catch (DateTimeParseException e) {
                    Ui.printReply("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
                break;
            }
            case "event": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                String[] splitTask = arguments.split(" /at ");
                if (splitTask.length < 2) {
                    throw new DukeException("Please indicate the event time frame using '/at'.");
                }
                String description = splitTask[0];
                String atString = splitTask[1];
                try {
                    LocalDateTime at = LocalDateTime.parse(atString, FORMATTER);
                    this.tasks.addTask(new Event(description, at));
                } catch (DateTimeParseException e) {
                    Ui.printReply("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
                break;
            }
            case "todo": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                tasks.addTask(new Todo(arguments));
                break;
            }
            case "delete": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                int counter = Integer.parseInt(arguments);
                tasks.deleteTask(counter);
                break;
            }
            case "find": {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The keyword(s) of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = false;
                this.isFind = true;
                String[] splitKeywords = arguments.split(" ");
                TaskList matchingTasks = new TaskList();
                for (Task task : this.tasks) {
                    for (String keyword : splitKeywords) {
                        if (task.containsKeyword(keyword) && !matchingTasks.contains(task)) {
                            matchingTasks.add(task);
                        }
                    }
                }
                list(matchingTasks);
                break;
            }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return tasks;
    }
}
