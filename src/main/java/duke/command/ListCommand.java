package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListCommand extends Command{
    private LocalDate localDate;
    private boolean isOps;

    public static boolean isListOps(String input) throws DukeException {
        int length = input.length();
        if (length < 6) {
            return false;
        }
        try {
            LocalDate holder = LocalDate.parse(input.substring(5).trim());
            return true;
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }
    
    public ListCommand(String command) throws DukeException {
        if (isListOps(command)) {
            this.localDate = LocalDate.parse(command.substring(5).trim());
            isOps = true;
        } else {
            isOps = false;
        }
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            ui.emptyList();
        } else {
            if (isOps) {
                ui.showScheduleList(taskList, localDate);
            } else {
                ui.showFullList(taskList);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
