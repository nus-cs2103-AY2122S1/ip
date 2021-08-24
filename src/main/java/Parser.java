import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private DateTimeFormatter df;

    public Parser() {
        this.df = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
    }

    public String[] splitType(String input) {
        return input.split(" ", 2);
    }

    public int getIndex(String[] typeInput) {
        return Integer.parseInt(typeInput[1]) - 1;
    }

    public void checkDesc(String[] typeInput, String type) throws DukeException {
        if (typeInput.length < 2 || typeInput[1].equals("") || typeInput[1].equals(" ")) {
            throw new DukeException("The description of " + type + " cannot be empty!");
        }
    }

    public String[] furtherSplit(String input, String regex) {
        return input.split(regex, 2);
    }

    public void checkFurtherDesc(String[] furtherInput, String type) throws DukeException {
        if (furtherInput.length < 2 || furtherInput[0].equals("")) {
            throw new DukeException("The description of a " + type + " cannot be empty.\n" +
                    "Don't forget to use /by to indicate the deadline.");
        } else if (furtherInput[1].equals("") || furtherInput[1].equals(" ")) {
            throw new DukeException("Must come with a input date/time for the " + type + " .");
        }
    }

    public LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString.stripLeading(), df);
    }

    public void checkTaskIndex(int index, TaskList taskList) throws DukeException {
        if (index >= taskList.size() || index <= 0) {
            throw new DukeException("Task number does not exist!");
        }
    }
}
