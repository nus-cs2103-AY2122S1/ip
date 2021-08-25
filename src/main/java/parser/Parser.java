package parser;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

import models.Command;
import processor.IProcessor;

/**
 * Parser implementation that will parses command from the user.
 */
public class Parser implements IParser {
    /** Scanner object to read the line entered by the user. */
    private final Scanner scanner;

    /** Processor object which processes the parsed command. */
    private final IProcessor processor;

    /**
     * Constructor of the Parser object.
     *
     * @param processor Processor object that will process all the parsed commands.
     */
    public Parser(IProcessor processor) {
        this.scanner = new Scanner(System.in);
        this.processor = processor;
    }

    /**
     * Implementation that parses the command entered by the user.
     *
     * @param line Command that is entered by the user.
     * @return True if the command entered is not bye.
     */
    @Override
    public boolean parseLine(String line) {
        List<String> arguments = new ArrayList<>(Arrays.asList(line.split(" ")));
        if(line.equals("bye")) {
            this.processor.processCommand(Command.BYE, arguments);
            return false;
        } else if(line.equals("list")) {
            this.processor.processCommand(Command.LIST, arguments);
            return true;
        } else if(arguments.get(0).equals("done")) {
            this.processor.processCommand(Command.DONE, arguments);
            return true;
        } else if(arguments.get(0).equals("delete")) {
            this.processor.processCommand(Command.DELETE, arguments);
            return true;
        } else if(arguments.get(0).equals("find")) {
            this.processor.processCommand(Command.FIND, arguments);
            return true;
        } else {
            this.processor.processCommand(Command.DEFAULT, arguments);
            return true;
        }
    }

    /**
     * Function implementation that allows Parser to take the next command from the user.
     *
     * @return True if the command entered is not bye.
     */
    public boolean nextLine() {
        String line = scanner.nextLine();
        System.out.println("\t" + "____________________________________________________________");
        return parseLine(line);
    }
}