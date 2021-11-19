package duke.command;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.ToDo;

/**
 * A class that implements Command. This class is responsible for all commands that
 * result in the addition of a task into the task list.
 */
public class AddCommand implements Command {
    private String taskString;
    private String taskType;

    /**
     * Class constructor taking in the taskString and the taskType.
     *
     * @param taskString The main string for the task.
     * @param taskType The type of the task in String format.
     */
    public AddCommand(String taskString, String taskType) {
        this.taskString = taskString;
        this.taskType = taskType.toUpperCase();
    };

    /**
     * Method to obtain if the program should continue running.
     *
     * @return Always returns true.
     */
    @Override
    public boolean isRunning() {
        return true;
    }

    /**
     * Method to execute the given command.
     *
     * @param t The TaskList loaded.
     * @param ui The Ui object running the program.
     * @param storage The Storage object handling the loading and saving.
     * @throws DukeException Thrown in the event of input format errors.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try {
            switch(taskType) {
            case "TODO":
                t.add(new ToDo(taskString));
                break;
            case "DEADLINE":
                String[] deadlineArr = taskString.split(" /by ", 2);
                if (deadlineArr.length == 1) {
                    throw new DukeException("deadline format");
                } else {
                    try {
                        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
                        Date date = inputFormat.parse(deadlineArr[1]);
                        String outputDate = outputFormat.format(date);
                        t.add(new Deadline(deadlineArr[0], outputDate));
                    } catch (ParseException e) {
                        throw new DukeException("date parse");
                    }

                }
                break;
            case "EVENT":
                String[] eventArr = taskString.split(" /at ", 2);
                if (eventArr.length == 1) {
                    throw new DukeException("event format");
                } else {
                    t.add(new Event(eventArr[0], eventArr[1]));
                }
                break;
            case "DOAFTER":
                String[] doAfterArr = taskString.split("/after", 2);
                if (doAfterArr.length == 1) {
                    throw new DukeException("do after format");
                } else {
                    t.add(new DoAfter(doAfterArr[0], doAfterArr[1]));
                }
                break;
            default:
                System.out.println("should never reach here");
            }
            return ui.textFrame("Got it I've added this task:\n" + t.get(t.getSize() - 1));
        } catch (DukeException e) {
            return ui.errorFrame(e.getMessage());
        }
    }
}
