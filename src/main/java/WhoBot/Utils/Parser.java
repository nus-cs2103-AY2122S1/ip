package WhoBot.Utils;

import WhoBot.Main.WhoBotException;
import WhoBot.Main.UI;

import java.util.Locale;

public class Parser {

    public int parse(String command, UI ui, Storage storage, TaskList taskList) throws WhoBotException {

        Helper helper = new Helper(ui);

        if (command.isBlank()) {
            throw new WhoBotException("The input is blank. Please enter something.");
        }

        String[] commandList = command.toLowerCase(Locale.ROOT).split(" ");
        //All commands are taken as case-insensitive
        if (command.toLowerCase(Locale.ROOT).equals("bye") || command.toLowerCase(Locale.ROOT).equals("goodbye")) {
            // If input is bye or goodbye, quits program
            ui.goodbye();
            storage.saveMemory(taskList.getLIST());
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
            storage.saveMemory(taskList.getLIST());
        } else if (commandList.length == 2 && commandList[0].equals("undo")) {
            //If input starts with undo, mark the specific item in list as not done
            taskList.markAsUndone(commandList[1], ui);
            storage.saveMemory(taskList.getLIST());
        } else if (commandList.length == 2 && commandList[0].equals("delete")) {
            //If input starts with delete, delete the specific item in list
            taskList.deleteFromList(commandList[1], ui);
            storage.saveMemory(taskList.getLIST());
        } else if (commandList[0].equals("todo")) {
            //If input starts with todos, add that to list
            taskList.addTODO(command, ui);
            storage.saveMemory(taskList.getLIST());
        } else if (commandList[0].equals("event")) {
            //If input starts with todos, add that to list
            taskList.addEvent(command, ui);
            storage.saveMemory(taskList.getLIST());
        } else if (commandList[0].equals("deadline")) {
            //If input starts with todos, add that to list
            taskList.addDeadline(command, ui);
            storage.saveMemory(taskList.getLIST());
        } else if (commandList[0].equals("show")) {
            //If input starts with todos, add that to list
            taskList.showOnDate(command, ui);
        } else if (commandList[0].equals("find")) {
            //If input starts with todos, add that to list
            taskList.findTask(command, ui);
        } else {
            // Else Invalid
            throw new WhoBotException("Oops, That's an invalid command. Type in help to get list of possible commands.");
        }
        return 0;
    }
}
