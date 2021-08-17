package Processes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

import Enum.Command;

public class Parser implements IParser {

    private final Scanner scanner;
    private final Processor processor;

    public Parser() {
        this.scanner = new Scanner(System.in);
        this.processor = new Processor();
    }

    @Override
    public boolean parseLine(String line) {
        List<String> arguments = new ArrayList<String>(Arrays.asList(line.split(" ")));
        if(line.equals("bye")) {
            this.processor.processCommand(Command.BYE, arguments);
            return false;
        } else if(line.equals("list")) {
            this.processor.processCommand(Command.LIST, arguments);
            return true;
        } else if(arguments.get(0).equals("done")) {
            this.processor.processCommand(Command.DONE, arguments);
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