import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static ArrayList<Task> loadTasks(List<String> tasks) throws FileParseErrorException {
        ArrayList<Task> finalTasks = new ArrayList<>();
        for(int i = 0; i<tasks.size();i++) {
            String taskString = tasks.get(i);
            String[] tokens = taskString.split(",");
            if(tokens.length < 3) {
                System.out.println("damn");
                throw new FileParseErrorException();
            }

            Type type;
            String interpretedString = "";
            Boolean done = false;

            if(tokens[1].equals("0")) {
                done = false;
            } else if (tokens[1].equals("1")) {
                done = true;
            } else {
                throw new FileParseErrorException();
            }

            LocalDateTime localDateTime;
            if(tokens[0].equals("D") && tokens.length == 4) {
                type = Type.DEADLINE;
                interpretedString = tokens[2];
                localDateTime = parseDate(tokens[3]);
            } else if (tokens[0].equals("E") && tokens.length == 4) {
                type = Type.EVENT;
                interpretedString = tokens[2];
                localDateTime = parseDate(tokens[3]);
            } else if (tokens[0].equals("T") && tokens.length == 3) {
                type = Type.TODO;
                interpretedString = tokens[2];
                localDateTime = null;
            } else {
                throw new FileParseErrorException();
            }
            Task task = TaskList.initialiseByType(interpretedString, type, done, localDateTime);
            finalTasks.add(task);
        }
        return finalTasks;
    }

    public static LocalDateTime parseDate (String datetime) {
        if(datetime.length() == 10) {
            datetime += " 00:00";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, formatter);
        return localDateTime;
    }
}
