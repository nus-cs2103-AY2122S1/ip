package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalTaskException;

public class Parser {
    
    public static Command parse(String fullCommand) throws IllegalCommandException, IllegalTaskException {
        String command = fullCommand.split(" ")[0];
        if (command.equals("list")){
            return new ListCommand();
        } else if (command.equals("done")) {
            int toComplete = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DoneCommand(toComplete);
        } else if (command.equals("delete")) {
            int toDelete = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(toDelete);
        } else if (command.equals("todo")) {
            String task = fullCommand.replaceFirst("todo ","");
            if (task.equals("todo")) {
                throw new IllegalTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddCommand(new ToDo(task));
            }
        } else if (command.equals("deadline")) {
            String[] taskDate = fullCommand.replaceFirst("deadline ", "").split("/by ");
            String task = taskDate[0];
            String date = taskDate[1];
            String[] splitDateTime = date.split(" ");
            String[] splitDate = splitDateTime[0].split("/");
            LocalDate localDate;
            if (splitDate[1].length() == 1){
                localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
            } else if (splitDate[0].length() == 1){
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
            } else { 
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
            }
            LocalTime localTime;
            localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new AddCommand(new Deadline(task, localDate, localTime)); 
        } else if (command.equals("event")) {
            String[] taskDate = fullCommand.replaceFirst("event ", "").split("/at ");
            String task = taskDate[0];
            String date = taskDate[1];
            String[] splitDateTime = date.split(" ");
            String[] splitDate = splitDateTime[0].split("/");
            LocalDate localDate;
            if (splitDate[1].length() == 1){
                localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
            } else if (splitDate[0].length() == 1){
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
            } else { 
                localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
            }
            LocalTime localTime;
            localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            return new AddCommand(new Event(task, localDate, localTime));
        } else if (command.equals("bye")){
            return new ExitCommand();
        } else {
            throw new IllegalCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}