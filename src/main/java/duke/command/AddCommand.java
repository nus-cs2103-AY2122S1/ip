package duke.command;

import duke.ArchiveList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the add command of Duke.
 */
public class AddCommand extends Command {

    private final Task toAdd;

    /**
     * Constructs a AddCommand object to add given task object.
     *
     * @param toAdd Task to add to taskList.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Checks if AddCommand is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the AddCommand.
     *
     * @param taskList TaskList object to add toAdd to.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui Object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        taskList.add(this.toAdd);

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        ui.printAdd(this.toAdd, taskList.getSize());
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the adding of the task.
     *
     * @param taskList TaskList object to add toAdd to.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui Object to get the String representation from.
     * @param storage Storage object which saves and loads the taskList.
     * @return String representation of the things printed in the execute method.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        taskList.add(this.toAdd);

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        return ui.formatPrintAddString(this.toAdd, taskList.getSize());
    }

    /**
     * Gets the deadline from an array of words.
     *
     * @param words Array which deadline will be derived from.
     * @return The deadline.
     */
    public static String getDeadline(String[] words) {
        String deadline = "";

        for (int i = 0; i < words.length; i++) {
            if (Parser.isValidDate(words[i], DateTimeFormatter.ISO_LOCAL_DATE)) {
                deadline += LocalDate.parse(words[i], DateTimeFormatter.ISO_LOCAL_DATE)
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else if (Parser.isValidTime(words[i], DateTimeFormatter.ISO_LOCAL_TIME)) {
                deadline += LocalTime.parse(words[i], DateTimeFormatter.ISO_LOCAL_TIME)
                        .format(DateTimeFormatter.ofPattern("hh:mm a"));
            } else {
                deadline += words[i];
            }

            if (!(i == 0 && words[i].equals("")) && i != words.length - 1) {
                deadline += SPACE;
            }
        }

        if (deadline.equals("")) {
            deadline += " ";
        }

        return deadline;
    }

    /**
     * Gets the description from an array of strings.
     *
     * @param words Array which description would be derived from.
     * @return The description.
     */
    public static String getDescription(String[] words) {
        String description = "";

        for (int i = 1; i < words.length; i++) {
            description += words[i];
            if (i != words.length - 1) {
                description += SPACE;
            }
        }

        return description;
    }
}
