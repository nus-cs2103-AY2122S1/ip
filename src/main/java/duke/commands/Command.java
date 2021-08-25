package duke.commands;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.HashMap;

public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExitCommand() {
        return isExit;
    }
    public void setExitTrue() {
        isExit = true;
    }
    public static class ExitCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showByeMessage();
            super.setExitTrue();
        }
    }
    public static class ListAllCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGettingAllTaskItemsInteraction(tasks);
        }
    }
    public static class DoneCommand extends Command {
        private final int taskNum;
        public DoneCommand(int taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.markTaskAsDone(this.taskNum);
            storage.markPersistedTaskAsDone(this.taskNum, tasks.getTask(this.taskNum));
            ui.showTaskDoneInteraction(tasks.getTask(this.taskNum));
        }
    }
    public static class ToDoCommand extends Command {
        private final String description;
        public ToDoCommand(String description) {
            this.description = description;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ToDos newToDo = new ToDos(this.description, false);
            tasks.addTask(newToDo);
            ui.showTaskAddedInteraction(newToDo, tasks);
            storage.addTaskToPersistedData(newToDo);
        }
    }
    public static class DeadlineCommand extends Command {
        private final String description;
        private final String dateTimeBy;
        public DeadlineCommand(String description, String dateTimeBy) {
            this.description = description;
            this.dateTimeBy = dateTimeBy;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Deadlines newDeadline = new Deadlines(description, dateTimeBy, false);
            tasks.addTask(newDeadline);
            ui.showTaskAddedInteraction(newDeadline, tasks);
            storage.addTaskToPersistedData(newDeadline);
        }
    }
    public static class EventCommand extends Command {
        private final String description;
        private final String dateTimeAt;
        public EventCommand(String description, String dateTimeAt) {
            this.description = description;
            this.dateTimeAt = dateTimeAt;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Events newEvent = new Events(description, dateTimeAt, false);
            tasks.addTask(newEvent);
            ui.showTaskAddedInteraction(newEvent, tasks);
            storage.addTaskToPersistedData(newEvent);
        }
    }
    public static class DeleteCommand extends Command {
        private final int taskNum;
        public DeleteCommand(int taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task taskToDelete = tasks.getTask(this.taskNum);
            tasks.deleteTask(this.taskNum);
            storage.removePersistedTask(this.taskNum);
            ui.showTaskDeletedInteraction(taskToDelete, tasks);
        }
    }

    public static class FindCommand extends Command {
        private final String keyword;

        public FindCommand(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            HashMap<String, Task> matchingTasks = tasks.getMatchingTasks(this.keyword);
            ui.showMessagePrintingAllMatchingTasks(matchingTasks, tasks);
        }
    }
}
