package duke;

import duke.task.*;

/**
 * Parses input commands.
 * @author Thomas Hogben
 */
public class Parser {
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_DELETE = "delete ";
    private static final String COMMAND_DONE = "done ";
    private static final String COMMAND_EDIT = "edit ";
    private static final String COMMAND_EVENT = "event ";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find ";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo ";

    private static final String TAG_DESC = "/desc ";
    private static final String TAG_DATE = "/date ";
    private static final String TAG_TIME = "/time ";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Currently only scans System.in.
     *
     * @param taskList The TaskList the Parser should operate on.
     * @param storage A Storage to use for saving tasks.
     * @param ui A Ui to send display commands to.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses input continously and executes the commands provided.
     * Commands recognised: "bye" - exits parsing loop
     *                      "deadline" - creates new Deadline task
     *                      "delete" - deletes specified task
     *                      "done" - completes specified task
     *                      "event" - creates new Event task
     *                      "list" - lists all tasks in task list
     *                      "todo" - creates new ToDo task
     */
    public String parse(String input) {
        String result = "";
        try {
            if (input.equals(COMMAND_EXIT)) {
                //TO-DO
            } else if (input.equals(COMMAND_LIST)) {
                result = taskList.listTasks();
            } else if (input.startsWith(COMMAND_DONE)) {
                String parsedCommand = input.substring(COMMAND_DONE.length());
                result = taskList.completeTask(parsedCommand);
            } else if (input.startsWith(COMMAND_TODO)) {
                String parsedCommand = input.substring(COMMAND_TODO.length());
                result = taskList.addTask(new ToDo(parsedCommand));
            } else if (input.startsWith(COMMAND_DEADLINE)) {
                String parsedCommand = input.substring(COMMAND_DEADLINE.length());
                result = taskList.addTask(new Deadline(parsedCommand));
            } else if (input.startsWith(COMMAND_EVENT)) {
                String parsedCommand = input.substring(COMMAND_EVENT.length());
                result = taskList.addTask(new Event(parsedCommand));
            } else if (input.startsWith(COMMAND_EDIT)) {
                String parsedCommand = input.substring(COMMAND_EDIT.length());
                result = parseEditCommand(parsedCommand);
            } else if (input.startsWith(COMMAND_DELETE)) {
                String parsedCommand = input.substring(COMMAND_DELETE.length());
                result = taskList.deleteTask(parsedCommand);
            } else if (input.startsWith(COMMAND_FIND)) {
                String parsedCommand = input.substring(COMMAND_FIND.length());
                result = taskList.find(parsedCommand);
            } else {
                throw DukeException.DEFAULT;
            }
            storage.save(taskList);
        } catch (DukeException e) {
            result = ui.formatError(e);
        }

        return result;
    }

    private String parseEditCommand(String input) throws DukeException {
        int indexOfNextSpace = input.indexOf(' ');
        if (indexOfNextSpace == -1) {
            throw DukeException.DEFAULT;
        }
        int taskNumber = taskList.parseTaskNumber(input.substring(0, indexOfNextSpace));

        int indexOfNextKeyword = indexOfNextSpace + 1;
        //The remaining unparsed section of the input.
        String unparsedStr = input.substring(indexOfNextKeyword);
        Task task = taskList.getTask(taskNumber);

        //Parse the tag and edit the task accordingly.
        if (unparsedStr.startsWith(TAG_DESC)) {
            String description = unparsedStr.substring(TAG_DESC.length());
            task.setDescription(description);
        } else if (unparsedStr.startsWith(TAG_DATE)) {
            String date = unparsedStr.substring(TAG_DATE.length());
            if (task instanceof DateAndTimeTask) {
                DateAndTimeTask castedTask = (DateAndTimeTask) task;
                castedTask.setDate(date);
            } else {
                throw DukeException.INVALID_TASK_TYPE;
            }
        } else if (unparsedStr.startsWith(TAG_TIME)) {
            String time = unparsedStr.substring(TAG_DATE.length());
            if (task instanceof DateAndTimeTask) {
                DateAndTimeTask castedTask = (DateAndTimeTask) task;
                castedTask.setTime(time);
            } else {
                throw DukeException.INVALID_TASK_TYPE;
            }
        } else {
            throw DukeException.DEFAULT;
        }

        return ui.editTask(task);
    }
}