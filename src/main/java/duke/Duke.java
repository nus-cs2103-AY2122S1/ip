package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    enum TaskType {
        TODO,
        EVENT,
        DEADLINE,
    }

    private StorageList SL;
    private static Ui ui;
    private Parser parser;
    private Storage storage;

    private static final int TASK_TODO = 1;
    private static final int TASK_DEADLINE = 2;
    private static final int TASK_EVENT = 3;

    private static final int ERROR_OUTOFBOUNDS = 4;
    private static final int ERROR_UNKNOWN = 5;

    private static final int VALIDLENGTH_DONE = 6;
    private static final int VALIDLENGTH_TODO = 6;
    private static final int VALIDLENGTH_DEADLINE = 10;
    private static final int VALIDLENGTH_EVENT = 7;
    private static final int VALIDLENGTH_DELETE = 8;
    private static final int VALIDLENGTH_FIND = 6;
    private static final int VALIDLENGTH_VIEW = 6;

    public Duke() {}

    /**
     * Constructor to initialise variables.
     *
     * @param filePath File path to receive input from and write to.
     * @throws FileNotFoundException If file is not found.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        SL = new StorageList(storage.load());
    }

    public String getResponse(String input) {
        try {
            return new Duke("data").run(input);
        } catch (IOException e) {
            return ui.ioErrorMsg();
        }
    }

    /**
     * Executes the program after reading input from file or user.
     *
     * @param input The input given to the program.
     * @return The confirmation message depending on what the user inputted.
     */
    public String run(String input) {
        try {
            if (parser.isDoneCmd(input)) {
                return marking(input);

            } else if (parser.isValidTodo(input)) {
                if(input.length() < VALIDLENGTH_TODO) {
                    throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                }
                return putTaskInList(TaskType.TODO, input);

            } else if (parser.isValidDeadline(input)) {
                if (input.length() < VALIDLENGTH_DEADLINE) {
                    throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                }
                return putTaskInList(TaskType.DEADLINE, input);

            } else if (parser.isValidEvent(input)) {
                if (input.length() < VALIDLENGTH_EVENT) {
                    throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                }
                return putTaskInList(TaskType.EVENT, input);

            } else if (parser.isDeleteCmd(input)) {
                if (input.length() < VALIDLENGTH_DELETE) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                int idx = parser.getDeleteIdx(input);
                return deleteFromList(idx);

            } else if (parser.isFindCmd(input)) {
                if (input.length() < VALIDLENGTH_FIND) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                String keyword = input.substring(5);
                return SL.findAndReturn(keyword);

            } else if (parser.isViewCmd(input)) {
                if (input.length() < VALIDLENGTH_VIEW) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                String dateQuery = parser.getDateTime(input);
                return SL.viewSchedule(dateQuery);

            } else {
                switch (input) {
                case "bye":
                    return ui.bye();
                case "list":
                    return ui.displayListContents(SL);
                default:
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
            }

        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return ui.ioErrorMsg();
        } catch (DateTimeParseException e) {
            return ui.parsingFormatErrorMsg();
        }
    }

    private String putTaskInList(TaskType taskType, String input)
            throws IOException, DukeException, DateTimeParseException {
        Task task = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        switch (taskType) {
        case TODO:
            task = new ToDo(parser.getTodoDescription(input));
            break;
        case EVENT:
            if (!input.contains("/at ")) {
                throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
            }
            String at = parser.getTaskTime(input);
            LocalDateTime eventTime = parser.parseLocalDateTime(at);
            task = new Event(parser.getEventDescription(input), eventTime.format(dateTimeFormatter));
            break;
        case DEADLINE:
            if (!input.contains("/by ")) {
                throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
            }
            String by = parser.getTaskTime(input);
            LocalDateTime deadlineTime = parser.parseLocalDateTime(by);
            task = new Deadline(parser.getDeadlineDescription(input), deadlineTime.format(dateTimeFormatter));
            break;
        default:
            // code should not come into here
            break;
        }
        SL.addTask(task);
        storage.save(SL);
        return ui.taskAddedMsg(task.toString(), SL.size());
    }

    private String deleteFromList(int idx) throws IOException, DukeException {
        if (!parser.hasValidIdx(idx, SL.size())) {
            throw new DukeException(ui.taskErrorMsg(ERROR_OUTOFBOUNDS));
        } else {
            String desc = SL.get(idx).getDescription();
            SL.delete(idx);
            storage.save(SL);
            return ui.taskDeleteMsg(desc, SL.size());
        }

    }

    private String marking(String input) throws DukeException, IOException {
        if (input.length() >= VALIDLENGTH_DONE && input.substring(5).matches("[0-9]+")) {
            int taskNum = parser.getDoneIdx(input);
            if (parser.hasValidIdx(taskNum, SL.size())) {
                SL.get(taskNum).markAsDone();
                storage.save(SL);
                return ui.taskDoneConfirmation(SL.get(taskNum).getDescription());
            } else {
                throw new DukeException(ui.taskErrorMsg(ERROR_OUTOFBOUNDS));
            }
        }
        throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
    }
}
