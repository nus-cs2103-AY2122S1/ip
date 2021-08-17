package Processes;

import java.util.List;

import Enum.Command;
import Util.Output;

public class Processor implements IProcessor {

    private final TaskList list = new TaskList();

    @Override
    public void processCommand(Command command, List<String> arguments) {
        switch(command) {
            case BYE:
                processBye();
                break;
            case LIST:
                processList();
                break;
            case DONE:
                processDone(arguments.get(1));
                break;
            default:
                processDefault(String.join(" ", arguments));
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
    public void processDone(String index) {
        int i = Integer.parseInt(index);
        this.list.setDone(i - 1);
        Output.print("Nice! I've marked this task as done:\n   " + this.list.getTask(i - 1));
    }

    @Override
    public void processBye() {
        Output.print("Bye. Please meet me again later!");
    }
}