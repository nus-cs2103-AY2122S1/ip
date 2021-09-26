package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser that parses user input and commands.
 */
public class Parser {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HHmm";
    private static final int TODO_INPUT_START = 5;
    private static final int DEADLINE_INPUT_START = 9;
    private static final int EVENT_INPUT_START = 6;
    private static final int DATE_TIME_FLAG_LENGTH = 5;
    private static final int PRIORITY_FLAG_LENGTH = 4;
    private static final String PRIORITY_FLAG = " /p ";
    private static final String DATE_FLAG = " /by ";
    private static final String TIME_FLAG = " /at ";

    public enum Command { BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CLEAR, FIND, PRIORITY }
    private boolean isTerminated = false;

    /**
     * Class constructor.
     */
    public Parser() {
    }

    public boolean canTerminate() {
        return this.isTerminated;
    }

    /**
     * Parses and executes the command that the user input.
     *
     * @param ui UI object to render output to user.
     * @param tasks TaskList object that contains the tasks.
     * @param input Input that user entered.
     * @return Message to be shown to the user.
     * @throws DukeException If an error occurs.
     */
    public String executeCommand(Ui ui, TaskList tasks, String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        Parser.Command command;
        String message;
        try {
            command = Parser.Command.valueOf(parsedInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid Command");
        }
        switch (command) {
        case BYE:
            message = this.terminate(ui);
            break;
        case LIST:
            message = ui.listTasks(tasks);
            break;
        case DONE:
            if (parsedInput.length <= 1) {
                throw new TaskIndexOutOfBoundException("Missing Task Number!");
            }
            message = this.markDone(tasks, ui, Integer.parseInt(parsedInput[1]) - 1);
            break;
        case TODO:
            message = this.addTodo(tasks, ui, input);
            break;
        case DEADLINE:
            message = this.addDeadline(tasks, ui, input);
            break;
        case EVENT:
            message = this.addEvent(tasks, ui, input);
            break;
        case DELETE:
            if (parsedInput.length <= 1) {
                throw new TaskIndexOutOfBoundException("Missing Task Number!");
            }
            message = this.delete(tasks, ui, Integer.parseInt(parsedInput[1]) - 1);
            break;
        case CLEAR:
            message = this.clear(tasks, ui);
            break;
        case FIND:
            message = this.findTasks(tasks, ui, parsedInput[1]);
            break;
        case PRIORITY:
            if (parsedInput.length <= 1) {
                throw new TaskIndexOutOfBoundException("Missing Task Number!");
            }
            message = this.setPriority(tasks, ui, Integer.parseInt(parsedInput[1]) - 1,
                    Integer.parseInt(parsedInput[2]));
            break;
        default:
            throw new InvalidCommandException("Invalid Command");
        }
        tasks.saveList();
        return message;
    }

    /**
     * Terminates the Duke Personal Assistant Chat-bot.
     *
     * @param ui UI object to render output to user.
     * @return Message to be shown to the user.
     */
    public String terminate(Ui ui) {
        this.isTerminated = true;
        return ui.terminateMessage();
    }

    /**
     * Clears all tasks in the TaskList.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @return Message to be shown to the user.
     */
    public String clear(TaskList tasks, Ui ui) {
        tasks.clear();
        assert tasks.getListSize() == 0 : "TaskList should be empty after clearing";
        return ui.clearMessage();
    }

    /**
     * Adds a ToDo task to the TaskList.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param input Input that user entered.
     * @return Message to be shown to the user.
     * @throws ToDoDescriptionNotFoundException If user did not enter a task description.
     */
    public String addTodo(TaskList tasks, Ui ui, String input) throws ToDoDescriptionNotFoundException,
            InvalidPriorityException {
        if (input.length() <= TODO_INPUT_START || input.substring(5).stripLeading().length() <= 0) {
            throw new ToDoDescriptionNotFoundException("Missing Description!");
        }
        String toDo = parseTask(input, TODO_INPUT_START, input.indexOf(PRIORITY_FLAG));
        int priority = parsePriority(input);
        ToDo newToDo = new ToDo(toDo, priority);
        tasks.addTask(newToDo);
        assert tasks.getList().contains(newToDo) : "New Todo should be added";
        return ui.addMessage(newToDo, tasks);
    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param input Input that user entered.
     * @return Message to be shown to the user.
     * @throws DeadlineDescriptionNotFoundException If user did not enter a task description.
     * @throws DeadlineNotFoundException If user did not enter a deadline.
     */
    public String addDeadline(TaskList tasks, Ui ui, String input) throws DeadlineDescriptionNotFoundException,
            DeadlineNotFoundException, InvalidPriorityException {
        if (input.length() <= DEADLINE_INPUT_START || input.substring(DEADLINE_INPUT_START)
                .stripLeading().length() <= 0) {
            throw new DeadlineDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(DATE_FLAG)) {
            throw new DeadlineNotFoundException("Deadline Missing!");
        }
        int delimiterDate = input.indexOf(DATE_FLAG);
        String task = parseTask(input, DEADLINE_INPUT_START, delimiterDate);
        String date = parseDateTime(input, delimiterDate);
        int priority = parsePriority(input);
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        Deadline newDeadline = new Deadline(task, parsedDate, priority);
        tasks.addTask(newDeadline);
        assert tasks.getList().contains(newDeadline) : "New Deadline should be added";
        return ui.addMessage(newDeadline, tasks);
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param input Input that user entered.
     * @return Message to be shown to the user.
     * @throws EventDescriptionNotFoundException If user did not enter a task description.
     * @throws EventTimeNotFoundException If user did not enter date and time of event.
     */
    public String addEvent(TaskList tasks, Ui ui, String input) throws EventDescriptionNotFoundException,
            EventTimeNotFoundException, InvalidPriorityException {
        if (input.length() <= EVENT_INPUT_START || input.substring(EVENT_INPUT_START).stripLeading().length() <= 0) {
            throw new EventDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(TIME_FLAG)) {
            throw new EventTimeNotFoundException("Event Time Missing!");
        }
        int delimiterTime = input.indexOf(TIME_FLAG);
        String task = parseTask(input, EVENT_INPUT_START, delimiterTime);
        String time = parseDateTime(input, delimiterTime);
        int priority = parsePriority(input);
        LocalDateTime parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        Event newEvent = new Event(task, parsedTime, priority);
        tasks.addTask(newEvent);
        assert tasks.getList().contains(newEvent) : "New Event should be added";
        return ui.addMessage(newEvent, tasks);
    }

    /**
     * Gets the task from the input
     * @param input Input that user entered.
     * @param start Start index of task.
     * @param end End index of task.
     * @return Task.
     */
    public String parseTask(String input, int start, int end) {
        if (end == -1) {
            return input.substring(start);
        } else {
            return input.substring(start, end);
        }
    }

    /**
     * Gets the task priority from the input.
     * @param input Input that user entered.
     * @return Task priority.
     */
    public int parsePriority(String input) throws InvalidPriorityException {
        int priority;
        if (!input.contains(PRIORITY_FLAG)) {
            priority = 3;
        } else {
            int delimiterPriority = input.indexOf(PRIORITY_FLAG);
            priority = Integer.parseInt(input.substring(delimiterPriority + PRIORITY_FLAG_LENGTH));
            if (priority > 3 || priority < 1) {
                throw new InvalidPriorityException("Priority level is invalid!");
            }
        }
        return priority;
    }

    /**
     * Gets String version of date or time from the input
     * @param input Input that the user entered.
     * @param delimiter Index where the date or time starts in input.
     * @return String version of date or time.
     */
    public String parseDateTime(String input, int delimiter) {
        String dateTime;
        if (input.contains(PRIORITY_FLAG)) {
            int delimiterPriority = input.indexOf(PRIORITY_FLAG);
            dateTime = input.substring(delimiter + DATE_TIME_FLAG_LENGTH, delimiterPriority);
        } else {
            dateTime = input.substring(delimiter + DATE_TIME_FLAG_LENGTH);
        }
        return dateTime;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param index Index of the task to be marked as done.
     * @return Message to be shown to the user.
     * @throws TaskIndexOutOfBoundException If no task bears the index.
     */
    public String markDone(TaskList tasks, Ui ui, int index) throws TaskIndexOutOfBoundException {
        if (index >= tasks.getListSize()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = tasks.setComplete(index);
        assert task.getIsCompleted() : "Task should be marked as completed";
        return ui.doneMessage(task);
    }

    /**
     * Deletes a task.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param index Index of the task to be deleted.
     * @return Message to be shown to the user.
     * @throws TaskIndexOutOfBoundException If no task bears the index.
     */
    public String delete(TaskList tasks, Ui ui, int index) throws TaskIndexOutOfBoundException {
        if (index >= tasks.getListSize()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = tasks.deleteTask(index);
        assert !tasks.getList().contains(task) : "Task should be deleted";
        return ui.deleteMessage(task, tasks);
    }

    /**
     * Finds tasks that contains keyword.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param keyword Keyword to be searched.
     * @return Message to be shown to the user.
     */
    public String findTasks(TaskList tasks, Ui ui, String keyword) {
        return ui.findMessage(tasks.findTasks(keyword));
    }

    /**
     * Updates the priority level of a task.
     *
     * @param tasks TaskList object that contains the tasks.
     * @param ui UI object to render output to user.
     * @param index Index of the task to be deleted.
     * @param priority Priority level to be updated.
     * @return Message to be shown to the user.
     * @throws TaskIndexOutOfBoundException If no task bears the index.
     */
    public String setPriority(TaskList tasks, Ui ui, int index, int priority) throws TaskIndexOutOfBoundException {
        if (index >= tasks.getListSize()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = tasks.setPriority(index, priority);
        return ui.setPriorityMessage(task);
    }
}
