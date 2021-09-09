package whobot.utils;

import java.util.Locale;

import whobot.main.UI;
import whobot.main.WhoBot;
import whobot.main.WhoBotException;

/***
 * Class to Help Parse Input Commands
 */
public class Parser {

    /***
     * Parses the Given Command and does this appropriate action
     * Return -1 if given the quitting command else 0.
     *
     * @param command String command to parse.
     * @param ui UI to give outputs to.
     * @param storage Storage to save any changes to list of made.
     * @param taskList TaskList to take action based on command.
     *
     * @return -1 if end else 0.
     *
     * @throws WhoBotException If any issues with the commands.
     */
    public int parse(String command, UI ui, Storage storage, TaskList taskList) throws WhoBotException {

        assert ui != null;
        assert storage != null;
        assert taskList != null;

        Helper helper = new Helper(ui);

        if (command.isBlank()) {
            throw new WhoBotException("The input is blank. Please enter something.");
        }

        String[] commandList = command.toLowerCase(Locale.ROOT).split(" ");
        //All commands are taken as case-insensitive
        if (command.toLowerCase(Locale.ROOT).equals("bye") || command.toLowerCase(Locale.ROOT).equals("goodbye")) {
            // If input is bye or goodbye, quits program
            if (!WhoBot.isGui()) {
                ui.goodbye();
            }
            storage.saveMemory(taskList.getList());
            return -1;
        } else if (command.toLowerCase(Locale.ROOT).equals("list")) {
            // If input is list, prints list
            taskList.printList(ui);
        } else if (commandList[0].equals("help")) {
            // If input is list, prints list
            if (commandList.length == 1) {
                helper.showMainHelp();
            } else {
                helper.showCommandHelp(commandList[1]);
            }
        } else if (commandList.length == 2 && commandList[0].equals("done")) {
            //If input starts with done, mark the specific item in list as done
            taskList.markAsDone(commandList[1], ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList.length == 2 && commandList[0].equals("undo")) {
            //If input starts with undo, mark the specific item in list as not done
            taskList.markAsUndone(commandList[1], ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList.length == 2 && commandList[0].equals("delete")) {
            //If input starts with delete, delete the specific item in list
            taskList.deleteFromList(commandList[1], ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("todo")) {
            //If input starts with todos, add that to list
            taskList.addTodo(command, ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("event")) {
            //If input starts with event, add that to list
            taskList.addEvent(command, ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("deadline")) {
            //If input starts with deadline, add that to list
            taskList.addDeadline(command, ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("show")) {
            //If input starts with show, show tasks on specified date
            taskList.showOnDate(command, ui);
        } else if (commandList[0].equals("find")) {
            //If input starts with todos, add that to list
            taskList.findTask(command, ui);
        } else if (commandList[0].equals("tag")) {
            //If input starts with todos, add that to list
            taskList.tagTask(command, ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("untag")) {
            //If input starts with todos, add that to list
            taskList.untagTask(command, ui);
            storage.saveMemory(taskList.getList());
        } else if (commandList[0].equals("list")) {
            //If input starts with todos, add that to list
            taskList.listTagTask(command, ui);
            storage.saveMemory(taskList.getList());
        } else {
            // Else Invalid
            throw new WhoBotException("Oops, That's an invalid command."
                    + " Type in help to get list of possible commands.");
        }
        return 0;
    }
}
