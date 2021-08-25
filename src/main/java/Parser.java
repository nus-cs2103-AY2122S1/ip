import java.util.*;

public class Parser {

    /**
     * Function that handles all the parsing of the Parser system
     * @param command Refers to the entire line of strings the user entered. The line is then process here
     *                and packaged into Command. See Command for more.
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

    public static ArrayList<Command> parsePreloadedTasks(ArrayList<String> previousHistory) throws InvalidCommandException {
        int eventTypeIndex = 1;
        int isCompletedIndex = 4;
        ArrayList<Command> preloadedList = new ArrayList<>();

        for (String line: previousHistory) {
            String[] temp_packaged_history = line.split(" ");
            ArrayList<String> packaged_history = new ArrayList<>();
            Collections.addAll(packaged_history,temp_packaged_history);

            Task.TaskType eventType = Task.TaskType.NOTAPPLICABLE;
            switch (packaged_history.get(0).charAt(eventTypeIndex)) {
                case 'T':
                    eventType = Task.TaskType.TODO;
                    break;
                case 'E':
                    eventType = Task.TaskType.EVENT;
                    break;
                case 'D':
                    eventType = Task.TaskType.DEADLINE;
                case 'N':
                    eventType = Task.TaskType.NOTAPPLICABLE;
            }
            boolean isCompleted = packaged_history.get(0).charAt(isCompletedIndex) == '✓';
            packaged_history.remove(0);
            Command command = new Command(eventType, packaged_history , String.join(" ", packaged_history));
            preloadedList.add(command);
        }
        return preloadedList;
    }
}
