import java.time.LocalDate;

public class FormatAdapter {
    private static final String indentation = "     ";
    private static final String subIndentation = "       ";
    private static final String[] months  = new String[] {
            "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY",
            "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
    };

    public String formatMessage(String message) {
        return "    ____________________________________________________________\n" + indentation +
                message + "    ____________________________________________________________";
    }

    public LocalDate convertToLocalTime(String time) {
        String copy = time;
        String month = copy.substring(0,time.indexOf(" "));
        int monthValue = -1;
        for (int i = 0; i < months.length; i++) {
            if(month.equals(months[i])) {
                monthValue = i + 1;
                break;
            }
        }
        copy = copy.replace(month + " ", "");
        String day = copy.substring(0,copy.indexOf(" ")).trim();
        int dayValue = Integer.parseInt(day);
        copy = copy.replace(day + " ", "");
        int yearValue = Integer.parseInt(copy);
        return LocalDate.of(yearValue,monthValue,dayValue);
    }

    public static String getIndentation() {
        return indentation;
    }

    public static String getSubIndentation() {
        return subIndentation;
    }
}
