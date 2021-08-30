package Duke.Command;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.IndexNotInListException;
import Duke.Exceptions.NoDescriptionException;
import Duke.Exceptions.WrongInputException;

import Duke.Storage.Storage;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;
import Duke.Tasks.Todo;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    private static boolean exit;

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

    public static class ListCommand extends Command {

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showTasklist(tasklist);
        }

    }

    public static class ClearCommand extends Command {

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            tasklist.deleteAll();
            ui.showTasklistDeleted();
            storage.updateTxtFile(tasklist);
        }
    }

    public static class FindCommand extends Command {
        String input;

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
        String input;

        public DoneCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage)
                throws IndexNotInListException, WrongInputException, NoDescriptionException {
            if (input.trim().toLowerCase().equals("done")) {
                String message = "You say done but neh tell me do which one?!";
                throw new NoDescriptionException(message);
            } else {
                try {
                    int index = Integer.parseInt(input.split(" ")[1].trim());
                    Task task = tasklist.complete(index-1);
                    ui.showTaskCompleted(task, tasklist);
                    storage.updateTxtFile(tasklist);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Not a valid number leh!");
                }
            }
        }
    }

    public static class TodoCommand extends Command {
        String input;

        public TodoCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException{
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

    public static class DeadlineCommand extends Command{
        String input;

        public DeadlineCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException, WrongInputException {
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
                    deadline.setType('D');
                } else {
                    deadline = Deadline.createDeadline(description, "");
                    deadline.setType('D');
                }
                tasklist.insert(deadline);
                storage.updateTxtFile(tasklist);
                ui.showTaskAdded(deadline, tasklist);
            }
        }
    }

    public static class EventCommand extends Command{
        String input;

        public EventCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) throws NoDescriptionException, WrongInputException {
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

    public static class DeleteCommand extends Command{
        String input;

        public DeleteCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage)
                throws IndexNotInListException, NoDescriptionException, WrongInputException {
            if (input.trim().toLowerCase().equals("delete")) {
                String message = "You say want delete but neh tell me do which one?!";
                throw new NoDescriptionException(message);
            } else {
                try {
                    int index = Integer.parseInt(input.split(" ")[1].trim());
                    Task task = tasklist.delete(index-1);
                    ui.showTaskDeleted(task, tasklist);
                    storage.updateTxtFile(tasklist);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Not a valid number leh!");
                }
            }
        }
    }

    public static class NonsenseCommand extends Command {
        String input;

        public NonsenseCommand(String input) {
            this.input = input;
        }

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showNonsenseMessage(input);
        }
    }

    public static class ExitCommand extends Command {

        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage) {
            ui.showExitMessage();
            exit = true;
        }
    }

    public boolean isExit() {
        return exit;
    }
}
