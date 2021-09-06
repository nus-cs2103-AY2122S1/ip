package utils;

import exceptions.DukeException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DukeDate {


 // OPTION 1
// Why you don't use SimpleDateFormat instead of regex :
//
//            try{
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
//        format.parse("55555");
//        System.out.println("Correct date");
//    }catch(ParseException e){
//        System.out.println("Incorrect date");
//    }


 // OPTION 2

    // from stackoverflow
    // https://stackoverflow.com/questions/43845215/java-regex-to-check-for-date-and-time
//    String[] patterns = {"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH"};
//    String date = "2018-02-02 11:50";
//    boolean check = Arrays.asList(patterns).stream()
//            .anyMatch(pattern -> {
//                try {
//                    LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
//                    System.out.println("Correct");
//                    return true;
//                } catch (Exception e) {
//                    System.out.println("Not Correct");
//                    return false;
//                }
//            });
//
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
