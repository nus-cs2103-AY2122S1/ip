import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            " <description> /by <date> - add a task to be completed by <date> in yyyy/MM/dd HHmm (24-hour format)\n" +
            "   Example: " + COMMAND_WORD + " project submission /by 2021/08/30 2359";

    private String userCommand;

    public DeadlineCommand(String userCommand) {
        super();
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int byIndex = userCommand.indexOf("/by");
            if (userCommand.strip().length() < 9 || byIndex <= 9) {
                throw new IllegalArgumentException("Please add a description and/or deadline!");
            }

            String by = userCommand.substring(byIndex + 4);
            LocalDateTime date = LocalDateTime.parse(by, Command.inputFormatter);
            Deadline newDeadline = new Deadline(userCommand.substring(9, byIndex - 1), date);

            tasks.addTask(newDeadline);
            storage.save(tasks.getItems());

            ui.printTaskAdded(newDeadline, tasks.getSize());
        } catch (IOException | IllegalArgumentException e) {
            ui.printError(e.getMessage());

        } catch (DateTimeException e) {
            ui.printError("Please add a valid event date of format yyyy/MM/dd HHmm (24-hour format)!");
        }

    }
}
