package commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DueCommand Class inherits Command and is
 * a specific type of executable command.
 */

public final class DueCommand extends Command {

    /**
     * Constructs the DueCommand object.
     *
     * @param userInput the entire line of user input
     */
    public DueCommand(ArrayList<String> userInput) {
        super(userInput);
    }

    /**
     * Executes the command.
     *
     * @param list the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        assert list != null : "invalid TaskList object detected";
        assert ui != null : "invalid Ui object detected";
        assert storage != null : "invalid Storage object detected";
        if (getInput().size() != 2) {
            return "     Please input in the form 'due <date>'.\n"
                    + "     Date should be in 'DD/MM/YYYY' form.";
        }
        LocalDate dueDate = getDate(getInput().get(1));
        if (dueDate == null) {
            return "     Please input a valid date in 'DD/MM/YYYY' form.";
        }
        ArrayList<Task> tasksDue = list.findTasksDue(dueDate);
        if (tasksDue.isEmpty()) {
            return "     No task due!";
        }
        String result = "     The task(s) due are: \n";
        for (int i = 0; i < tasksDue.size(); i++) {
            if (i + 1 < tasksDue.size()) {
                result += "     " + (i + 1) + "." + tasksDue.get(i).getType()
                        + tasksDue.get(i).getStatus() + " " + tasksDue.get(i).getDescription() + "\n";
            } else {
                result += "     " + (i + 1) + "." + tasksDue.get(i).getType()
                        + tasksDue.get(i).getStatus() + " " + tasksDue.get(i).getDescription();
            }
        }
        return result;
    }

    private LocalDate getDate(String userInput) {
        String str = userInput.replaceAll(" ", "");
        String temp = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}$";
        Pattern p = Pattern.compile(temp);
        Matcher m = p.matcher(str);
        String dateStr;
        if (m.find()) {
            dateStr = m.group();
            try {
                String[] date = dateStr.split("/");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                return null;
            }
        }
        return null;
    }
}
