package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.util.Pair;
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
    public Pair<Boolean, String> parseLine(String line) {
        List<String> arguments = new ArrayList<>(Arrays.asList(line.split(" ")));
        if (line.equals("bye")) {
            return new Pair<>(false, this.processor.processCommand(Command.BYE, arguments));
        } else if (line.equals("list")) {
            return new Pair<>(true, this.processor.processCommand(Command.LIST, arguments));
        } else if (arguments.get(0).equals("done")) {
            return new Pair<>(true, this.processor.processCommand(Command.DONE, arguments));
        } else if (arguments.get(0).equals("delete")) {
            return new Pair<>(true, this.processor.processCommand(Command.DELETE, arguments));
        } else if (arguments.get(0).equals("find")) {
            return new Pair<>(true, this.processor.processCommand(Command.FIND, arguments));
        } else {
            return new Pair<>(true, this.processor.processCommand(Command.DEFAULT, arguments));
        }
    }

    /**
     * Function implementation that allows Parser to take the next command from the user.
     *
     * @return True if the command entered is not bye and the response from the chatbot.
     */
    public Pair<Boolean, String> nextLine() {
        String line = scanner.nextLine();
        System.out.println("\t" + "____________________________________________________________");
        return parseLine(line);
    }

    /**
     * Function implementation that allows Parser to take command from the user and get response.
     *
     * @return True if the command entered is not bye and the response from the chatbot.
     */
    public Pair<Boolean, String> getResponse(String input) {
        return parseLine(input);
    }
}
