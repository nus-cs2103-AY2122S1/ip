import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time {
    public static boolean validateJavaDate(String strDate) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        sdfrmt.setLenient(false);

        try
        {
                Date javaDate = sdfrmt.parse(strDate);
        }
        catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static String changeDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputtedDate = LocalDate.parse(date, formatter);
        return inputtedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

}
