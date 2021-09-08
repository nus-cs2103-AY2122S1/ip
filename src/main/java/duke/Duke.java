package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeDatabaseException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidDateRangeException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeNoTaskFoundException;
import duke.exception.DukeUnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the Duke chatbot object.
 */
public class Duke {
    private static String FILEPATH = "./data/duke.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;


    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage(Duke.FILEPATH);
        this.ui = new Ui();
        this.isRunning = true;
        try {
            this.taskList = new TaskList(this.storage.loadData());
        } catch (DukeException e) {
            this.ui.showDukeException(e);
        }
    }


    private void run() {
        System.out.println(this.ui.showGreet());
        while (this.isRunning) {
            String input = this.ui.readInput();
            System.out.println(this.getResponse(input));
        }
    }



    private void exit() throws DukeDatabaseException {
        assert this.isRunning;
        try {
            this.ui.closeInput();
            this.storage.saveData(this.taskList);
            this.isRunning = false;
        } catch (IOException e) {
            throw new DukeDatabaseException();
        }
    }


    protected String getResponse(String input) {
        try {
            switch (Parser.parseCmd(input)) {
            case BYE:
                this.exit();
                return this.ui.showFarewell();
            case LIST:
                return this.ui.showList(this.taskList);
            case TODO:
                return this.addTodo(Parser.parseArgs(input));
            case EVENT:
                return this.addEvent(Parser.parseArgs(input));
            case DEADLINE:
                return this.addDeadline(Parser.parseArgs(input));
            case DONE:
                return this.markAsDone(Parser.parseArgs(input));
            case DELETE:
                return this.delete(Parser.parseArgs(input));
            case FIND:
                return this.ui.showSearchResult(this.taskList, Parser.parseArgs(input));
            default:
                throw new DukeUnknownCommandException(input);
            }
        } catch (DukeException e) {
            return this.ui.showDukeException(e);
        }
    }


    private String addTodo(String args) throws DukeMissingArgumentException {
        try {
            Task todo = new Todo(args);
            this.taskList.add(todo);
            return this.ui.showAdd(todo, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private String addEvent(String args)
            throws DukeMissingArgumentException, DukeInvalidDateException, DukeInvalidDateRangeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = args.split(" /from ", 2);
            String[] timestamps = splits[1].split(" /to ", 2);
            String start = timestamps[0];
            String end = timestamps[1];
            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
            if (startTime.isAfter(endTime)) {
                throw new DukeInvalidDateRangeException();
            }
            Task event = new Event(splits[0], startTime, endTime);
            this.taskList.add(event);
            return this.ui.showAdd(event, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }


    private String addDeadline(String args) throws DukeMissingArgumentException, DukeInvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = args.split(" /by ", 2);
            LocalDateTime time = LocalDateTime.parse(splits[1], formatter);

            Task deadline = new Deadline(splits[0], time);
            this.taskList.add(deadline);
            return this.ui.showAdd(deadline, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }


    private String markAsDone(String args)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(args);
            if (taskNum > this.taskList.getLength()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            this.taskList.get(taskNum).markAsDone();
            return this.ui.showDone(this.taskList.get(taskNum));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private String delete(String args)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(args);
            if (taskNum > this.taskList.getLength() || taskNum < 0) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            Task taskToDelete = this.taskList.get(taskNum);
            String msg = this.ui.showDelete(taskToDelete, this.taskList.getLength());
            this.taskList.delete(taskNum);
            return msg;
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
