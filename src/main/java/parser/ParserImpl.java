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
     * Creates CommandProcessorImplementation that would parse the inputs into commands.
     *
     * @param commandLogicUnit CommandLogicUnit that would process the parsed input.
     * @param ui UI.
     */
    public ParserImpl(ICommandLogicUnit commandLogicUnit, IUi ui) {
        this.commandLogicUnit = commandLogicUnit;
        this.ui = ui;
    }
    
    private CommandArgument parseBye() {
        CommandArgument arguments = new CommandArgument();
        
        arguments.setCommand(Command.BYE);
        
        return arguments;
    }
    
    private CommandArgument parseList(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        if (parsedCommands.size() > 1) {
            String date = parsedCommands.get(1);
            LocalDateTime localDateTime = parseStringToLocalDateTime(date);
            
            if (localDateTime == null) {
                return arguments;
            }
            
            arguments.setTiming(localDateTime);
        }
        
        arguments.setCommand(Command.LIST);
        return arguments;
    }
    
    private CommandArgument parseDone(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        try {
            Integer index = parseIndexStringToInt(parsedCommands);
            arguments.setIndex(index);
            arguments.setCommand(Command.DONE);
            
            return arguments;
        } catch (IndexOutOfBoundsException e) {
            processException(e);
        }
        
        arguments.setCommand(Command.INVALID);
        return arguments;
    }
    
    private CommandArgument parseDeadline(List<String> parsedCommands) {
        String timingSeparator = "/by";
        CommandArgument arguments = parseTimedTaskArguments(parsedCommands, timingSeparator);
        
        arguments.setCommand(Command.DEADLINE);
        return arguments;
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
    
    private CommandArgument parseTodo(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        String desc = String.join(" ", parsedCommands.subList(1, parsedCommands.size()));
        
        if (desc.isBlank()) {
            logger.warning("Empty Todo desc being supplied");
            throw new IllegalArgumentException("todo desc cannot be empty");
        }
        
        arguments.setDescription(desc);
        arguments.setCommand(Command.TODOS);
        
        return arguments;
    }
    
    private CommandArgument parseEvent(List<String> parsedCommands) {
        String timingSeparator = "/at";
        CommandArgument arguments = parseTimedTaskArguments(parsedCommands, timingSeparator);
        arguments.setCommand(Command.EVENT);
        return arguments;
    }
    
    private CommandArgument parseDelete(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        try {
            int index = parseIndexStringToInt(parsedCommands);
            arguments.setIndex(index);
            arguments.setCommand(Command.DELETE);
            
            return arguments;
        } catch (IndexOutOfBoundsException e) {
            processException(e);
        }
        arguments.setCommand(Command.INVALID);
        return arguments;
    }
    
    private CommandArgument parseFind(List<String> parsedCommands) {
        CommandArgument arguments = new CommandArgument();
        
        if (parsedCommands.size() < 2) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("keyword cannot be empty");
            processException(illegalArgumentException);
            
            arguments.setCommand(Command.INVALID);
            return arguments;
        }
        
        String keyword = parsedCommands.get(1);
        arguments.setDescription(keyword);
        arguments.setCommand(Command.FIND);
        
        return arguments;
    }
    
    private CommandArgument parseMan() {
        ui.printSentence("() -> optional parameter or format of date time\n"
                + "# -> the parameter of the commands");
        ui.printIndexedList(availableCommands);
        
        CommandArgument argument = new CommandArgument();
        argument.setCommand(Command.MAN);
        
        return argument;
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processCommand(CommandArgument argument) {
        Command command = argument.getCommand();
        commandLogicUnit.processCommand(command, argument);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CommandArgument parseInput(String input) {
        // take the next command within a line, inputted using enter
        List<String> parsedCommands = Arrays.asList(input.split(" "));
        
        switch (parsedCommands.get(0)) {
        case "bye":
            return parseBye();
        case "list":
            return parseList(parsedCommands);
        case "done":
            return parseDone(parsedCommands);
        case "deadline":
            return parseDeadline(parsedCommands);
        case "todo":
            return parseTodo(parsedCommands);
        case "event":
            return parseEvent(parsedCommands);
        case "delete":
            return parseDelete(parsedCommands);
        case "find":
            return parseFind(parsedCommands);
        case "man":
            return parseMan();
        default:
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            
            CommandArgument argument = new CommandArgument();
            argument.setCommand(Command.INVALID);
            
            if (stackTraceElements[2].getMethodName().equals("canProcessResponse")) {
                return argument;
            }
            
            logger.warning("Unknown command : " + parsedCommands.get(0) + "being parsed");
            IllegalCallerException illegalCallerException =
                    new IllegalCallerException("I'm sorry, but I don't know what that means :-(");
            processException(illegalCallerException);
            
            return argument;
        }
    }
}
