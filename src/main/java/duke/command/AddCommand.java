package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.MissingKeywordException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskTypes;
import duke.task.ToDo;


/**
 * The AddCommand encapsulates a Command that adds Tasks to a TaskList.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private static final String BAD_ADD_ERROR_MSG = "something went wrong with adding a task!";
    private TaskTypes type;
    private String args;

    /**
     * Constructor of an AddCommand.
     *
     * @param type the type of the Task to add.
     * @param args the arguments to create the Task.
     */
    public AddCommand(TaskTypes type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Will add the Task corresponding to this AddCommand to the given TaskList.
     * Will also store the updated TaskList to taskList.txt.
     *
     * @param tasks the given TaskList.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when a Task cannot be created.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task newTask;

        switch (type) {
        case TODO:
            newTask = createToDo(args);
            break;
        case DEADLINE:
            newTask = createDeadline(args);
            break;
        case EVENT:
            newTask = createEvent(args);
            break;
        default:
            throw new DukeException(BAD_ADD_ERROR_MSG);
        }

        assert newTask != null;

        tasks.add(newTask);
        storage.save(tasks);
        return generateMsg(newTask, tasks);
    }

    private ToDo createToDo(String args) {
        return new ToDo(args);
    }

    private Deadline createDeadline(String args) throws MissingKeywordException {
        checkKeywordExists(args, Deadline.KEYWORD_WITH_SPACE);

        String[] argArr = splitStringByKeyword(args, Deadline.KEYWORD_WITH_SPACE);
        String name = argArr[0];
        String dline = argArr[1];

        return new Deadline(name, dline);
    }

    private Event createEvent(String args) throws MissingKeywordException {
        checkKeywordExists(args, Event.KEYWORD_WITH_SPACE);

        String[] argArr = splitStringByKeyword(args, Event.KEYWORD_WITH_SPACE);
        String name = argArr[0];
        String time = argArr[1];

        return new Event(name, time);
    }

    private String generateMsg(Task tsk, TaskList tasks) {
        String msg = "Got it. I've added this task:\n  " + tsk + "\nNow you have " + tasks.numTasks();
        msg = tasks.numTasks() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
        return msg;
    }

    private void checkKeywordExists(String input, String keyword) throws MissingKeywordException {
        if (!input.contains(keyword)) {
            throw new MissingKeywordException(keyword);
        }
    }

    private String[] splitStringByKeyword(String input, String keyword) {
        return input.split(keyword, 2);
    }
}
