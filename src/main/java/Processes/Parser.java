package Processes;

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
        switch (line) {
            case "bye":
                this.processor.processCommand(Command.BYE, "");
                return false;
            case "list":
                this.processor.processCommand(Command.LIST, "");
                return true;
            default:
                this.processor.processCommand(Command.DEFAULT, line);
                return true;
        }
    }

    public boolean nextLine() {
        String line = scanner.nextLine();
        System.out.println("\t" + "____________________________________________________________");
        return parseLine(line);
    }
}