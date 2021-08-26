package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public enum Command {BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CLEAR}
    boolean isTerminate = false;

    public Parser() {
    }

    public boolean getIsTerminate() {
        return this.isTerminate;
    }

    public void executeCommand(Ui ui, TaskList tasks, String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        Parser.Command command;
        try {
            command = Parser.Command.valueOf(parsedInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid Command");
        }
        switch (command) {
        case BYE:
            this.terminate(ui);
            break;
        case LIST:
            ui.listTasks(tasks);
            break;
        case DONE:
            if (parsedInput.length <= 1) {
                throw new TaskIndexOutOfBoundException("Missing Task Number!");
            }
            this.done(tasks, ui, Integer.parseInt(parsedInput[1]) - 1);
            break;
        case TODO:
            this.addTodo(tasks, ui, input);
            break;
        case DEADLINE:
            this.addDeadline(tasks, ui, input);
            break;
        case EVENT:
            this.addEvent(tasks, ui, input);
            break;
        case DELETE:
            if (parsedInput.length <= 1) {
                throw new TaskIndexOutOfBoundException("Missing Task Number!");
            }
            this.delete(tasks, ui,Integer.parseInt(parsedInput[1]) - 1);
            break;
        case CLEAR:
            this.clear(tasks, ui);
            break;
        default:
            throw new InvalidCommandException("Invalid Command");
        }
        tasks.saveList();
    }

    public void terminate(Ui ui) {
        this.isTerminate = true;
        ui.terminateMessage();
    }

    public void clear(TaskList tasks, Ui ui) {
        tasks.clear();
        ui.clearMessage();
    }

    public void addTodo(TaskList tasks, Ui ui, String input) throws ToDoDescriptionNotFoundException {
        if (input.length() <= 5 || input.substring(5).stripLeading().length() <= 0) {
            throw new ToDoDescriptionNotFoundException("Missing Description!");
        }
        String toDo = input.substring(5);
        ToDo newToDo = new ToDo(toDo);
        tasks.addTask(newToDo);
        ui.addMessage(newToDo, tasks);
    }

    public void addDeadline(TaskList tasks, Ui ui, String input) throws DeadlineDescriptionNotFoundException ,DeadlineNotFoundException{
        if (input.length() <= 9 || input.substring(9).stripLeading().length() <= 0) {
            throw new DeadlineDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(" /by ")) {
            throw new DeadlineNotFoundException("Deadline Missing!");
        }
        int delimiter = input.indexOf(" /by ");
        String task = input.substring(9, delimiter);
        String date = input.substring(delimiter + 5);
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Deadline newDeadline = new Deadline(task, parsedDate);
        tasks.addTask(newDeadline);
        ui.addMessage(newDeadline, tasks);
    }

    public void addEvent(TaskList tasks, Ui ui, String input) throws EventDescriptionNotFoundException , EventTimeNotFoundException {
        if (input.length() <= 6 || input.substring(6).stripLeading().length() <= 0) {
            throw new EventDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(" /at ")) {
            throw new EventTimeNotFoundException("Event Time Missing!");
        }
        int delimiter = input.indexOf(" /at ");
        String task = input.substring(6, delimiter);
        String time = input.substring(delimiter + 5);
        LocalDateTime parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        Event newEvent = new Event(task, parsedTime);
        tasks.addTask(newEvent);
        ui.addMessage(newEvent, tasks);
    }

    public void done(TaskList tasks, Ui ui, int index) throws TaskIndexOutOfBoundException{
        if (index >= tasks.getListSize()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = tasks.setComplete(index);
        ui.doneMessage(task);
    }

    public void delete(TaskList tasks, Ui ui, int index) throws TaskIndexOutOfBoundException{
        if (index >= tasks.getListSize()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = tasks.deleteTask(index);
        ui.deleteMessage(task, tasks);
    }
}
