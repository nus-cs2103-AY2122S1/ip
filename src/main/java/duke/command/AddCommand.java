package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Parser;
import duke.Storable;
import duke.Ui;
import duke.Ui.Commands;
import duke.Ui.Descriptors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;

public class AddCommand extends Command {
    private String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }


    private void addTask(TaskList tasks, Ui ui, Character separator) throws DukeException {
        // Checks for command given in user input.
        String userCommand;
        if (this.userInput.startsWith(Commands.TODO.getCommand())) {
            userCommand = Commands.TODO.getCommand();
        } else if (this.userInput.startsWith(Commands.DEADLINE.getCommand())) {
            userCommand = Commands.DEADLINE.getCommand();
        } else if (this.userInput.startsWith(Commands.EVENT.getCommand())) {
            userCommand = Commands.EVENT.getCommand();
        } else {
            throw new DukeException(Ui.exceptionInvalidUserCommand());
        }

        // Checks if user input contains a task description.
        if (this.userInput.length() <= (userCommand.length() + 1)) {
            // Check for case where there is a single non-space character right after command.
            // User might have intended for this to be a description.
            // Missing spaces error message is more appropriate in this case.
            if (this.userInput.length() == (userCommand.length() + 1) &&
                    this.userInput.charAt(userCommand.length() - 1) != ' ') {
                throw new DukeException(Ui.exceptionMissingSpaceAfterCommand(userCommand));
            } else {
                throw new DukeException(Ui.exceptionMissingTaskDescription(userCommand));
            }
        }

        // Checks if user input contains a space after the command.
        if (this.userInput.charAt(userCommand.length()) != ' ') {
            throw new DukeException(Ui.exceptionMissingSpaceAfterCommand(userCommand));
        }

        // Extracts task description.
        String description = this.userInput.substring(userCommand.length() + 1);

        // Parses description and adds the corresponding task to tasks.
        if (userCommand.equals(Commands.TODO.getCommand())) {
            // Adds to-do task to tasks.
            tasks.add(new Todo(description));
        } else if (userCommand.equals(Commands.DEADLINE.getCommand())) {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.BY, separator, Commands.DEADLINE);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds duke.task.Deadline task to tasks.
            tasks.add(new Deadline(descriptions[0], localDate));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.AT, separator, Commands.EVENT);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds duke.task.Event task to tasks.
            tasks.add(new Event(descriptions[0], localDate));
        }

        // Prints response to user after successfully adding task to tasks.
        ui.showAddSuccess(tasks.get(tasks.size() - 1), tasks.size());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Add task according to user specifications.
            this.addTask(tasks, ui, '/');

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
