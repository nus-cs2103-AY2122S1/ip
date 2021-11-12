package duke;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * This is the logic layer where most of the commands are processed and errors are handled.
 * Please refer to the structure of the diagram in the docs for more information
 */
public class Logic {

    /**
     * This is to check if any special command or empty command is given
     *
     * @param command refers to the parsed string. See presentation for parsing
     * @throws InvalidCommandException is throwed when there is an invalid command in the form of a string
     */
    public static String checkIfSpecialCommand(String command) {
        //duke.Logic to check each individual commands, checks for special commands first, then checks for other input
        //Use duke.Parser to package command into a packaged command
        try {
            Command packagedCommand = Parser.parse(command);
            ArrayList<String> listOfCommandInputs = packagedCommand.getListOfCommandInputs();
            String loggedCommand = packagedCommand.getLog();
            if (command.equals("")) {
                throw new EmptyCommandException();
            } else if (command.equals("bye")) {
                return processBye();
            } else if (listOfCommandInputs.size() == 1 && listOfCommandInputs.get(0).equals("list")) {
                return processList();
            } else if (listOfCommandInputs.contains("delete")) {
                return processDelete(packagedCommand, listOfCommandInputs, loggedCommand);
            } else if (listOfCommandInputs.contains("done")) {
                return processDone(packagedCommand, listOfCommandInputs, loggedCommand);
            } else if (listOfCommandInputs.contains("find")) {
                return processFind(packagedCommand, listOfCommandInputs, loggedCommand);
            } else if (listOfCommandInputs.get(0).equals("help")) {
                return processHelp(listOfCommandInputs);
            } else {
                processTask(packagedCommand, true);
                return loggedCommand;
            }
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads in all the text history from previous times of running the bot
     */
    public static void preload() {
        try {
            ArrayList<Command> commandArrayList = DataHandlerLayer.loadPreset();
            for (Command command : commandArrayList) {
                processTask(command, false);
            }
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }


    /**
     * Process the tasks and pass it on to the data Handler layer for tasks
     *
     * @param packagedCommand    duke.Command that is parsed by the parser.
     * @param isWrittenToHistory boolean value if the command should be written to history.
     *                           most of the times this should be the case other than preload, since
     *                           writing to history would result in double occurence of tasks
     * @throws InvalidCommandException
     */
    public static void processTask(Command packagedCommand, boolean isWrittenToHistory) throws InvalidCommandException {
        //duke.Logic to check each individual commands, checks for special commands first, then checks for other input
        //Use duke.Parser to package command into a packaged command
        ArrayList<String> listOfCommandInputs = packagedCommand.getListOfCommandInputs();
        String loggedCommand = packagedCommand.getLog();
        Task tempTask = null;

        switch (packagedCommand.getTaskType()) {
        case TODO:
            tempTask = new Todo(loggedCommand);
            break;
        case DEADLINE:
            processDeadline(packagedCommand, listOfCommandInputs, loggedCommand, isWrittenToHistory);
            break;
        case EVENT:
            processEvent(packagedCommand, listOfCommandInputs, loggedCommand, isWrittenToHistory);
            break;
        case NOTAPPLICABLE:
            throw new InvalidCommandException();
        default:
            System.out.println("Should never reach here");
        }

        if (tempTask != null) {
            DataHandlerLayer.addToLog(tempTask);
            if (isWrittenToHistory) {
                DataHandlerLayer.appendToHistory(tempTask);
            }
        }

        DataHandlerLayer.updateHistory();
        DataHandlerLayer.printHistory();
    }

    private static String processBye() {
        Duke.stop();
        DataHandlerLayer.updateHistory();
        DataHandlerLayer.stopWriting();
        return "See ya";
    }

    private static String processList() {
        return DataHandlerLayer.getFilteredLog(a-> true);
    }

    private static String processDelete(Command packagedCommand,
                                        ArrayList<String> listOfCommandInputs, String loggedCommand) {
        int position = Integer.parseInt(listOfCommandInputs.get(listOfCommandInputs.indexOf("delete") + 1));
        DataHandlerLayer.delete(position);
        DataHandlerLayer.updateHistory();
        return "Cancel culture is real, even in this world";
    }

    private static String processDone(Command packagedCommand, ArrayList<String> listOfCommandInputs,
                                      String loggedCommand) throws InvalidCommandException {
        int pos = Integer.parseInt(listOfCommandInputs.get(1));
        if (pos > Task.getNumberOfTask()) {
            throw new InvalidCommandException();
        }
        Task currentTask = DataHandlerLayer.getTask(pos - 1);
        currentTask.completeTask();
        DataHandlerLayer.updateHistory();
        return "Ohhhh myyyy. I have been waiting for this quest to complete for ages.";
    }

    private static String processFind(Command packagedCommand,
                                      ArrayList<String> listOfCommandInputs, String loggedCommand) {
        String temp = listOfCommandInputs.get(listOfCommandInputs.indexOf("find") + 1);
        Function<Task, Boolean> findKeyword = a -> a.toString().contains(temp);
        return DataHandlerLayer.getFilteredLog(findKeyword);
    }

    private static String processHelp(ArrayList<String> listOfCommandInputs) throws InvalidCommandException {
        if (listOfCommandInputs.size() > 2) {
            throw new InvalidCommandException();
        } else if (listOfCommandInputs.size() == 1) {
            return HelpCommand.getCommand(0);
        }
        return HelpCommand.getCommand(Integer.parseInt(listOfCommandInputs.get(1)));
    }

    private static void processDeadline(Command packagedCommand,
                                        ArrayList<String> listOfCommandInputs,
                                        String loggedCommand,
                                        boolean isWrittenToHistory) throws InvalidCommandException {

        int indicatorDeadline = listOfCommandInputs.indexOf("/by");
        String tempDeadlineString = new String();
        String deadlineDate = listOfCommandInputs.get(indicatorDeadline + 1);
        String deadlineTime = listOfCommandInputs.get(indicatorDeadline + 2);
        String deadlineDateTime = deadlineDate + " " + deadlineTime;
        for (int i = 0; i < indicatorDeadline; i++) {
            tempDeadlineString = tempDeadlineString + listOfCommandInputs.get(i) + " ";
        }

        Deadline tempDeadLine = new Deadline(tempDeadlineString, Parser.convertToDateTime(deadlineDateTime));
        DataHandlerLayer.addToLog(tempDeadLine);
        if (isWrittenToHistory) {
            DataHandlerLayer.appendToHistory(tempDeadLine);
        }
    }

    private static void processEvent(Command packagedCommand,
                                        ArrayList<String> listOfCommandInputs,
                                        String loggedCommand,
                                        boolean isWrittenToHistory) throws InvalidCommandException {
        int indicatorEvent = listOfCommandInputs.indexOf("/at");
        String date = listOfCommandInputs.get(indicatorEvent + 1);
        String time = listOfCommandInputs.get(indicatorEvent + 2);
        String dateTime = date + " " + time;
        String temp = new String();
        for (int i = 0; i < indicatorEvent; i++) {
            temp = temp + listOfCommandInputs.get(i) + " ";
        }
        Event tempEvent = new Event(temp, Parser.convertToDateTime(dateTime));
        DataHandlerLayer.addToLog(tempEvent);
        if (isWrittenToHistory) {
            DataHandlerLayer.appendToHistory(tempEvent);
        }
    }
}
