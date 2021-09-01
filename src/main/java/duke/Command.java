package duke;

import java.util.ArrayList;

/**
 * Deals with the Command given by user and implement the corresponding actions.
 */
public abstract class Command {
    /**
     * Abstract method meant to indicate the actions taken for different command.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui The Ui of Duke.
     * @param storage The Storage of Duke.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns if the current command is an exit command.
     *
     * @return If current command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Handles the adding of new task into Duke.
     */
    public static class AddCommand extends Command {
        /** Contains the String representation of task description */
        private String input;
        /** Contains the int representation of task type */
        private int taskType;

        /**
         * Constructor for the AddCommand.
         *
         * @param input The String representation of task description.
         * @param type The type of task.
         */
        public AddCommand(String input, int type) {
            this.taskType = type;
            this.input = input;
        }

        /**
         * Adds the corresponding task into Duke.
         *  @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         * @return
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                TaskList.Task task = taskList.add(input, taskType);

                return ui.displayAddUi(taskList, task);
            } catch (DukeException e) {
                return ui.displayDukeExceptionMessage(e);
            }
        }
    }

    /**
     * Handles marking task as done in Duke.
     */
    public static class DoneCommand extends Command {
        /** The task number that is to be marked as done */
        private int taskNumber;

        /**
         * Constructor for the DoneCommand.
         *
         * @param taskNumber The task number that is to be marked as done.
         */
        public DoneCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        /**
         * Marks the specified task as done.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                return ui.displayDoneUi(taskList.done(taskNumber));
            } catch (DukeException e) {
                return ui.displayDukeExceptionMessage(e);
            }
        }
    }

    /**
     * Handles deleting specific task from Duke.
     */
    public static class DeleteCommand extends Command {
        /** The task number that is to be deleted */
        private int taskNumber;

        /**
         * Constructor for the DeleteCommand.
         *
         * @param taskNumber The task number that is to be deleted.
         */
        public DeleteCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        /**
         * Deletes the specified task.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            try {
                return ui.displayDeleteUi(taskList, taskList.delete(taskNumber));
            } catch (DukeException e) {
                return ui.displayDukeExceptionMessage(e);
            }
        }
    }

    /**
     * Handles displaying the task recorded in Duke.
     */
    public static class ListCommand extends Command {
        /**
         * Constructor for the ListCommand.
         */
        public ListCommand() {};

        /**
         * Displays the recorded tasks in Duke.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            return ui.displayListUi(taskList);
        }
    }

    /**
     * Handles closing and exiting Duke.
     */
    public static class ExitCommand extends Command {
        /**
         * Constructor for the ExitCommand.
         */
        public ExitCommand() {};

        /**
         * Closes and exits Duke.
         * Saving the tasks in Duke to Storage.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            storage.write(taskList);

            return ui.displayExitUi();
        }

        /**
         * Returns true as an exit command.
         *
         * @return True.
         */
        @Override
        public boolean isExit() {
            return true;
        }
    }


    /**
     * Handles when Duke do not understand the command given.
     */
    public static class UnknownCommand extends Command {
        /**
         * Constructor for the UnknownCommand.
         */
        public UnknownCommand() {};

        /**
         * Informs user that command given is not understood.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            return ui.displayUnknownUi();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (obj instanceof UnknownCommand) {
                return true;
            }

            return false;
        }
    }

    /**
     * Handles finding keyword in task description of task in TaskList.
     */
    public static class FindCommand extends Command {
        /** The list that tasks with keyword are added to */
        private ArrayList<TaskList.Task> relatedList;
        /** The keyword used to find tasks */
        private String keyword;

        /**
         * Constructor for the FindCommand.
         *
         * @param keyword The keyword used to find tasks.
         */
        public FindCommand(String keyword) {
            this.relatedList = new ArrayList<>();
            this.keyword = keyword;
        }

        /**
         * Finds tasks with keyword and add them into a list.
         *
         * @param taskList The TaskList that is saved in Duke.
         * @param ui The Ui of Duke.
         * @param storage The Storage of Duke.
         */
        @Override
        public String execute(TaskList taskList, Ui ui, Storage storage) {
            taskList.find(relatedList, keyword);
            return ui.displayFindUi(relatedList);
        }
    }
}

