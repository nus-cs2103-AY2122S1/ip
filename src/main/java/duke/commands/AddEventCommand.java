package duke.commands;

import duke.tasks.Event;
import duke.Storage;
import duke.TaskList;

import java.io.FileWriter;
import java.io.IOException;

public class AddEventCommand extends Command {
    private final String at;
    public AddEventCommand(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            Event event = new Event(super.getDesc(), at, false);
            tasks.add(event);

            System.out.println("Got it. I've added this task:");
            System.out.println(event);

            fw.write("E | 0 | " + super.getDesc() + " | " + at + "\n");
            fw.close();

            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
