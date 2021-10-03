package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        return helpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String helpMessage() {
        String helpMessage = "Hi, how can I help you? Here are some of the available commands which you can use.\n"
                + "1. Adding a ToDo Task: todo <task>\n"
                + "2. Scheduling a Task on a fixed time: scheduledtask <task> /on <date> /from <start time> /to <end time>\n"
                + "3. Adding a TimedTask Task: timedtask <task> /needs <amount of time>\n"
                + "4. Adding tags: tag <task id> <your tags>\n" + "5. Listing all the tasks: list\n"
                + "6. Viewing all scheduled task: viewschedule\n" + "7. Completing a Task: done <task id>\n"
                + "8. Deleting a Task: delete <task id>\n" + "9. Exiting the program: bye\n"
                + "Do visit https://github.com/rickyaandrew/ip/blob/master/README.md for more commands.";
        return helpMessage;
    }

}
