import java.util.*;

public class Parser {

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

        command = command.replaceAll(stringTaskType, "");
        //Split the list of commands
        Command packagedCommand;
        ArrayList<String> listOfCommandInputs = new ArrayList<>();
        Collections.addAll(listOfCommandInputs,command.split(" "));
        packagedCommand = new Command(taskType, listOfCommandInputs, command);
        return packagedCommand;
    }
}
