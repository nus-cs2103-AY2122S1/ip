package duke;

import java.time.LocalDate;

/**
 * Parser class containing static methods for the parsing of Duke commands and
 * inputs.
 */
public class Parser {
    /**
     * Executes a command on the input task list and storage objects, given
     * an input.
     * @param tasks List of tasks.
     * @param storage Storage object.
     * @param input Command input.
     * @return Boolean flag representing whether the program should terminate.
     * If the flag is true, the program should terminate; otherwise, the program
     * should continue running.
     * @throws Exception
     */
    public static boolean runCommand(TaskList tasks, Storage storage,
            String input) throws Exception {
        var parameters = input.split(" ");
        var command = parameters[0];
        if (command.equals("bye") && parameters.length == 1) {
            Ui.printBye();
            return true;
        } else if (command.equals("list") && parameters.length == 1) {
            Ui.printTaskList(tasks);
        } else if (command.equals("done") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            task.toggle(true);
            storage.writeTaskList(tasks);
            Ui.printBanner(new String[] {
                "Nice! I've marked this task as done:",
                "  " + Ui.renderTask(task)
            });
        } else if (command.equals("delete") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            tasks.remove(i);
            storage.writeTaskList(tasks);
            Ui.printBanner(new String[] {
                "Noted. I've removed this task:",
                "  " + Ui.renderTask(task),
                String.format("Now you have %d tasks in the list.",
                        tasks.size()),
            });
        } else if (command.equals("todo")) {
            if (parameters.length == 1) {
                throw new Exception(
                    "The description of a todo cannot be empty."
                );
            }
            String taskLine = input.replace("todo", "").strip();
            var task = Parser.parseTaskLine(taskLine, TaskType.TODO);
            tasks.add(task);
            storage.writeTaskList(tasks);
            Ui.printTaskAdded(tasks, task);
        } else if (command.equals("deadline") && input.contains("/by")) {
            String taskLine = input.replace("deadline", "").strip();
            var task = Parser.parseTaskLine(taskLine, TaskType.DEADLINE);
            tasks.add(task);
            storage.writeTaskList(tasks);
            Ui.printTaskAdded(tasks, task);
        } else if (command.equals("event") && input.contains("/at")) {
            String taskLine = input.replace("event", "").strip();
            var task = Parser.parseTaskLine(taskLine, TaskType.EVENT);
            tasks.add(task);
            storage.writeTaskList(tasks);
            Ui.printTaskAdded(tasks, task);
        } else {
            throw new Exception(
                "I'm sorry, but I don't know what that means :-("
            );
        }
        return false;
    }

    /**
     * Parses a task line into a task.
     * @param taskLine Task line.
     * @param taskType Type of task.
     * @return Task object corresponding to the input parameters.
     */
    public static Task parseTaskLine(String taskLine, TaskType taskType) {
        switch (taskType) {
        case TODO:
            return new Todo(taskLine);
        case DEADLINE:
            String[] deadlineParts = taskLine.split("\\s+/by\\s+", 2);
            return new Deadline(
                deadlineParts[0],
                LocalDate.parse(deadlineParts[1])
            );
        case EVENT:
            String[] eventParts = taskLine.split("\\s+/at\\s+", 2);
            return new Event(
                eventParts[0],
                LocalDate.parse(eventParts[1])
            );
        default:
            throw new UnsupportedOperationException(
                "task type is not a valid enum value"
            );
        }
    }
}
