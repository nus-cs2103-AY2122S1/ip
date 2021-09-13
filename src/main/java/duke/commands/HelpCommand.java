package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * when the user requests for help.
 */
public class HelpCommand extends Command{

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
//        System.out.println("Here are the different commands in Duke");
//        System.out.println("Type 'bye' to exit.");
//        System.out.println("Type 'list' to view all tasks in your list.");
//        System.out.println("Type 'delete x' to delete task number x. Eg. 'delete 5'");
//        System.out.println("Type 'done x' to mark task number x as done. Eg. 'done 5'");
//        System.out.println("Type 'find wordtofind' to search for tasks that contain the word");
//        System.out.println("Eg. 'find apple' ");
//
//        System.out.println("There are 3 types of tasks: Todo, Event, and Deadline");
//        System.out.println("To add a Todo task, type 'todo' followed by your task description");
//        System.out.println("Eg. 'todo read book' ");
//        System.out.println("To add a Event task, type 'event' followed by your task description, " +
//                "followed by '/at' followed by your event date");
//        System.out.println("Eg. 'event finance paper /at 2/12/2019 1800' ");
//        System.out.println("To add a Deadline task, type 'event' followed by your task description, " +
//                "followed by '/by' followed by your deadline ");
//        System.out.println("Eg. 'deadline return book /by 2/12/2019 1800' ");
//
//        System.out.println("Type 'help' to see the list of commands available");
//
        ui.printResponse("Here are the different commands in Duke");
        ui.printResponse("Type 'bye' to exit.");
        ui.printResponse("Type 'list' to view all tasks in your list.");
        ui.printResponse("Type 'delete x' to delete task number x. Eg. 'delete 5'");
        ui.printResponse("Type 'done x' to mark task number x as done. Eg. 'done 5'");
        ui.printResponse("Type 'find wordtofind' to search for tasks that contain the word");
        ui.printResponse("Eg. 'find apple' ");

        ui.printResponse("There are 3 types of tasks: Todo, Event, and Deadline");
        ui.printResponse("To add a Todo task, type 'todo' followed by your task description");
        ui.printResponse("Eg. 'todo read book' ");
        ui.printResponse("To add a Event task, type 'event' followed by your task description, " +
                "followed by '/at' followed by your event date");
        ui.printResponse("Eg. 'event finance paper /at 2/12/2019 1800' ");
        ui.printResponse("To add a Deadline task, type 'event' followed by your task description, " +
                "followed by '/by' followed by your deadline ");
        ui.printResponse("Eg. 'deadline return book /by 2/12/2019 1800' ");

        ui.printResponse("Type 'help' to see the list of commands available");



    }

    @Override
    public boolean isExit(){
        return false;
    }
}
