package parser;

import dao.TaskDaoImpl;
import logic.CommandArgument;
import logic.ICommandLogicUnit;
import model.Command;
import ui.IUi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * An entity that takes in the input from the console and parse it to valid command or throw exception
 * if the command is invalid
 */
public class ParserImpl implements IParser {
    /** CommandLogicUnit to process all the commands */
    private final ICommandLogicUnit commandLogicUnit;
    
    /** UI that is responsible for dealing with interactions with the user */
    private final IUi ui;
    
    /** list of available commands and arguments that can be parsed */
    private final List<String> availableCommands = List.of(
            "bye",
            "list (#date dd/MM/yyyy)",
            "done #index",
            "deadline #desc /by #deadline-date(dd/MM/yyyy)",
            "todo #desc",
            "event #desc /at #timing-date(dd/MM/yyyy)",
            "delete #index"
    );
    
    private final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());
    
    /**
     * CommandProcessorImplementation that would parse the inputs into commands.
     *
     * @param commandLogicUnit CommandLogicUnit that would process the parsed input.
     * @param ui UI.
     */
    public ParserImpl(ICommandLogicUnit commandLogicUnit, IUi ui) {
        this.commandLogicUnit = commandLogicUnit;
        this.ui = ui;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(String input) {
        // take the next command within a line, inputted using enter
        List<String> parsedCommands = Arrays.asList(input.split(" "));
        
        processInputHelper(parsedCommands);
    }
    
    /**
     * Processes the input as list of strings into commands.
     * Helper method for processInput method.
     *
     * @param parsedCommands List of string from user inputs.
     */
    private void processInputHelper(List<String> parsedCommands) {
        switch (parsedCommands.get(0)) {
        case "bye":
            parseBye();
            break;
        case "list":
            parseList(parsedCommands);
            break;
        case "done":
            parseDone(parsedCommands);
            break;
        case "deadline":
            parseDeadline(parsedCommands);
            break;
        case "todo":
            parseTodo(parsedCommands);
            break;
        case "event":
            parseEvent(parsedCommands);
            break;
        case "delete":
            parseDelete(parsedCommands);
            break;
        case "find":
            parseFind(parsedCommands);
            break;
        case "man":
            parseMan();
            break;
        default:
            logger.warning("Unknown command : " + parsedCommands.get(0) + "being parsed");
            IllegalCallerException illegalCallerException = new IllegalCallerException("I'm sorry, but I don't know what that means :-(");
            processException(illegalCallerException);
            break;
        }
    }
    
    private void parseBye() {
        CommandArgument arguments = new CommandArgument();
        
        commandLogicUnit.processCommand(Command.BYE, arguments);
    }
    
    private void parseList(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        if (parsedCommands.size() > 1) {
            String date = parsedCommands.get(1);
            LocalDateTime localDateTime = parseStringToLocalDateTime(date);
            
            if (localDateTime == null) {
                return;
            }
            
            arguments.setTiming(localDateTime);
        }
        
        commandLogicUnit.processCommand(Command.LIST, arguments);
    }
    
    private void parseDone(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
    
        try {
            Integer index = parseIndexStringToInt(parsedCommands);
            arguments.setIndex(index);
            commandLogicUnit.processCommand(Command.DONE, arguments);
        } catch (IndexOutOfBoundsException e) {
            processException(e);
        }
    }
    
    private void parseDeadline(List<String> parsedCommands) {
        String timingSeparator = "/by";
        CommandArgument arguments = parseTimedTaskArguments(parsedCommands, timingSeparator);
        
        commandLogicUnit.processCommand(Command.DEADLINE, arguments);
    }
    
    private CommandArgument parseTimedTaskArguments(List<String> parsedCommands, String timingSeparator) {
        CommandArgument arguments = new CommandArgument();
        
        int byIndex = parsedCommands.indexOf(timingSeparator);
        if (byIndex == -1) {
            logger.warning("/by not supplied in deadline command");
            throw new IllegalArgumentException("/by command not found");
        }
        
        String desc = String.join(" ", parsedCommands.subList(1, byIndex));
        if (desc.isBlank()) {
            logger.warning("Empty deadline desc supplied");
            throw new IllegalArgumentException("deadline desc cannot be empty");
        }
        
        arguments.setDescription(desc);
        
        String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
        if (timing.isBlank()) {
            logger.warning("Empty deadline timing supplied");
            throw new IllegalArgumentException("deadline timing cannot be empty");
        }
        
        LocalDateTime localDateTime = parseStringToLocalDateTime(timing);
        arguments.setTiming(localDateTime);
        
        return arguments;
    }
    
    private void parseTodo(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
    
        String desc = String.join(" ", parsedCommands.subList(1, parsedCommands.size()));
    
        if (desc.isBlank()) {
            logger.warning("Empty Todo desc being supplied");
            throw new IllegalArgumentException("todo desc cannot be empty");
        }
    
        arguments.setDescription(desc);
    
        commandLogicUnit.processCommand(Command.TODOS, arguments);
    }
    
    private void parseEvent(List<String> parsedCommands) {
        String timingSeparator = "/at";
        CommandArgument arguments = parseTimedTaskArguments(parsedCommands, timingSeparator);
    
        commandLogicUnit.processCommand(Command.EVENT, arguments);
    }
    
    private void parseDelete(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
    
        try {
            int index = parseIndexStringToInt(parsedCommands);
            arguments.setIndex(index);
        
            commandLogicUnit.processCommand(Command.DELETE, arguments);
        } catch (IndexOutOfBoundsException e) {
            processException(e);
        }
    }
    
    private void parseFind(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
    
        if (parsedCommands.size() < 2) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("keyword cannot be empty");
            processException(illegalArgumentException);
            return;
        }
    
        String keyword = parsedCommands.get(1);
        arguments.setDescription(keyword);
    
        commandLogicUnit.processCommand(Command.FIND, arguments);
    }
    
    private void parseMan() {
        ui.printSentence("() -> optional parameter or format of date time\n"
                + "# -> the parameter of the commands");
        ui.printIndexedList(availableCommands);
    }
    
    private void processException(Exception e) {
        assert e.getMessage() != null;
        
        ui.printSentence("OOPS!!! " + e.getMessage());
    }
    
    private LocalDateTime parseStringToLocalDateTime(String localDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try {
            return LocalDateTime.parse(localDateTimeString, formatter);
        } catch (DateTimeParseException e) {
            processException(new IllegalArgumentException("Invalid date time format, please follow dd/MM/yyyy"));
        }
        
        return null;
    }
    
    private int parseIndexStringToInt(List<String> parsedCommands) {
        return Integer.parseInt(parsedCommands.get(1)) - 1;
    }
}
