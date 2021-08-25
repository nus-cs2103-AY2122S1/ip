import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    private DateTimeFormatter formatFromInput = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public AddTaskCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput[0].equals("todo")) {
            if (parsedUserInput.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTask = new ToDo(parsedUserInput[1]);
            addTaskToList(newTask, ui, tasks, storage);
        } else if (parsedUserInput[0].equals("deadline")) { // Add deadline
            if (parsedUserInput.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (!parsedUserInput[1].contains("/by")) {
                throw new DukeException("Please include the keyword \"/by\" if you want to add a deadline.");
            } else {
                String[] parsedDeadlineInput = parsedUserInput[1].split("/by ", 2);
                String date = parsedDeadlineInput[1];
                LocalDate localDate;
                try {
                    localDate = LocalDate.parse(date, formatFromInput);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please write the date in this format: dd/MM/yyyy");
                }
                Task newTask = new Deadline(parsedDeadlineInput[0], localDate);
                addTaskToList(newTask, ui, tasks, storage);
            }
        } else if (parsedUserInput[0].equals("event")) { // Add event
            if (parsedUserInput.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (!parsedUserInput[1].contains("/at")) {
                throw new DukeException("Please include the keyword \"/at\" if you want to add an event.");
            } else {
                String[] parsedDeadlineInput = parsedUserInput[1].split("/at ", 2);
                String date = parsedDeadlineInput[1];
                LocalDate localDate;
                try {
                    localDate = LocalDate.parse(date, formatFromInput);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please write the date in this format: dd/MM/yyyy");
                }
                Task newTask = new Event(parsedDeadlineInput[0], localDate);
                addTaskToList(newTask, ui, tasks, storage);
            }
        }
    }

    private void addTaskToList(Task newTask, Ui ui, TaskList tasks, Storage storage) {
        tasks.getTasks().add(newTask);
        storage.updateLS(tasks.getTasks());
        ui.reply("Got it. I've added this task: \n" + newTask.toString() +
                "     \nNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
