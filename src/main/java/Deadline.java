import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {
    final private LocalDateTime date;
    final private static String inputExample = "deadline return book /by 3/4/2021 400";

    public static Deadline of(String input) throws DukeException {
        String[] eachWord = input.split("/by");
        if (eachWord.length == 0 || eachWord[0].length() == 0 || eachWord[0].equals(" ")) {
            throw new DukeException("Description cannot be empty. Type description before /by\nEg."
                    + Deadline.inputExample);
        }
        if (eachWord.length == 1 || eachWord[1].length() == 0 || eachWord[1].equals(" ")) {
            throw new DukeException("The date for this event cannot be empty. Type date after /by\nEg."
                    + Deadline.inputExample);
        }
        String dateDescription = eachWord[1];
        String[] dateSplitBySpace = dateDescription.split(" ");
        if (dateSplitBySpace.length < 3) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Deadline.inputExample);
        }
        String[] dateArr = dateSplitBySpace[1].split("/");
        if (dateArr.length < 3) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Deadline.inputExample);
        }
        try {
            int year = Integer.parseInt(dateArr[2]);
            int month = Integer.parseInt(dateArr[1]);
            int date = Integer.parseInt(dateArr[0]);
            int hour = Integer.parseInt(dateSplitBySpace[2].substring(0, dateSplitBySpace[2].length() - 2));
            int min = Integer.parseInt(dateSplitBySpace[2].substring(dateSplitBySpace[2].length() - 2));
            LocalDateTime dateTime = LocalDateTime.of(year, month, date, hour, min);
            return new Deadline(eachWord[0], dateTime);
        } catch (NumberFormatException e) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Deadline.inputExample);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date inputed. Please check that the date is correct\nEg."
                    + Deadline.inputExample);
        }
    }

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.date = dateTime;
    }

//    private Deadline(String input) {
//        super(input.split("/by", 2)[0]);
//        String dateDescription = input.split("/by", 2)[1];
//        String[] dateSplitBySpace = dateDescription.split(" ");
//        String[] dateArr = dateSplitBySpace[1].split("/");
//        int hour = Integer.parseInt(dateSplitBySpace[2].substring(0, dateSplitBySpace[2].length() - 2));
//        this.date = LocalDateTime.of(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[1]),
//                Integer.parseInt(dateArr[0]), hour,
//                Integer.parseInt(dateSplitBySpace[2].substring(dateSplitBySpace[2].length() - 2)));
//    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + String.format("(by:%s)", this.date));
    }

    @Override
    public String typeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formatDateTime = this.date.format(formatter);
        return "deadline" + Task.sep + super.toSaveInFile("/by " + formatDateTime);
    }
}
