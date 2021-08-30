package parser;

import dao.TaskDaoImpl;
import logic.ICommandLogicUnit;
import model.Command;
import ui.IUi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
            "list (#date dd/MM/yyyy HHmm)",
            "done #index",
            "deadline #desc /by #deadline-date(dd/MM/yyyy HHmm)",
            "todo #desc",
            "event #desc /at #timing-date(dd/MM/yyyy HHmm)",
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
            commandLogicUnit.processCommand(Command.BYE, Map.of());
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
            ui.printSentence("() -> optional parameter or format of date time\n"
                    + "# -> the parameter of the commands");
            ui.printIndexedList(availableCommands);
            break;
        default:
            logger.warning("Unknown command : " + parsedCommands.get(0) + "being parsed");
            processException(new IllegalCallerException("I'm sorry, but I don't know what that means :-("));
            break;
        }
    }
    
    private void parseList(List<String> parsedCommands) {
        String date = "";
        
        if (parsedCommands.size() >= 2) {
            date = parsedCommands.get(1);
        }
        
        commandLogicUnit.processCommand(Command.LIST, Map.of(
                "date", date
        ));
    }
    
    private void parseDone(List<String> parsedCommands) {
        try {
            commandLogicUnit.processCommand(Command.DONE, Map.of(
                    "index", parsedCommands.get(1)
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void parseDeadline(List<String> parsedCommands) {
        try {
            int byIndex = parsedCommands.indexOf("/by");
            if (byIndex == -1) {
                logger.warning("/by not supplied in deadline command");
                throw new IllegalArgumentException("/by command not found");
            }
            
            String desc = String.join(" ", parsedCommands.subList(1, byIndex));
            if (desc.isBlank()) {
                logger.warning("Empty deadline desc supplied");
                throw new IllegalArgumentException("deadline desc cannot be empty");
            }
            
            String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
            if (timing.isBlank()) {
                logger.warning("Empty deadline timing supplied");
                throw new IllegalArgumentException("deadline timing cannot be empty");
            }
            
            commandLogicUnit.processCommand(Command.DEADLINE, Map.of(
                    "description", desc,
                    "timing", timing
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void parseTodo(List<String> parsedCommands) {
        try {
            String desc = String.join(" ", parsedCommands.subList(1, parsedCommands.size()));
            
            if (desc.isBlank()) {
                logger.warning("Empty Todo desc being supplied");
                throw new IllegalArgumentException("todo desc cannot be empty");
            }
            
            commandLogicUnit.processCommand(Command.TODOS, Map.of(
                    "description", desc
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void parseEvent(List<String> parsedCommands) {
        try {
            int byIndex = parsedCommands.indexOf("/at");
            if (byIndex == -1) {
                logger.warning("/at not supplied in event command");
                throw new IllegalArgumentException("/at command not found");
            }
            
            String desc = String.join(" ", parsedCommands.subList(1, byIndex));
            if (desc.isBlank()) {
                logger.warning("Empty event desc supplied");
                throw new IllegalArgumentException("event desc cannot be empty");
            }
            
            String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
            if (timing.isBlank()) {
                logger.warning("Empty todo timing supplied");
                throw new IllegalArgumentException("event timing cannot be empty");
            }
            
            commandLogicUnit.processCommand(Command.EVENT, Map.of(
                    "description", desc,
                    "timing", timing
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void parseDelete(List<String> parsedCommands) {
        try {
            commandLogicUnit.processCommand(Command.DELETE, Map.of(
                    "index", parsedCommands.get(1)
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void parseFind(List<String> parsedCommands) {
        if (parsedCommands.size() < 2) {
            processException(new IllegalArgumentException("keyword cannot be empty"));
        }
        
        String keyword = parsedCommands.get(1);
        try {
            commandLogicUnit.processCommand(Command.FIND, Map.of(
                    "keyword", keyword
            ));
        } catch (Exception e) {
            processException(e);
        }
    }
    
    private void processException(Exception e) {
        ui.printSentence("☹ OOPS!!! " + e.getMessage());
    }
}
