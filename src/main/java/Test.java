import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        String sDate1="31/12/1998 1800";
        Date date1=new SimpleDateFormat("dd/MM/yyyy HHmm").parse(sDate1);

        LocalDateTime newDT = new java.sql.Timestamp(date1.getTime()).toLocalDateTime();

        //hh -> 12hr, HH -> 24hr, a -> AM/PM
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm HH:mm");

        String formattedDate = newDT.format(myFormatObj);

        System.out.println(sDate1+"\n"+formattedDate);
    }
}
