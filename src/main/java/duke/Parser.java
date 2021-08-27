package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * duke.Parser that handles all the string manipulation. The users input is taken in as a string and passed to parser
 * for packaging into a command.
 */
public class Parser {

    /**
     * Function that handles all the parsing of the duke.Parser system
     *
     * @param command Refers to the entire line of strings the user entered. The line is then process here
     *                and packaged into duke.Command. See duke.Command for more.
     * @return A packaged command for ease of access of infomation
     */
    public static Command parse(String command) throws InvalidCommandException {
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
        Collections.addAll(listOfCommandInputs, command.split(" "));
        if (listOfCommandInputs.contains(stringTaskType)) {
            int redundantIndicator = listOfCommandInputs.indexOf(stringTaskType);
            for (int i = 0; i <= redundantIndicator; i++) {
                listOfCommandInputs.remove(0);
            }
        }

        String tempCommand = new String();
        for (String commandsLeft : listOfCommandInputs) {
            commandsLeft += " ";
            tempCommand += commandsLeft;
        }

        packagedCommand = new Command(taskType, listOfCommandInputs, tempCommand);
        return packagedCommand;
    }

    /**
     * Used to parse a different kind of string format and package into and arraylist of task. Only for strings
     * found in the history, aka persistent storage
     * @param previousHistory ArrayList of strings in previous history
     * @return
     * @throws InvalidCommandException
     */
    public static ArrayList<Command> parsePreloadedTasks(ArrayList<String> previousHistory) throws InvalidCommandException {
        int eventTypeIndex = 1;
        int isCompletedIndex = 4;
        ArrayList<Command> preloadedList = new ArrayList<>();

        for (String line : previousHistory) {
            String[] temp_packaged_history = line.split(" ");
            ArrayList<String> packaged_history = new ArrayList<>();
            Collections.addAll(packaged_history, temp_packaged_history);

            Task.TaskType eventType = Task.TaskType.NOTAPPLICABLE;
            char temp = packaged_history.get(0).charAt(eventTypeIndex);
            System.out.println("=========================");
            System.out.println(temp);
            System.out.println("=========================");
            switch (packaged_history.get(0).charAt(eventTypeIndex)) {
            case 'T':
                eventType = Task.TaskType.TODO;
                break;
            case 'E':
                eventType = Task.TaskType.EVENT;
                packaged_history.remove("event");
                break;
            case 'D':
                eventType = Task.TaskType.DEADLINE;
                packaged_history.remove("deadline");
                break;
            case 'N':
                eventType = Task.TaskType.NOTAPPLICABLE;
            }
            packaged_history.remove(0);
            System.out.println(eventType);
            Command command = new Command(eventType, packaged_history, String.join(" ", packaged_history));
            preloadedList.add(command);
        }
        return preloadedList;
    }

    /**
     * Converts a string to local date time
     * @param datetimeString string in date and time format
     * @return A local dateTime to show what time it is
     * @throws InvalidCommandException when the string is not in the right format
     */
    public static LocalDateTime convertToDateTime(String datetimeString) throws InvalidCommandException {
        try {
            System.out.println(datetimeString);
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
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
    }
}
