package duke.command;

import duke.task.Events;
import duke.List;
import duke.Storage;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND = "event";
    private  String desc;
    private LocalDateTime at;

    public EventCommand(String desc, LocalDateTime at) {
        this.desc = desc;
        this.at = at;
    }

    @Override
    public void execute(List list, Ui ui, Storage storage) throws IOException {
        Events newEvent = new Events(this.desc, this.at);
        list.add(newEvent);
        storage.writeToFile(list);
        ui.printAdd(newEvent, list.getList().size());
    }
}
