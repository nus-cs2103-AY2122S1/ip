package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand implements Command {
    private String taskString;
    private String taskType;

    public AddCommand(String taskString, String taskType) {
        this.taskString = taskString;
        this.taskType = taskType.toUpperCase();
    };

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try{
            switch(taskType) {
            case "TODO":
                t.add(new ToDo(taskString));
                break;
            case "DEADLINE":
                String[] deadlineArr = taskString.split(" /by ", 2);
                if(deadlineArr.length == 1) {
                    throw new DukeException("deadline format");
                } else {
                    try{
                        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
                        Date date = inputFormat.parse(deadlineArr[1]);
                        String  outputDate = outputFormat.format(date);
                        t.add(new Deadline(deadlineArr[0], outputDate));
                    } catch (ParseException e) {
                        throw new DukeException("date parse");
                    }

                }
                break;
            case "EVENT":
                String[] eventArr = taskString.split(" /at ", 2);
                if(eventArr.length == 1) {
                    throw new DukeException("event format");
                } else {
                    t.add(new Event(eventArr[0], eventArr[1]));
                }
                break;
            default:
                System.out.println("should never reach here");
            }
            ui.textFrame("Got it I've added this task:\n" + t.get(t.getSize() - 1));
        } catch (DukeException e){
            ui.errorFrame(e.getMessage());
        }
    }
}
