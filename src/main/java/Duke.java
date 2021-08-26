import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private enum CommandType {
        BYE, LIST,
        TODO, EVENT, DEADLINE,
        DONE, DELETE,
    }


    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.loadData());
        } catch (DukeException e) {
            this.ui.showDukeException(e);
        }
    }


    private void run() {
        this.ui.showGreet();
        while (true) {
            try {
                String rawInput = this.ui.readInput();
                String[] userInput = rawInput.split(" ", 2);
                CommandType commandType = this.getCommand(userInput[0]);

                switch (commandType) {
                case BYE:
                    this.exit();
                    this.ui.closeInput();
                    return;
                case LIST:
                    this.ui.showList(this.taskList);
                    break;
                case TODO:
                    this.addTodo(userInput);
                    break;
                case EVENT:
                    this.addEvent(userInput);
                    break;
                case DEADLINE:
                    this.addDeadline(userInput);
                    break;
                case DONE:
                    this.markAsDone(userInput);
                    break;
                case DELETE:
                    this.delete(userInput);
                    break;
                }
            } catch (DukeException e) {
                this.ui.showDukeException(e);
            }
        }
    }


    private CommandType getCommand(String command) throws DukeUnknownCommandException {
        try {
            return Duke.CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeUnknownCommandException(command);
        }
    }


    private void exit() {
        try {
            this.storage.saveData(this.taskList);
            this.ui.showExit();
        } catch (IOException e) {
            this.ui.showSavingError();
        }
    }


    private void addTodo(String[] userInput) throws DukeMissingArgumentException {
        try {
            String description = userInput[1];

            Task todo = new Todo(description);
            this.taskList.add(todo);
            this.ui.showAdd(todo, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void addEvent(String[] userInput) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = userInput[1].split(" /from ", 2);
            String[] timestamps = splits[1].split(" /to ", 2);
            String start = timestamps[0];
            String end = timestamps[1];
            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
            if (startTime.isAfter(endTime)) {
                this.ui.showInvalidDateRange();
                return;
            }

            Task event = new Event(splits[0], startTime, endTime);
            this.taskList.add(event);
            this.ui.showAdd(event, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateFormat();
        }
    }


    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = userInput[1].split(" /by ", 2);
            LocalDateTime time = LocalDateTime.parse(splits[1], formatter);

            Task deadline = new Deadline(splits[0], time);
            this.taskList.add(deadline);
            this.ui.showAdd(deadline, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateFormat();
        }
    }


    private void markAsDone(String[] userInput)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (taskNum > this.taskList.getLength()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            this.taskList.get(taskNum).markAsDone();
            this.ui.showDone(this.taskList.get(taskNum));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void delete(String[] userInput)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (taskNum > this.taskList.getLength()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            Task taskToDelete = this.taskList.get(taskNum);
            this.ui.showDelete(taskToDelete, this.taskList.getLength());
            this.taskList.delete(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}