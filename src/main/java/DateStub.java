import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateStub {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate d = LocalDate.parse("2/12/2019", formatter);
        System.out.println(d.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
