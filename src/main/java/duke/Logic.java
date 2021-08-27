package duke;

import java.util.ArrayList;

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
    public static void checkIfSpecialComand(String command) throws InvalidCommandException {
        //duke.Logic to check each individual commands, checks for special commands first, then checks for other input
        //Use duke.Parser to package command into a packaged command
        Command packagedCommand = Parser.parse(command);
        ArrayList<String> listOfCommandInputs = packagedCommand.getListOfCommandInputs();
        String loggedCommand = packagedCommand.getLog();
        if (command.equals("")) {
            throw new EmptyCommandException();
        } else if (command.equals("bye")) {
            Duke.stop();
            DataHandlerLayer.updateHistory();
            DataHandlerLayer.stopWriting();
        } else if (listOfCommandInputs.size() == 1 && listOfCommandInputs.get(0).equals("list")) {
            DataHandlerLayer.printLog();
        } else if (listOfCommandInputs.contains("delete")) {
            int position = Integer.parseInt(listOfCommandInputs.get(listOfCommandInputs.indexOf("delete") + 1));
            try {
                DataHandlerLayer.delete(position);
            } catch (IndexOutOfBoundsException exception) {
                throw new InvalidCommandException();
            }
            DataHandlerLayer.updateHistory();
        } else if (listOfCommandInputs.contains("done")) {
            int pos = Integer.parseInt(listOfCommandInputs.get(1));
            if (pos > Task.getNumberOfTask()) {
                throw new InvalidCommandException();
            }
            Task currentTask = DataHandlerLayer.getTask(pos - 1);
            currentTask.completeTask();
            DataHandlerLayer.updateHistory();
            System.out.println("Ohhhh myyyy. I have been waiting for this quest to complete for ages.");
        } else {
            processTask(packagedCommand, true);
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
                System.out.println("preload");
                System.out.println(command.getTaskType());
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

        switch (packagedCommand.getTaskType()) {
        case TODO:
            Todo tempTodo = new Todo(loggedCommand);
            DataHandlerLayer.addToLog(tempTodo);
            if (isWrittenToHistory) {
                DataHandlerLayer.appendToHistory(tempTodo);
            }
            break;
        case DEADLINE:
            int indicatorDeadline = listOfCommandInputs.indexOf("/by");
            String tempDeadlineString = new String();
            String deadlineDate = listOfCommandInputs.get(indicatorDeadline + 1);
            String deadlineTime = listOfCommandInputs.get(indicatorDeadline + 2);
            String deadlineDateTime = deadlineDate + " " + deadlineTime;
            for (int i = 0; i < indicatorDeadline; i++) {
                tempDeadlineString = tempDeadlineString + listOfCommandInputs.get(i);
            }
            Deadline tempDeadLine = new Deadline(tempDeadlineString, Parser.convertToDateTime(deadlineDateTime));
            DataHandlerLayer.addToLog(tempDeadLine);
            if (isWrittenToHistory) {
                DataHandlerLayer.appendToHistory(tempDeadLine);
            }
            break;
        case EVENT:
            int indicatorEvent = listOfCommandInputs.indexOf("/at");
            String date = listOfCommandInputs.get(indicatorEvent + 1);
            String time = listOfCommandInputs.get(indicatorEvent + 2);
            String dateTime = date + " " + time;
            String temp = new String();
            for (int i = 0; i < indicatorEvent; i++) {
                temp = temp + listOfCommandInputs.get(i);
            }
            System.out.println("=====================");
            System.out.println(dateTime);
            System.out.println("=====================");
            Event tempEvent = new Event(temp, Parser.convertToDateTime(dateTime));
            DataHandlerLayer.addToLog(tempEvent);
            if (isWrittenToHistory) {
                DataHandlerLayer.appendToHistory(tempEvent);
            }
            break;
        case NOTAPPLICABLE:
            //For echoing commands
        }
        DataHandlerLayer.updateHistory();
        DataHandlerLayer.printHistory();
    }
}
