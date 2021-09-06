package duke.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DukeDate {

    public static Date formatDate(String dateLiteral){
        if(dateLiteral.length()==14 || dateLiteral.length()==15){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
            try{
                Date dateFormatted = format.parse(dateLiteral);
                //System.out.println(dateFormatted);
                return dateFormatted;
                // eg. dateLiteral = 2/12/2019 1800
                // after parse
                // Mon Dec 02 18:00:00 SGT 2019

            } catch (java.text.ParseException e){
                System.out.println("Incorrect date format");
            }

        } else if (dateLiteral.length() == 9 || dateLiteral.length() == 10){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date dateFormatted = format.parse(dateLiteral);
                //System.out.println(dateFormatted);
                return dateFormatted;
                // eg. dateLiteral = 2/12/2019
                // after parse
                // Mon Dec 02 00:00:00 SGT 2019

            } catch (java.text.ParseException e){
                System.out.println("Incorrect date format");
            }
        }
        System.out.println("Incorrect date format");
        return new Date();

    }

    public static String parseDateToString(Date date){
        String[] dateArr = date.toString().split(" ");
        String dateStr = "";
        dateStr += dateArr[0] + ", " + dateArr[1] + " " + dateArr[2] + " " + dateArr[5];

        // for adding the time to the string
        dateStr += ", " + dateArr[3].substring(0,5);

        return dateStr;
    }



}
