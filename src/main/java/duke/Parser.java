package duke;

import duke.tasks.TaskManager;
import duke.tasks.TaskType;
import duke.command.*;

public class Parser {


    public Parser(){
    }


    // Reads the input of the line to determine the command and run it
    public Command parseCommand(TaskManager taskList, String userInput){

        // Get command from the userInput
        final String[] splitLine = userInput.split(" ", 3);
        final String command = splitLine[0];

        // Remove command from userInput
        final String arguments = userInput.replaceAll( RegexType.START_LINE_REGEX.getRegexType() + command + RegexType.SPACE_REGEX.getRegexType(), "");

        // Check the command type then execute the commands
        if(arguments.equals(InputType.LIST.getInputType())) {
            return new ListCommand(taskList);
        }

        if (command.equals(InputType.TODO.getInputType())){
            return new AddCommand(taskList, arguments, TaskType.TODO);
        }
        if (command.equals(InputType.DEADLINE.getInputType())){
            return new AddCommand(taskList, arguments, TaskType.DEADLINE);
        }
        if (command.equals(InputType.EVENT.getInputType())){
            return new AddCommand(taskList, arguments, TaskType.EVENT);
        }
        if (command.equals(InputType.DELETE.getInputType()) ){
            return new DeleteCommand(taskList, arguments);
        }
        if (command.equals(InputType.DONE.getInputType()) ) {
            return new MarkAsDoneCommand(taskList, arguments);
        }
        if(command.equals(InputType.HIGH.getInputType())) {
            return new PriorityCommand(taskList, arguments);
        }
        if (command.equals(InputType.FIND.getInputType())) {
            return new FindCommand(taskList, arguments);
        }
        return new EmptyCommand(taskList);
    }
}