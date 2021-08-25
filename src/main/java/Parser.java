import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Parser {

    public static void main(String[] args) {
        LocalDateTime curr = convertToDateTime("12/03/2021 23:00");
        LocalDateTime temp = convertToDateTime("2021/03/12 23:00");
        System.out.println(curr);
        System.out.println(temp);
    }

    /**
     * Function that handles all the parsing of the Parser system
     * @param command Refers to the entire line of strings the user entered. The line is then process here
     *                and packaged into Command. See Command for more.
     * @return A packaged command for ease of access of infomation
     */
    public static Command parse(String command) {
        //Remove TaskType from the command first
        Task.TaskType taskType = Task.TaskType.NOTAPPLICABLE;
        String stringTaskType = new String();
        if (command.contains("todo ")) {
            taskType = Task.TaskType.TODO;
            stringTaskType = "todo";
        } else if (command.contains("deadline ")) {
            taskType = Task.TaskType.DEADLINE;
            stringTaskType = "deadline ";
        } else if (command.contains("event")) {
            taskType = Task.TaskType.EVENT;
            stringTaskType = "event";
        }

        //command = command.replaceAll(stringTaskType, "");
        //Split the list of commands
        Command packagedCommand;
        ArrayList<String> listOfCommandInputs = new ArrayList<>();
        Collections.addAll(listOfCommandInputs,command.split(" "));
        if (listOfCommandInputs.contains(stringTaskType)) {
            int redundantIndicator = listOfCommandInputs.indexOf(stringTaskType);
            for (int i = 0; i <= redundantIndicator; i++) {
                listOfCommandInputs.remove(0);
            }
        }

        String tempCommand = new String();
        for (String commandsLeft: listOfCommandInputs) {
            commandsLeft += " ";
            tempCommand += commandsLeft;
        }

        packagedCommand = new Command(taskType, listOfCommandInputs, tempCommand);
        return packagedCommand;
    }

    public static LocalDateTime convertToDateTime(String datetimeString) {
        String[] temp = datetimeString.split(" ");
        String[] date = temp[0].split("/");
        String[] time = temp[1].split(":");
        DateTimeFormatter formatter;
        if (date[0].length() == 2) {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        }

        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);

        return LocalDateTime.parse(datetimeString, formatter);
    }
}
