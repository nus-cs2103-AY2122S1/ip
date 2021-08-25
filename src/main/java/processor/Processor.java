package processor;

import config.Setting;
import exception.DukeException;
import models.Command;
import models.Deadline;
import models.Event;
import models.Todo;
import storage.IStorage;
import storage.Storage;
import ui.Ui;

import java.util.List;


import java.time.format.DateTimeParseException;
import java.time.LocalDate;


public class Processor implements IProcessor {

    private IStorage storage;

    public Processor(IStorage storage) {
        this.storage = storage;
    }

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
        case DELETE:
            processDelete(arguments.get(1));
            break;
        default:
            processDefault(arguments);
            break;
        }
    }

    @Override
    public void processDefault(List<String> arguments) {
        try {
            String type = arguments.get(0);
            if (type.equals("todo")) {
                arguments.remove(0);
                if (arguments.size() == 0) {
                    throw new DukeException("Todo description cannot be empty");
                }
                this.storage.addTask(new Todo(String.join(" ", arguments)));
            } else if (type.equals("deadline")) {
                arguments.remove(0);
                if (arguments.size() == 0) {
                    throw new DukeException("Deadline description cannot be empty");
                }
                String line = String.join(" ", arguments);
                String[] input = line.split(" /by ");
                if (input.length == 1) {
                    throw new DukeException("Deadline command must have /by specified");
                }
                LocalDate time = LocalDate.parse(input[1].trim());
                this.storage.addTask(new Deadline(input[0], time));
            } else if (type.equals("event")) {
                arguments.remove(0);
                if (arguments.size() == 0) {
                    throw new DukeException("Event description cannot be empty");
                }
                String line = String.join(" ", arguments);
                String[] input = line.split(" /at ");
                if (input.length == 1) {
                    throw new DukeException("Event command must have /at specified");
                }
                LocalDate time = LocalDate.parse(input[1].trim());
                this.storage.addTask(new Event(input[0], time));
            } else {
                throw new DukeException("I don't understand:(");
            }
            Ui.print("Got it. I've added this task:\n   " + this.storage.getLastTask() + "\nNow you have " + this.storage.getSize() + " tasks in the list.");
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.print(e.getMessage());
        }
    }

    @Override
    public void processList() {
        Ui.print(this.storage.toString());
    }

    @Override
    public void processDone(String index) {
        try {
            int i = Integer.parseInt(index);
            this.storage.setDone(i - 1);
            Ui.print("Nice! I've marked this task as done:\n   " + this.storage.getTask(i - 1));
        } catch(DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    @Override
    public void processDelete(String index) {
        try {
            int i = Integer.parseInt(index);
            String result = this.storage.deleteTask(i - 1);
            Ui.print("Got it! I've removed this task:\n   " + result + "\nNow you have " + this.storage.getSize() + " tasks in the list.");
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    @Override
    public void processBye() {
        Ui.print("Bye. Please meet me again later!");
    }
}