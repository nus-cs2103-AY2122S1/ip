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
                processDefault(arguments);
        }
    }

    @Override
    public void processDefault(List<String> arguments) {
        String type = arguments.get(0);
        if(type.equals("todo")) {
            arguments.remove(0);
            this.list.addTask(new Todo(String.join(" ", arguments)));
        } else if(type.equals("deadline")) {
            arguments.remove(0);
            String line = String.join(" ", arguments);
            String[] input = line.split(" /by ");
            if (input.length == 1) {
                Output.print("Invalid command!");
                return;
            }
            this.list.addTask(new Deadline(input[0], input[1]));
        } else if(type.equals("event")) {
            arguments.remove(0);
            String line = String.join(" ", arguments);
            String[] input = line.split(" /at ");
            if (input.length == 1) {
                Output.print("Invalid command!");
                return;
            }
            this.list.addTask(new Event(input[0], input[1]));
        } else {
            Output.print("Invalid command!");
            return;
        }
        Output.print("Got it. I've added this task:\n   " + this.list.getLastTask() + "\nNow you have " + this.list.getSize() + " in the list.");
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