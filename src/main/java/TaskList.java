import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deals with interactions with user.
 * Responds accordingly to user commands and inputs.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void markTask(String userInput) throws DukeException {
        if (userInput.length() <= (Ui.Commands.DONE.getLength() + 1)) {
            // Missing user input for index of task to be marked as done.
            throw new DukeException("An index must be provided to mark task at the index as done.");
        } else {
            // Parses integer in user input.
            int userNumInput = Parser.parseUserNumInput(userInput, Ui.Commands.DONE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= this.tasks.size() || idx < 0) {
                throw new DukeException("Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Marks task at index as done.
            this.tasks.get(idx).markAsDone();

            // Prints response to user after successfully marking task at index as done.
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.tasks.get(idx).toString());
        }
    }

    public void deleteTask(String userInput) throws DukeException {
        if (userInput.length() <= (Ui.Commands.DELETE.getLength() + 1)) {
            // Missing user input for index of task to be deleted.
            throw new DukeException("An index must be provided to delete task at index.");
        } else {
            // Parses integer in user input. 1 space is accounted for as it separates command and index.
            int userNumInput = Parser.parseUserNumInput(userInput, Ui.Commands.DELETE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= this.tasks.size() || idx < 0) {
                throw new DukeException("Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Assigns task to be deleted to tempTask for printing.
            Task tempTask = this.tasks.get(idx);

            // Deletes task at index.
            this.tasks.remove(idx);

            // Prints response to user after successfully deleting task at index.
            System.out.println("Noted. I've removed this task:");
            System.out.println(tempTask.toString());
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        }
    }

    public void addTask(String userInput, Character separator) throws DukeException {
        // Checks for command given in user input.
        String userCommand;
        if (userInput.startsWith(Ui.Commands.TODO.getCommand())) {
            userCommand = Ui.Commands.TODO.getCommand();
        } else if (userInput.startsWith(Ui.Commands.DEADLINE.getCommand())) {
            userCommand = Ui.Commands.DEADLINE.getCommand();
        } else if (userInput.startsWith(Ui.Commands.EVENT.getCommand())) {
            userCommand = Ui.Commands.EVENT.getCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        // Checks if user input contains a task description and obtain it if it exists.
        if (userInput.length() <= userCommand.length() + 1) {
            throw new DukeException("The description of " + userCommand + " cannot be empty.");
        }
        String description = userInput.substring(userCommand.length() + 1);

        // Parses description and adds the corresponding task to tasks.
        if (userCommand.equals(Ui.Commands.TODO.getCommand())) {
            // Adds to-do task to tasks.
            this.tasks.add(new Todo(description));
        } else if (userCommand.equals(Ui.Commands.DEADLINE.getCommand())) {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Ui.Descriptors.BY, separator, Ui.Commands.DEADLINE);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Deadline task to tasks.
            this.tasks.add(new Deadline(descriptions[0], localDate));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Ui.Descriptors.AT, separator, Ui.Commands.EVENT);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Event task to tasks.
            this.tasks.add(new Event(descriptions[0], localDate));
        }

        // Prints response to user after successfully adding task to tasks.
        System.out.println("Got it. I have added this task:");
        System.out.println(this.tasks.get(this.tasks.size() - 1).toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            // Increment i by 1 so number matches display indexing which starts from 1.
            int idx = i + 1;

            // Format should be "?. taskDescription\n"
            System.out.printf("%d.%s%n", idx, this.tasks.get(i).toString());
        }
    }

    public void printTaskAtDate(String userInput) throws DukeException {
        // Initialize counters to track number of tasks, events and deadlines.
        int counter = 0;
        int events = 0;
        int deadlines = 0;

        // Parses user input into LocalDate. User input for date will follow "date" command.
        String dateString = userInput.substring(Ui.Commands.DATE.getLength() + 1);
        LocalDate localDate = Parser.toLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        // Print to notify users of the date they are searching for.
        System.out.println("Here are the Deadlines or Events that fall on " + formattedDateString + ":");

        // Print Deadlines and Events with LocalDate that matches date input from user.
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.by)) {
                    counter++;
                    deadlines++;
                    System.out.println(counter + "." + deadline);
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.at)) {
                    counter++;
                    events++;
                    System.out.println(counter + "." + event);
                }
            }
        }

        // Print a summary of matching tasks to the user.
        System.out.println("A total of " + counter + " events (" + deadlines + " deadlines and " +
                events + " events) fall on " + formattedDateString);
    }
}
