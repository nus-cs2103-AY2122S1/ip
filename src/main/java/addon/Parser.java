package addon;

import addon.Ui.IncorrectFormatException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Parser {

    private Tasklist tasklist;
    private static LocalDateTime NULLDATE = LocalDateTime.of(1, 1, 1, 1, 0);

    public Parser(Tasklist tasklist) {
        this.tasklist = tasklist;
    }

    public void updateList(String input) throws IncorrectFormatException {
        if (input.equalsIgnoreCase("/list")) {
            this.tasklist.listEntries();
        } else if (input.indexOf(" ") > 0) {
            String[] splitted = input.split(" ", 2);
            input = splitted[1];
            if (!splitted[0].equalsIgnoreCase("/done") && !splitted[0].equalsIgnoreCase("/delete")) {
                if (splitted[0].equalsIgnoreCase("/check")) {
                    String original = splitted[1];
                    try {
                        LocalDateTime date = LocalDate.parse(original).atTime(0, 0);
                        this.tasklist.filterDates(date);
                    } catch (DateTimeException e) {
                        throw new IncorrectFormatException("Date format provided incorrectly.\n    Date format: yyyy-mm-dd");
                    }
                } else if (splitted[0].equalsIgnoreCase("todo")) {
                    if (input.isEmpty()) {
                        throw new IncorrectFormatException("Task name not provided.\n    FORMAT: \" TODO TASKNAME\"");
                    }
                    this.tasklist.addEntry(new String[]{"T", input}, NULLDATE);
                } else {
                    boolean isDeadline = false;
                    String[] splited;
                    if (splitted[0].equalsIgnoreCase("deadline")) {
                        splited = input.split("(?i) /by ");
                        isDeadline = true;
                    } else {
                        if (!splitted[0].equalsIgnoreCase("event")) {
                            throw new IncorrectFormatException("Task type or task name not provided!");
                        }
                        splited = input.split("(?i) /at ");
                    }
                    if (splited.length == 1) {
                        throw new IncorrectFormatException("Task name or date not provided.");
                    }
                    String[] timesplit = new String[]{"0000-00-00", "0000"};
                    if (splited[1].indexOf(" ") > 0) {
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
                        throw new IncorrectFormatException("Date format provided incorrectly.\n    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).");
                    }
                    this.tasklist.addEntry(new String[]{isDeadline ? "D" : "E", splited[0]}, date);
                }
            } else {
                if (!input.matches("^[0-9]*$")) {
                    throw new IncorrectFormatException("Number not detected.");
                }
                int num = Integer.parseInt(input);
                if (splitted[0].equalsIgnoreCase("/done")) {
                    this.tasklist.changeDone(num);
                } else if (splitted[0].equalsIgnoreCase("/delete")) {
                    this.tasklist.removeEntry(num);
                }
            }
        } else if (input.equalsIgnoreCase("help")) {
            System.out.println(Ui.bar + "\n    ToDos: tasks without any date/time attached to it e.g., visit new theme park\n      FORMAT: todo TASKNAME\n    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm\n      FORMAT: deadline TASKNAME /by DEADLINE\n    Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm\n      FORMAT: todo TASKNAME /by DATE\n    Date format: yyyy-mm-dd TIME ((optional) in 24hrs format).\n    \"/done x\" where x is the task number to mark task as done.\n    \"/check DATE\" where DATE is the date you want to query.\n    \"/clearlist\" clears.\n    \"/list x\" lists.\n    \"/delete x\" where x is the task number to be deleted. \n" + Ui.bar);
        } else {
            if (!input.equals("/clearlist")) {
                throw new IncorrectFormatException("Command incomplete.");
            }
            this.tasklist.clearList();
        }

    }
}