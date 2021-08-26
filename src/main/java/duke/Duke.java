package duke;

import duke.exception.*;
import duke.task.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


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
        running:
        while (true) {
            try {
                String userInput = this.ui.readInput();
                switch (Parser.parseCmd(userInput)) {
                case BYE:
                    this.exit();
                    break running;
                case LIST:
                    this.ui.showList(this.taskList);
                    break;
                case TODO:
                    this.addTodo(Parser.parseArgs(userInput));
                    break;
                case EVENT:
                    this.addEvent(Parser.parseArgs(userInput));
                    break;
                case DEADLINE:
                    this.addDeadline(Parser.parseArgs(userInput));
                    break;
                case DONE:
                    this.markAsDone(Parser.parseArgs(userInput));
                    break;
                case DELETE:
                    this.delete(Parser.parseArgs(userInput));
                    break;
                }
            } catch (DukeException e) {
                this.ui.showDukeException(e);
            }
        }
        this.ui.showFarewell();
    }


    private void exit() {
        try {
            this.ui.closeInput();
            this.storage.saveData(this.taskList);
        } catch (IOException e) {
            this.ui.showSavingError();
        }
    }


    private void addTodo(String args) throws DukeMissingArgumentException {
        try {
            Task todo = new Todo(args);
            this.taskList.add(todo);
            this.ui.showAdd(todo, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void addEvent(String args) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = args.split(" /from ", 2);
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


    private void addDeadline(String args) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = args.split(" /by ", 2);
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


    private void markAsDone(String args)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(args);
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


    private void delete(String args)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(args);
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