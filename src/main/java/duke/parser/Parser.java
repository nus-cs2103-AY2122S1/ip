package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>Parser</code> class contains main bot logic to parse user commands
 * Has a ui attribute to print out specific messages according to user command.
 * parseCommand(input,list) is the sole method of the class.
 */

public class Parser{

    private final String FILEPATH = "./duke.txt";
    private final Storage storage = new Storage(FILEPATH);

    private TaskList tasks = new TaskList(storage.load());

    public Parser(){
    }

    private String sayBye(){
        return "Bye! *Poof!*";
    }

    private String help(){

        String dialog = "I'm Mr Meeseeks look at me! :)\n"
                + "Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!\n"
                + "Type \"list\" to show all tasks so far\n"
                + "Type \"done\" to mark a task as done\n"
                + "Type \"find\" to search for a task by keywords\n"
                + "Type \"bye\" to exit";

        return dialog;
    }

    private String list(){
        String dialog = "Okay! Here's a list of your tasks so far:\n";
        for (int i = 0; i < tasks.getSize(); i++){
            dialog += tasks.getTask(i).toString() + "\n";
        }
        return dialog;
    }

    private String markTaskAsDone(String input){

        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to mark as done...";
        }

        try {
            int index = Integer.parseInt(input.substring(5));
            tasks.markDone(index-1);
            storage.save(tasks.getList());
            return "Can Do! I've marked the below task as done\n"
                + tasks.getTask(index-1).toString();
        } catch (Exception e) {
            return "Oops! Please enter a valid task number to mark as done\n"
                    + "from 1 to " + tasks.getSize();
        }

    }

    private String deleteTask(String input){
        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to delete...";
        }
        try {
            int index = Integer.parseInt(input.substring(8));
            String deletedTask = tasks.getTask(index-1).toString();
            tasks.delete(index-1);
            storage.save(tasks.getList());
            return "Okay Boom! The below task is gone\n" + deletedTask
                    + "\n There are now " + tasks.getSize() + " tasks";
        } catch (Exception e) {
            return "Oops! Please enter a valid task number to delete\n"
                    + "from 1 to " + tasks.getSize();
        }
    }

    private String makeToDo(String input){
        try {
            String description = input.substring(5);
            Task todo = new ToDo(description, false);
            tasks.add(todo);
            storage.save(tasks.getList());
            return "Can Do! I've created the todo task below:\n"
                    + todo.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please enter a description for the task";
        }
    }

    private String makeDeadline(String input){
        int endIndex = input.length()-1;

        try {
            String timeString = input.substring(endIndex-4);
            String dateString = input.substring(endIndex-15, endIndex-5);

            LocalTime time = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(timeString));
            LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));

            String description = input.substring(9, endIndex-16);

            Task deadline = new Deadline(description, date,time,false);
            tasks.add(deadline);
            storage.save(tasks.getList());
            return "Can Do! I've created the deadline task below:\n"
                    + deadline.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please follow the format:\n deadline (description) yyyy-mm-dd hh:ss\n"
                    + "Check if the date/time is valid and if there is any missing spaces/characters";
        }

    }

    private String makeEvent(String input){
        int endIndex = input.length()-1;

        try {
            String endTimeString = input.substring(endIndex-4);
            String startTimeString = input.substring(endIndex-10,endIndex-5);
            String dateString = input.substring(endIndex-21, endIndex-11);

            System.out.println(endTimeString);
            System.out.println(startTimeString);
            System.out.println(dateString);

            LocalTime endTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(endTimeString));
            LocalTime startTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(startTimeString));
            LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));

            String description = input.substring(6, endIndex-22);

            Task event = new Event(description, date, startTime, endTime,false);
            tasks.add(event);
            storage.save(tasks.getList());
            return "Can Do! I've created the event task below:\n"
                    + event.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please follow the format:\n event (description) yyyy-mm-dd hh:ss hh:ss\n"
                    + "Check if the date/time is valid and if there is any missing spaces/characters";
        }
    }

    private String findTask(String input){
        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to search for...";
        }

        String target = input.substring(5);
        String output = "Ok! Here are the tasks that match your query: " + target + "\n";

        for (int i = 0; i < tasks.getSize(); i++){
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(target)){
                output += task.toString();
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Takes a user input command string and processes it
     * Updates the referenced input TaskList where applicable
     * Prints invalidInput if input is not a recognised command
     *
     * @param input input String which corresponds to a user command
     */

    public String parseCommand(String input) {

        if (input.equals("bye")) {
            return sayBye();
        } else if (input.equals("help")||input.equals("start")) {
            return help();
        } else if (input.equals("list")) {
            return list();
        } else if (input.startsWith("done")) {
            return markTaskAsDone(input);
        } else if (input.startsWith("delete")) {
            return deleteTask(input);
        } else if (input.startsWith("todo")) {
            return makeToDo(input);
        } else if (input.startsWith("deadline")) {
            return makeDeadline(input);
        } else if (input.startsWith("event")) {
            return makeEvent(input);
        } else if (input.startsWith("find")) {
            return findTask(input);
        } else {
            return "Oops! I didn't recognise that command!\n"
                + "Type \"help\" for a list of commands\n"
                + "Make sure to check for spaces and invalid characters/case";
        }

    }
}
