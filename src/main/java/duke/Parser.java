package duke;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final Map<String, ThrowingBiFunction<TaskList, String, String>> functions = new HashMap<>() {
        {
            put("list", (tasks, input) -> list(tasks));
            put("done", (tasks, input) -> markDone(tasks, input));
            put("todo", (tasks, input) -> addToDo(tasks, input));
            put("deadline", (tasks, input) -> addDeadline(tasks, input));
            put("event", (tasks, input) -> addEvent(tasks, input));
            put("delete", (tasks, input) -> deleteTask(tasks, input));
            put("find", (tasks, input) -> findTasks(tasks, input));
        }
    };

    /**
     * Parse the user input string and execute the respective command given in the functions Map.
     * @param tasks Current Tasklist being used by the program.
     * @param input Input String for the command.
     * @return String Result to be printed by the UI class.
     */
    public String dispatch(TaskList tasks, String input) {
        String[] cmd = input.split(" ", 2);
        assert cmd != null : "Input String cannot be empty";
        return functions.getOrDefault(cmd[0], (a, b) -> "FOOLISH MORTAL, I CAN'T EXECUTE THAT COMMAND. TRY AGAIN:")
                .apply(tasks, cmd.length > 1 ? cmd[1] : "");
    }

    private String list(TaskList tasks) {
        StringBuilder output = new StringBuilder("YOU WISH FOR THESE FOOLISH THINGS:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format(" %s. %s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    private String markDone(TaskList tasks, String input) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("YOU HAVE NO TASKS YOU FOOL.");
        }
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > tasks.size()) {
                throw new DukeException("INVALID TASK NUMBER YOU FOOL.");
            }
            Task currentTask = tasks.get(index - 1);
            if (currentTask.getIsDone()) {
                return String.format("YOU ALREADY DID THIS YOU FOOL.");
            }
            tasks.setDone(index - 1);
            return String.format("YOU SAY YOU'VE COMPLETED THIS TASK:\n " + currentTask);
        } catch (NumberFormatException e) {
            throw new DukeException("DO YOU NOT KNOW WHAT A NUMBER IS, FOOLISH HUMAN?");
        }
    }

    private String findTasks(TaskList tasks, String input) throws DukeException {
        assert input != null : "Input String cannot be empty";
        if (tasks.isEmpty()) {
            throw new DukeException("YOU HAVE NO TASKS YOU FOOL.");
        }
        return tasks.findTasks(input);
    }

    private String deleteTask(TaskList tasks, String input) throws DukeException {
        assert input != null : "Input String cannot be empty";
        if (tasks.isEmpty()) {
            throw new DukeException("YOU HAVE NO TASKS YOU FOOL.");
        }
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > tasks.size()) {
                throw new DukeException("INVALID TASK NUMBER YOU FOOL.");
            }
            Task currentTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            return String.format(String.format("YOU'VE CHOSEN TO ABANDON THIS TASK:\n %s\n YOU HAVE %d TASKS LEFT.",
                    currentTask, tasks.size()));
        } catch (NumberFormatException e) {
            throw new DukeException("DO YOU NOT KNOW WHAT A NUMBER IS, FOOLISH HUMAN?");
        }
    }

    private String addToDo(TaskList tasks, String taskName) throws DukeException {
        assert taskName != null : "taskName String cannot be empty";
        if (taskName.equals("")) {
            throw new DukeException("NAME YOUR TASK. DON'T WASTE MY TIME.");
        }
        Task newTask = new ToDo(taskName);
        tasks.add(newTask);
        return String.format(String.format(" MORTAL, YOU'VE ADDED THIS TASK:\n %s\n YOU HAVE %d TASKS LEFT.", newTask,
                tasks.size()));
    }

    private String addEvent(TaskList tasks, String input) throws DukeException {
        if (input.equals("") || !input.contains(" /at")) {
            throw new DukeException("FORMAT YOUR EVENT PROPERLY. DON'T WASTE MY TIME.");
        }
        try {
            String[] args = input.split("/at", 2);
            Task newEvent = new Event(args[0].trim(), args[1].trim());
            tasks.add(newEvent);
            return String.format(String.format(" MORTAL, YOU'VE ADDED THIS EVENT:\n %s\n YOU HAVE %d TASKS LEFT.",
                    newEvent, tasks.size()));
        } catch (DateTimeParseException e) {
            throw new DukeException("FORMAT YOUR DATETIME PROPERLY YOU FOOL");
        }
    }

    private String addDeadline(TaskList tasks, String input) throws DukeException {
        if (input.equals("") || !input.contains(" /by")) {
            throw new DukeException("FORMAT YOUR DEADLINE PROPERLY. DON'T WASTE MY TIME.");
        }
        try {
            String[] args = input.split("/by", 2);
            Task newDeadline = new Deadline(args[0].trim(), args[1].trim());
            tasks.add(newDeadline);
            return String.format(String.format(" MORTAL, YOU'VE ADDED THIS DEADLINE:\n %s\n YOU NOW HAVE %d TASKS.",
                    newDeadline, tasks.size()));
        } catch (DateTimeParseException e) {
            throw new DukeException("FORMAT YOUR DATETIME PROPERLY YOU FOOL");
        }
    }
}
