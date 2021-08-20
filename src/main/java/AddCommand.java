import java.time.LocalDate;

public class AddCommand extends Command {
    private String userInput;

    AddCommand(String userInput) {
        this.userInput = userInput;
    }

    private void addTask(TaskList tasks, Ui ui, Character separator) throws DukeException {
        // Checks for command given in user input.
        String userCommand;
        if (this.userInput.startsWith(Ui.Commands.TODO.getCommand())) {
            userCommand = Ui.Commands.TODO.getCommand();
        } else if (this.userInput.startsWith(Ui.Commands.DEADLINE.getCommand())) {
            userCommand = Ui.Commands.DEADLINE.getCommand();
        } else if (this.userInput.startsWith(Ui.Commands.EVENT.getCommand())) {
            userCommand = Ui.Commands.EVENT.getCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        // Checks if user input contains a task description and obtain it if it exists.
        if (this.userInput.length() <= userCommand.length() + 1) {
            throw new DukeException("The description of " + userCommand + " cannot be empty.");
        }
        String description = this.userInput.substring(userCommand.length() + 1);

        // Parses description and adds the corresponding task to tasks.
        if (userCommand.equals(Ui.Commands.TODO.getCommand())) {
            // Adds to-do task to tasks.
            tasks.add(new Todo(description));
        } else if (userCommand.equals(Ui.Commands.DEADLINE.getCommand())) {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Ui.Descriptors.BY, separator, Ui.Commands.DEADLINE);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Deadline task to tasks.
            tasks.add(new Deadline(descriptions[0], localDate));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Ui.Descriptors.AT, separator, Ui.Commands.EVENT);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Event task to tasks.
            tasks.add(new Event(descriptions[0], localDate));
        }

        // Prints response to user after successfully adding task to tasks.
        ui.showAddSuccess(tasks.get(tasks.size() - 1), tasks.size());
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Add task according to user specifications.
            this.addTask(tasks, ui, '/');

            // Saves edited TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
