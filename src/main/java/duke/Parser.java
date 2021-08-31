package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class encapsulates the mechanism to parse user commands.
 *
 * @author Kleon Ang
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private final TaskList tasks;
    private boolean toRewriteData;
    private boolean isExit;
    private boolean isFind;

    /**
     * Constructor for a Parser class.
     *
     * @param tasks A TaskList for the Parser to refer to.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.toRewriteData = false;
        this.isExit = false;
        this.isFind = false;
    }

    private String list(TaskList tasks) throws DukeException {
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
       return tasksBuilder.toString();
    }

    private String done(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task doneTask = tasks.get(counter - 1);
        doneTask.markAsDone();
        return "Nice! I've marked this task as done:\n  " + doneTask;
    }

    /**
     * Getter for the rewrite status of the last parsed command.
     *
     * @return True if data in Storage needs to be rewritten.
     */
    public boolean toRewrite() {
        return this.toRewriteData;
    }

    /**
     * Getter for the exit status of the last parsed command.
     *
     * @return True if ready to exit Duke after "bye" command.
     */
    public boolean toExit() {
        return this.isExit;
    }

    /**
     * Parses the user input and runs the corresponding command.
     *
     * @param readIn A string containing the user input.
     * @return A TaskList containing the updated Tasks after command is parsed.
     * @throws DukeException A Duke-specific exception that occurs when user input is parsed.
     */
    public String parse(String readIn) throws DukeException {
        if (readIn.equals("bye")) {
            this.isExit = true;
            return "Bye. Hope to see you again soon!";
        } else if (readIn.equals("list")) {
            return list(this.tasks);
        } else {
            String[] commandArguments = readIn.split(" ", 2);
            String command = commandArguments[0];
            String arguments = "";
            if (commandArguments.length == 2) {
                arguments = commandArguments[1];
            }

            if (command.equals("done")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                int counter = Integer.parseInt(arguments);
                return done(counter);
            } else if (command.equals("deadline")) {
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
                    return this.tasks.addTask(new Deadline(description, by));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
            } else if (command.equals("event")) {
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
                    return this.tasks.addTask(new Event(description, at));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
            } else if (command.equals("todo")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                return this.tasks.addTask(new Todo(arguments));
            } else if (command.equals("delete")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                this.toRewriteData = true;
                int counter = Integer.parseInt(arguments);
                return this.tasks.deleteTask(counter);
            } else if (command.equals("find")) {
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
                return list(matchingTasks);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
