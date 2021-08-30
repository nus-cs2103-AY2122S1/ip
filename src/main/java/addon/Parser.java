package addon;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import addon.Ui.IncorrectFormatException;

/**
 * Class that handles parsing of users' inputs.
 */
public class Parser {

    private static final LocalDateTime NULLDATE = LocalDateTime.of(1, 1, 1, 1, 0);
    private final Tasklist tasklist;

    public Parser(Tasklist tasklist) {
        this.tasklist = tasklist;
    }
    /**
     * Handles user's commands, calls appropriate methods in Tasklist class.
     *
     * @param input Input string of user.
     * @exception IncorrectFormatException Error if command input is formatted wrongly.
     */
    public void updateList (String input) throws IncorrectFormatException {
        if (input.equalsIgnoreCase("/list")) {
            tasklist.listEntries();
        } else {
            if (input.indexOf(' ') > 0) { // checking if command given was >= two words
                String[] splitted = input.split(" ", 2);
                input = splitted[1];
                if (splitted[0].equalsIgnoreCase("/done")
                        || splitted[0].equalsIgnoreCase("/delete")) {
                    if (!input.matches("^[0-9]*$")) {
                        throw new IncorrectFormatException("Number not detected.");
                    } else {
                        int num = Integer.parseInt(input);
                        if (splitted[0].equalsIgnoreCase("/done")) { // done-ing item
                            tasklist.changeDone(num);
                        } else if (splitted[0].equalsIgnoreCase("/delete")) { // removing item
                            tasklist.removeEntry(num);
                        }
                    }
                } else if (splitted[0].equalsIgnoreCase("/check")) { // checking date
                    LocalDateTime date;
                    String original = splitted[1];
                    try {
                        date = LocalDate.parse(original).atTime(0, 0);
                        tasklist.filterDates(date);
                    } catch (DateTimeException e) {
                        throw new IncorrectFormatException("Date format provided incorrectly.\n    "
                                + "Date format: yyyy-mm-dd");
                    }
                } else if (splitted[0].equalsIgnoreCase("/find")) { // finding keyword
                    String keyword = splitted[1];
                    tasklist.filterNames(keyword);
                } else if (splitted[0].equalsIgnoreCase("todo")) { // adding todoitem
                    if (input.isEmpty()) {
                        throw new IncorrectFormatException("Task name not provided.\n"
                                + "    FORMAT: \" TODO TASKNAME\"");
                    } else {
                        tasklist.addEntry(new String[]{"T", input}, NULLDATE);
                    }
                } else {
                    String[] splited;
                    boolean isDeadline = false;
                    if (splitted[0].equalsIgnoreCase("deadline")) { // adding deadline
                        splited = input.split("(?i) /by ");
                        isDeadline = true;
                    } else if (splitted[0].equalsIgnoreCase("event")) { // adding event
                        splited = input.split("(?i) /at ");
                    } else {
                        throw new IncorrectFormatException("Task type or task name not provided!");
                    }
                    if (splited.length == 1) {
                        throw new IncorrectFormatException("Task name or date not provided.");
                    } else {
                        String[] timesplit = {"0000-00-00", "0000"};
                        if (splited[1].indexOf(' ') > 0) {
                            timesplit = splited[1].split(" ", 2);
                        } else {
                            timesplit[0] = splited[1];
                            timesplit[1] = "0000";
                        }
                        LocalDateTime date;
                        try {
                            int hour = Integer.parseInt(timesplit[1].substring(0, 1));
                            int minute = Integer.parseInt(timesplit[1].substring(2, 3));
                            date = LocalDate.parse(timesplit[0]).atTime(hour, minute);
                        } catch (DateTimeException e) {
                            throw new IncorrectFormatException("Date format provided incorrectly."
                                    + "    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).");
                        }
                        tasklist.addEntry(new String[]{isDeadline ? "D" : "E", splited[0]}, date);
                    }
                }
            } else { // only one word
                if (input.equalsIgnoreCase("help")) {
                    System.out.println(Ui.BAR + "\n    ToDos: tasks without any date/time attached to it "
                            + "e.g., visit new theme park\n" + "      FORMAT: todo TASKNAME\n"
                            + "    Deadlines: tasks that need to be done before a specific date/time "
                            + "e.g., submit report by 11/10/2019 5pm\n"
                            + "      FORMAT: deadline TASKNAME /by DEADLINE\n"
                            + "    Events: tasks that start at a specific time and ends at a specific time "
                            + "e.g., team project meeting on 2/10/2019 2-4pm\n"
                            + "      FORMAT: todo TASKNAME /by DATE\n"
                            + "    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).\n"
                            + "    \"/done x\" where x is the task number to mark task as done.\n"
                            + "    \"/check DATE\" where DATE is the date you want to query.\n"
                            + "    \"/clearlist\" clears.\n" + "    \"/list x\" lists.\n"
                            + "    \"/delete x\" where x is the task number to be deleted. \n" + Ui.BAR);
                } else if (input.equals("/clearlist")) {
                    tasklist.clearList();
                } else {
                    throw new IncorrectFormatException("Command incomplete.");
                }
            }
        }
    }
}
