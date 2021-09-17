package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

import java.util.ArrayList;

/**Command which tags task*/
public class TagCommand extends Command {
    private ArrayList<Integer> indexes;
    private ArrayList<String> tags;

    public static final String syntax = "tag {index of task1} {index of task2} {...}| {tag1} {tag2} {...}";
    public static final String inputExample = "Eg. 1 2 | tag hell pain";

    /**
     * Constructor for tag command.
     *
     * @param keywords tags and indexes for tasks.
     * @throws DukeException if input is invalid.
     */
    public TagCommand(String ...keywords) throws DukeException {
        indexes = new ArrayList<Integer>();
        tags = new ArrayList<String>();
        addKeywordsAndIndexes(keywords);
    }

    /**
     * Returns help message for tagging.
     *
     * @return help message for tagging.
     */
    public static String getHelpMessage() {
        return "To tag a task type\n" + syntax + "\n" + inputExample;
    }

    private void addKeywordsAndIndexes(String[] keywords) throws DukeException {
        boolean isTags = false;
        for (String keyword: keywords) {
            if (!isTags && keyword.equals("|")) {
                isTags = true;
                continue;
            }
            if (!isTags) {
                try {
                    int parsedInteger = Integer.parseInt(keyword);
                    if (parsedInteger <= 0) {
                        throw new DukeException("Invalid input. Please enter a positive integer for the index");
                    }
                    indexes.add(parsedInteger);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid input. Please enter a integer for the index");
                }
            } else {
                tags.add(keyword);
            }
        }
        if (tags.size() == 0) {
            throw new DukeException("Invalid input. Please enter a tag after |");
        }
    }

    /**
     * Tags tasks with tags.
     *
     * @param tasks the current list of tasks.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     * @return Ui's message indicating tagging is done.
     * @throws DukeException if input is invalid.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        Tasklist taggedTasks = tasks.addTags(this.indexes, this.tags);
        fileManager.updateTaskList(tasks, ui);
        return ui.showTagsAdded(taggedTasks, this.tags);
    }

    /**
     * Returns if this command is exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
