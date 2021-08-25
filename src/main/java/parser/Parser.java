package parser;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

import models.Command;
import processor.IProcessor;

public class Parser implements IParser {

    private final Scanner scanner;
    private final IProcessor processor;

    public Parser(IProcessor processor) {
        this.scanner = new Scanner(System.in);
        this.processor = processor;
    }

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

    public boolean nextLine() {
        String line = scanner.nextLine();
        System.out.println("\t" + "____________________________________________________________");
        return parseLine(line);
    }
}