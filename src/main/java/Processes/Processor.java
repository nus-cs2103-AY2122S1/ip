package Processes;

import Enum.Command;
import Util.Output;

public class Processor implements IProcessor {

    private final TaskList list = new TaskList();

    @Override
    public void processCommand(Command command, String line) {
        switch(command) {
            case BYE:
                processBye();
                break;
            case LIST:
                processList();
                break;
            default:
                processDefault(line);
        }
    }

    @Override
    public void processDefault(String line) {
        this.list.addTask(new Task(line));
        Output.print("Added: " + line);
    }

    @Override
    public void processList() {
        Output.print(this.list.toString());
    }

    @Override
    public void processBye() {
        Output.print("Bye. Please meet me again later!");
    }
}