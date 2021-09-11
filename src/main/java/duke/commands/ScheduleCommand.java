package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.util.ArrayList;
import java.util.Optional;

import duke.tasks.Task;
import duke.date.Date;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongScheduleFormatException;

public class ScheduleCommand extends Command {

    public ScheduleCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(
            TaskList tasks,
            Ui ui,
            Storage storage) throws WrongScheduleFormatException,
            WrongDateFormatException {
        String[] instructions = this.
                commandDescription.
                split(" ");
        if (instructions.length != 2) {
            throw new WrongScheduleFormatException();
        }

        Date userInputDate = new Date(instructions);

        StringBuilder sb = new StringBuilder("");
        ArrayList<Task> allTasksItems = tasks.getTaskList();
        int counter = 1;
        for (int i = 1; i <= tasks.getTaskListLength(); i++) {
            Task task = allTasksItems.get(i - 1);
            Optional<Date> date = task.getOptionalDate();
           if (date.isPresent()
                   && userInputDate.isEquivalentDate(date.get())) {
               if (counter == 1) {
                   sb.append(counter + ". " + task.toString());
                } else {
                    sb.append("\n" + counter + ". " + task.toString());
                }
                counter++;
           }
        }
        return ui.displayTaskMatchingDate(sb.toString());
    }
}
