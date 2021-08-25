package duke.commands;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes this command accordingly. The actual implementation must be defined by
     * the subclass.
     *
     * @param tasks the full task list containing all the tasks
     * @param ui the ui instance
     * @param storage the storage instance
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks whether this command is an ExitCommand
     *
     * @return true if it is an exit command, else false is returned
     */
    public boolean isExitCommand() {
        return isExit;
    }
    private void setExitTrue() {
        isExit = true;
    }

    /**
     * The command indicating that the user wants to shut down the chat-bot
     */
    public static class ExitCommand extends Command {
        /**
         * Performs the required actions that will print a bye message and terminate the program
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showByeMessage();
            super.setExitTrue();
        }
    }

    /**
     * The command indicating that the user wants to list all the tasks within the
     * task list
     */
    public static class ListAllCommand extends Command {
        /**
         * Performs the necessary method invoking to print all the tasks within the task list
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGettingAllTaskItemsInteraction(tasks);
        }
    }

    /**
     * The command indicating that the user wants to mark a task as done
     */
    public static class DoneCommand extends Command {
        private int taskNum;

        /**
         * Constructor for DoneCommand
         *
         * @param taskNum the task number whose corresponding task you want to mark as done
         */
        public DoneCommand(int taskNum) {
            this.taskNum = taskNum;
        }

        /**
         * Performs the necessary actions that will mark the given task as done, as well
         * as update the persisted data text file
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.markTaskAsDone(this.taskNum);
            storage.markPersistedTaskAsDone(this.taskNum, tasks.getTask(this.taskNum));
            ui.showTaskDoneInteraction(tasks.getTask(this.taskNum));
        }
    }

    /**
     * The command indicating that the user wants to add in a ToDos into the task list
     */
    public static class ToDoCommand extends Command {
        private String description;

        /**
         * Constructor for ToDoCommand
         *
         * @param description the description of the ToDos to be added
         */
        public ToDoCommand(String description) {
            this.description = description;
        }

        /**
         * Performs the actions that adds the ToDos to the task list
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ToDos newToDo = new ToDos(this.description, false);
            tasks.addTask(newToDo);
            ui.showTaskAddedInteraction(newToDo, tasks);
            storage.addTaskToPersistedData(newToDo);
        }
    }

    /**
     * The command indicating that the user wants to add in a Deadline into the task list
     */
    public static class DeadlineCommand extends Command {
        private String description;
        private String dateTimeBy;

        /**
         * Constructor for DeadlineCommand
         *
         * @param description the description of the Deadline to be added
         * @param dateTimeBy the date where this deadline is due
         */
        public DeadlineCommand(String description, String dateTimeBy) {
            this.description = description;
            this.dateTimeBy = dateTimeBy;
        }

        /**
         * Performs the actions that adds the Deadline to the task list
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Deadlines newDeadline = new Deadlines(description, dateTimeBy, false);
            tasks.addTask(newDeadline);
            ui.showTaskAddedInteraction(newDeadline, tasks);
            storage.addTaskToPersistedData(newDeadline);
        }
    }

    /**
     * The command indicating that the user wants to add in a event into the task list
     */
    public static class EventCommand extends Command {
        private String description;
        private String dateTimeAt;

        /**
         * Constructor for EventCommand
         *
         * @param description the description of the Deadline to be added
         * @param dateTimeAt the time/place/period where this event will occur
         */
        public EventCommand(String description, String dateTimeAt) {
            this.description = description;
            this.dateTimeAt = dateTimeAt;
        }

        /**
         * Performs the actions that adds the event to the task list
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Events newEvent = new Events(description, dateTimeAt, false);
            tasks.addTask(newEvent);
            ui.showTaskAddedInteraction(newEvent, tasks);
            storage.addTaskToPersistedData(newEvent);
        }
    }

    /**
     * The command indicating that the user wants to delete a task from the task list
     */
    public static class DeleteCommand extends Command {
        private int taskNum;

        /**
         * Constructor for DeleteCommand
         *
         * @param taskNum the task number of the task that you want to delete
         */
        public DeleteCommand(int taskNum) {
            this.taskNum = taskNum;
        }

        /**
         * Performs the actions that adds the deletes the task from the task list
         *
         * @param tasks the full task list containing all the tasks
         * @param ui the ui instance
         * @param storage the storage instance
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task taskToDelete = tasks.getTask(this.taskNum);
            tasks.deleteTask(this.taskNum);
            storage.removePersistedTask(this.taskNum);
            ui.showTaskDeletedInteraction(taskToDelete, tasks);
        }
    }
}
