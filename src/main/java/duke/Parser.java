package duke;

import duke.command.BlahCommand;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;

public class Parser {
    private String input;

    public Parser() {
        //this.input = input;
    }

    public Command parse(String command) {
        if(command.equals("bye")) {
            return new ExitCommand(command);
        } else if(command.equals("list")) {
            return new ListCommand(command);
        } else if(command.equals("blah")) {
            return new BlahCommand(command);
        } else if(command.split(" ")[0].equals("done")) {
            String taskNumber = command.split(" ")[1];
            return new DoneCommand(taskNumber);
        } else if(command.split(" ")[0].equals("delete")) {
            String taskNumber = command.split(" ")[1];
            return new DeleteCommand(taskNumber);
        } else {
            return new AddCommand(command);
        }
    }

    //the parse method should return a command object
    // this command object will be an instance of one of the subclasses of command --> so there will be polymorphism
    // the command object will have an execute method which will be called (polymorphism)
    // where do we create parser object?

}
