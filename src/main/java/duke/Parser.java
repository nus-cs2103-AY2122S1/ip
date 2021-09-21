package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * This class encapsulates the mechanism to parse user commands.
 *
 * @author Kleon Ang
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private final TaskList tasks;
    private boolean toRewriteData;
    private boolean toFind;

    /**
     * Constructor for a Parser class.
     *
     * @param tasks A TaskList for the Parser to refer to.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.toRewriteData = false;
        this.toFind = false;
    }

    /**
     * Lists the tasks in the given TaskList as a string.
     *
     * @param tasks TaskList containing the tasks to list.
     * @return A string representation of the given tasks.
     * @throws DukeException A Duke-specific exception that may occur when listing tasks.
     */
    private String list(TaskList tasks) throws DukeException {
        String matching = this.toFind ? "matching " : "";
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no " + matching + "tasks in your list.");
        }
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

    /**
     * Marks the task of the given index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @return A string containing a success message for marking the task as done.
     * @throws DukeException A Duke-specific exception that may occur when marking tasks as done.
     */
    private String done(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + taskIndex + ".");
        }
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        return "Nice! I've marked this task as done:\n  " + doneTask;
    }

    /**
     * Finds tasks matching the given keywords.
     *
     * @param keywords A string containing one or more keywords.
     * @return A string representation of the matching tasks.
     * @throws DukeException A Duke-specific exception that may occur when finding tasks.
     */
    private String find(String keywords) throws DukeException {
        this.toFind = true;
        String[] splitKeywords = keywords.split(" ");
        TaskList matchingTasks = new TaskList();
        this.tasks.stream().filter(task -> Arrays.stream(splitKeywords)
                        .anyMatch(keyword -> task.containsKeyword(keyword) && !matchingTasks.contains(task)))
                .forEach(matchingTasks::add);
        return list(matchingTasks);
    }

    /**
     * Sets a given task to a given priority level.
     *
     * @param taskIndex The index of the task to set priority.
     * @param priorityToSet A string indicating which priority to set the task to.
     * @return A string containing a success message for setting a priority.
     * @throws DukeException A Duke-specific exception that may occur when setting priority.
     */
    private String priority(int taskIndex, String priorityToSet) throws DukeException {
        if (taskIndex <= 0 || taskIndex > this.tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + taskIndex + ".");
        }
        Task taskToSetPriority = this.tasks.get(taskIndex - 1);
        taskToSetPriority.setPriority(priorityToSet.toUpperCase());
        return "Nice! I've marked this task as " + taskToSetPriority.getPriority()
                + " priority :\n  " + taskToSetPriority;
    }

    /**
     * Getter for the rewrite status of the last parsed command.
     *
     * @return True if data in Storage needs to be rewritten.
     */
    public boolean needsToRewrite() {
        return this.toRewriteData;
    }

    /**
     * Parses the user input and runs the corresponding command.
     *
     * @param userInput A string containing the user input.
     * @return A string containing the output to the user.
     * @throws DukeException A Duke-specific exception that occurs when user input is parsed.
     */
    public String parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (userInput.equals("list")) {
            return list(this.tasks);
        } else {
            String[] commandArguments = userInput.split(" ", 2);
            String command = commandArguments[0];
            String arguments = "";
            if (commandArguments.length == 2) {
                arguments = commandArguments[1];
            }

            if (command.equals("done")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                int taskIndex = Integer.parseInt(arguments);
                return done(taskIndex);
            } else if (command.equals("deadline")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                String[] splitTask = arguments.split(" /by ");
                if (splitTask.length < 2) {
                    throw new DukeException("Please indicate a deadline using '/by'.");
                }
                String description = splitTask[0];
                String byString = splitTask[1];
                try {
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(byString, FORMATTER);
                    return this.tasks.addTask(new Deadline(description, deadlineDateTime));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
            } else if (command.equals("event")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                String[] splitTask = arguments.split(" /at ");
                if (splitTask.length < 2) {
                    throw new DukeException("Please indicate the event time frame using '/at'.");
                }
                String description = splitTask[0];
                String atString = splitTask[1];
                try {
                    LocalDateTime eventDateTime = LocalDateTime.parse(atString, FORMATTER);
                    return this.tasks.addTask(new Event(description, eventDateTime));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                }
            } else if (command.equals("todo")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                return this.tasks.addTask(new Todo(arguments));
            } else if (command.equals("delete")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                int counter = Integer.parseInt(arguments);
                return this.tasks.deleteTask(counter);
            } else if (command.equals("find")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The keyword to '" + command + "' cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = false;
                return find(arguments);
            } else if (command.equals("priority")) {
                if (commandArguments.length < 2) {
                    throw new DukeException("☹ OOPS!!! The '" + command + "' level cannot be empty.");
                }
                assert !arguments.equals("") : "Arguments required, cannot be empty.";
                this.toRewriteData = true;
                String[] splitArguments = arguments.split(" ");
                int taskIndex = Integer.parseInt(splitArguments[0]);
                String priorityToSet = splitArguments[1];
                return priority(taskIndex, priorityToSet);
            }
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
