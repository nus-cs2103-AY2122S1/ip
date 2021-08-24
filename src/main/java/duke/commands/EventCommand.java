package duke.commands;

import duke.*;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new EventCommand(arguments);
    }

    @Override
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            ui.print("Please enter the name of the task after event");
            return;
        }
        if (!args.contains("/at")) {
            ui.print("Please enter the start date of the task after /at");
            return;
        }
        String[] parts = args.split(" /at ");
        LocalDateTime startDate = dth.parseDate(parts[1]);
        if (startDate == null) {
            ui.print(dth.invalidFormat());
            return;
        }
        Event e = new Event(parts[0], false, startDate);
        tl.addToList(e);
        ui.print(tl.taskAddedMessage(e));

    }

    @Override
    public String startsWith() {
        return "event";
    }
}
