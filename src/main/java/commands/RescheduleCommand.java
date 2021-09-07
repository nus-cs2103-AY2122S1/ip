package commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The RescheduleCommand Class inherits Command and is
 * a specific type of executable command.
 */
public class RescheduleCommand extends Command {

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
        try {
            int index = Integer.parseInt(getInput().get(1)) - 1;
            LocalDate localDate = getDate(getInput().get(2));
            String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String result = list.reschedule(localDate, index, date);
            storage.resetFile(list.getTasks());
            return result;
        } catch (NumberFormatException e) {
            return "     Please use a number instead!";
        } catch (IndexOutOfBoundsException e) {
            return "     Please input index and date!";
        } catch (NullPointerException e) {
            return "     Please input a valid date!";
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
}
