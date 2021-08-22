import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyParser {
    public void parse(String command,String description,Duke duke) throws DukeException {
        String userDescription = description.trim();
        switch (command) {
            case "bye":
                duke.dukeBye();
                break;
            case "list":
                duke.dukeList();
                break;
            case "done": {
                if (userDescription.isBlank()) {
                    throw new DukeException("OOPS!!! The description of done cannot be empty");
                }

                String regex = "[0-9]+";

                if (!userDescription.matches(regex)) {
                    throw new DukeException("OOPS!!! The description of done must be an integer ");
                }

                int targetTask = Integer.parseInt(userDescription);

                duke.dukeDone(targetTask);
                break;
            }
            case "delete": {
                if (userDescription.isBlank()) {
                    throw new DukeException("OOPS!!! The description of delete cannot be empty");
                }

                String regex = "[0-9]+";

                if (!userDescription.matches(regex)) {
                    throw new DukeException("OOPS!!! The description of delete must be an integer ");
                }

                int targetTask = Integer.parseInt(userDescription);
                duke.dukeDelete(targetTask);
                break;
            }
            case "todo":
                if (userDescription.isBlank()) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                }

                duke.dukeTodo(userDescription);
                break;
            case "deadline": {
                int dueByIndex = userDescription.indexOf("/by ");
                if (userDescription.isBlank() || dueByIndex == 0) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
                }

                if (dueByIndex == -1) {
                    throw new DukeException("OOPS!! No deadline has been set. Reinput with '/by dueBy'");
                }

                String desc = userDescription.substring(0, dueByIndex);
                String dueBy = userDescription.substring(dueByIndex + 4);

                if (dueBy.isBlank()) {
                    throw new DukeException("OOPS!!! The description of /by cannot be empty");
                }

                Calendar deadlineCal = Calendar.getInstance();
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d/MM/YYYY HHmm");

                try {
                    deadlineCal.setTime(dateTimeFormat.parse(dueBy));
                } catch(ParseException e) {
                    throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                }

                duke.dukeDeadline(desc, deadlineCal);
                break;
            }
            case "event": {
                int atIndex = userDescription.indexOf("/at ");
                if (userDescription.isBlank() || atIndex == 0) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty");
                }

                if (atIndex == -1) {
                    throw new DukeException("OOPS!! No event time has been set. Reinput Event with '/at time'");
                }

                String desc = userDescription.substring(0, atIndex);
                String at = userDescription.substring(atIndex + 4);

                if (at.isBlank()) {
                    throw new DukeException("OOPS!!! The description of /at cannot be empty");
                }

                Calendar eventCal = Calendar.getInstance();
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d/MM/YYYY HHmm");

                try {
                    eventCal.setTime(dateTimeFormat.parse(at));
                } catch(ParseException e) {
                    throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                }
                duke.dukeEvent(desc, eventCal);
                break;
            }
            default:
                throw new DukeException("OOPS!!! I'm Sorry, but I don't know what that means");
        }
    }
}

