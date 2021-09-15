package commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The RescheduleCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class RescheduleCommand extends Command {

    /**
     * Constructs the RescheduleCommand object.
     *
     * @param userInput the entire line of user input
     */
    public RescheduleCommand(ArrayList<String> userInput) {
        super(userInput);
    }

    /**
     * Executes the command.
     *
     * @param list    the TaskList object that stores the list of tasks
     * @param ui      the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (getInput().size() > 4) {
            return "     Please input in the form 'reschedule <index> <date> <time> (if applicable)'\n"
                    + "     Date should be in 'DD/MM/YYYY' form and time should be in HH/MM.";
        }
        if (getInput().size() < 3) {
            return "     Please input index and date at least";
        }
        try {
            int index = Integer.parseInt(getInput().get(1)) - 1;
            LocalDate localDate = getDate(getInput().get(2));
            LocalTime localTime = null;
            if (getInput().size() == 4) {
                localTime = getTime(getInput().get(3));
            }
            if (localDate == null) {
                throw new DukeException("Not a valid date!");
            }
            String prefix = "";
            if (localTime == null) {
                prefix = "     Not a valid time.\n";
            }
            String result = prefix + list.reschedule(localDate, localTime, index);
            storage.resetFile(list.getTasks());
            return result;
        } catch (NumberFormatException e) {
            return "     Please use a number instead!";
        } catch (DukeException e) {
            return "     " + e.getMessage();
        }
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

    private LocalTime getTime(String input) {
        String time = "^[0-9]{4}$";
        Pattern p = Pattern.compile(time);
        Matcher m = p.matcher(input);
        if (m.find()) {
            String matched = m.group();
            try {
                return LocalTime.of(Integer.parseInt(matched.substring(0, 2)),
                        Integer.parseInt(matched.substring(2)));
            } catch (DateTimeException e) {
                return null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
