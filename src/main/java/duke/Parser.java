package duke;

import duke.command.Command;
import duke.command.deadlineCommand;
import duke.command.doneCommand;
import duke.command.eventCommand;
import duke.command.listCommand;
import duke.command.InvalidCommand;
import duke.command.deleteCommand;
import duke.command.todoCommand;

public class Parser {
    private String command;
    
    public Parser(String command) {
        this.command = command;
    }
    
    public Command parse() {
        if (command.equals("list")) {
            return new listCommand(command);
        } else if (command.startsWith("done") && Character.isDigit(command.charAt(command.length() - 1)) && command.length() <= 8 
                && !Character.isAlphabetic(command.charAt(command.length() - 2))  && Character.isDigit(command.charAt(5))) {
            return new doneCommand(command);
        } else {
            if (command.startsWith("todo")) {
                return new todoCommand(command);
            } else if (command.startsWith("deadline")) {
                return new deadlineCommand(command);
            } else if (command.startsWith("event")) {
                return new eventCommand(command);
            } else if (command.startsWith("delete")) {
                return new deleteCommand(command);
            } else {
                return new InvalidCommand(command);    
            }
        }
    }    
    
    public boolean isExit() {
        if (command.equals("bye")) {
            Ui.sayBye();
            Ui.showLine();
            return true;
        }
        if (command.equals("")) {
            return true;
        }
        return false;
    }
    
}