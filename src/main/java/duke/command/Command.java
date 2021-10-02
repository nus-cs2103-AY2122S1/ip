package duke.command;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.IndexNotInListException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.WrongInputException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * The Command class is responsible for directing the user's interaction with Duke.
 */
public abstract class Command {
    private static boolean isExit;

    /**
     * Executes the specified action that is expected of the command.
     *
     * @param tasklist Takes in a tasklist to update the list of tasks
     * @param ui Takes in an instance of the ui class to show details to the user
     * @param storage Takes in an instance of the storage to update and renew the txt files.
     * @throws DukeException If an error caused by Duke's limitation occurs
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    public static class SnoozeCommand extends Command {
        private final String input;

        public SnoozeCommand(String input) {
            this.input = input;
        }
        /**
         * Postpone the task by 1 week.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage)
                throws NoDescriptionException, WrongInputException, IndexNotInListException {
            if (input.trim().toLowerCase().equals("done")) {
                String message = "You say snooze but neh tell me snooze which one?!";
                throw new NoDescriptionException(message);
            } else {
                try {
                    int index = Integer.parseInt(input.split(" ")[1].trim());
                    Task task = tasklist.snooze(index - 1);
                    ui.showTaskSnoozed(task);
                    storage.updateTxtFile(tasklist);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Not a valid number leh!");
                }
            }
        }
    }

    public static class ListCommand extends Command {

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showTasklist(tasklist);
        }

    }

    public static class ClearCommand extends Command {

        /**
         * Executes the clearing of the task list.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            tasklist.deleteAll();
            ui.showTasklistDeleted();
            storage.updateTxtFile(tasklist);
        }
    }

    public static class FindCommand extends Command {
        private final String input;

        public FindCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException {
            if (input.trim().toLowerCase().equals("find")) {
                String message = "Oi, what you want me find?!";
                throw new NoDescriptionException(message);
            } else {
                String keyword = input.split(" ")[1].trim();
                ArrayList<Task> searches = tasklist.find(keyword);
                ui.showSearches(searches);
            }
        }
    }

    public static class DoneCommand extends Command {
        private final String input;

        public DoneCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the completing of a specific task and outputting the behaviour of duke.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage)
                throws IndexNotInListException, WrongInputException, NoDescriptionException {
            if (input.trim().toLowerCase().equals("done")) {
                String message = "You say done but neh tell me do which one?!";
                throw new NoDescriptionException(message);
            } else {
                try {
                    int index = Integer.parseInt(input.split(" ")[1].trim());
                    Task task = tasklist.complete(index - 1);
                    ui.showTaskCompleted(task, tasklist);
                    storage.updateTxtFile(tasklist);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Not a valid number leh!");
                }
            }
        }
    }

    public static class TodoCommand extends Command {
        private final String input;

        public TodoCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the TodoCommand, which creates a new Todo task and displaying the appropriate reaction from Duke.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException {
            if (input.trim().toLowerCase().equals("todo")) {
                String message = "Oi, description of your todo cannot be empty lah!";
                throw new NoDescriptionException(message);
            } else {
                String description = input.split(" ", 2)[1];
                Task todo = Todo.createTodo(description);
                todo.setType('T');
                tasklist.insert(todo);
                storage.updateTxtFile(tasklist);
                ui.showTaskAdded(todo, tasklist);
            }
        }
    }

    public static class DeadlineCommand extends Command {
        private final String input;

        public DeadlineCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the Deadline, which creates a new Deadline task and displaying the appropriate reaction from Duke.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException,
                WrongInputException {
            if (input.trim().toLowerCase().equals("deadline")) {
                String message = "Oi, description of your deadline cannot be empty lah!";
                throw new NoDescriptionException(message);
            } else {
                String description = input.split(" ", 2)[1];
                Task deadline;
                if (description.contains("/by")) {
                    String name = description.split("/by", 2)[0];
                    String date = description.split("/by", 2)[1];
                    deadline = Deadline.createDeadline(name, date);
                } else {
                    deadline = Deadline.createDeadline(description, "");
                }
                deadline.setType('D');
                tasklist.insert(deadline);
                storage.updateTxtFile(tasklist);
                ui.showTaskAdded(deadline, tasklist);
            }
        }
    }

    public static class EventCommand extends Command {
        private final String input;

        public EventCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the Event, which creates a new Event task and displaying the appropriate reaction from Duke.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException,
                WrongInputException {
            if (input.trim().toLowerCase().equals("event")) {
                String message = "Oi, description of your event cannot be empty lah!";
                throw new NoDescriptionException(message);
            } else {
                String description = input.split(" ", 2)[1];
                Task event;
                if (description.contains("/at")) {
                    String name = description.split("/at", 2)[0];
                    String date = description.split("/at", 2)[1];
                    event = Event.createEvent(name, date);
                } else {
                    event = Event.createEvent(description, "");
                }
                event.setType('E');
                tasklist.insert(event);
                storage.updateTxtFile(tasklist);
                ui.showTaskAdded(event, tasklist);
            }
        }
    }

    public static class DeleteCommand extends Command {
        private final String input;

        public DeleteCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the DeleteCommand, which deletes a specific task from the task list.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage)
                throws IndexNotInListException, NoDescriptionException, WrongInputException {
            if (input.trim().toLowerCase().equals("delete")) {
                String message = "You say want delete but neh tell me do which one?!";
                throw new NoDescriptionException(message);
            } else {
                try {
                    int index = Integer.parseInt(input.split(" ")[1].trim());
                    Task task = tasklist.delete(index - 1);
                    ui.showTaskDeleted(task, tasklist);
                    storage.updateTxtFile(tasklist);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Not a valid number leh!");
                }
            }
        }
    }

    public static class NonsenseCommand extends Command {
        private final String input;

        public NonsenseCommand(String input) {
            this.input = input;
        }

        /**
         * Executes the NonsenseCommand, which only occurs if duke does not understand what the user typed.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showNonsenseMessage(input);
        }
    }

    public static class ExitCommand extends Command {

        /**
         * Executes theExitCommand, which will cause Duke to terminate immediately.
         *
         * @param tasklist Takes in a tasklist to update the list of tasks
         * @param ui Takes in an instance of the ui class to show details to the user
         * @param storage Takes in an instance of the storage to update and renew the txt files.
         */
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showExitMessage();
            isExit = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }

    /**
     * Returns whether the programme should terminate or not
     *
     * @return the boolean variable
     */
    public boolean isExit() {
        return isExit;
    }
}
