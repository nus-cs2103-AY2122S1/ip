package middleware;

import model.EventTask;
import model.ToDoTask;
import parser.ParsedInput;
import ui.JadenGui;
import model.DeadlineTask;
import model.TaskList;

public class Middleman {
    private TaskList taskList;
    private JadenGui ui;

    public Middleman(TaskList taskList, JadenGui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }
    
    public String processParsedInput(ParsedInput parsedInput) {
        String outputString;
        switch (parsedInput.commandType) {
            case TODO:
                outputString = this.taskList.addTask(new ToDoTask(parsedInput.taskDescription));
                break;
            case DEADLINE:
                outputString = this.taskList.addTask(new DeadlineTask(parsedInput.taskDescription, parsedInput.deadline));
                break;
            case EVENT:
                outputString = this.taskList.addTask(new EventTask(parsedInput.taskDescription, parsedInput.eventPeriod));
                break;
            case LIST:
                outputString = this.taskList.listTasks();
                break;
            case DONE:
                outputString = this.taskList.markAsDone(parsedInput.taskIndex);
                break;
            case DELETE:
                outputString = this.taskList.removeTask(parsedInput.taskIndex, true);
                break;
            case FIND:
                outputString = this.taskList.findTasks(parsedInput.taskDescription);
                break;
            case BYE:
                outputString = "Bye.";
                break;
            default:
                outputString = "Invalid";
        }
        return outputString;
    }
}
