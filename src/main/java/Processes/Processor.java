package Processes;

import Enum.Command;
import Util.Output;

public class Processor implements IProcessor {

    @Override
    public void processCommand(Command command, String line) {
        switch(command) {
            case BYE:
                processBye();
                break;
            default:
                processDefault(line);
        }
    }

    @Override
    public void processDefault(String line) {
        Output.print(line);
    }

    @Override
    public void processBye() {
        Output.print("Bye. Please meet me again later!");
    }
}