package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.tasks.Deadline;
import duke.Storage;
import duke.TaskList;

public class AddDeadlineCommand extends Command {
    private final String by;
    public AddDeadlineCommand(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] split = by.split(" ");

        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            Deadline deadline = new Deadline(super.getDesc(), by, false);
            tasks.add(deadline);

            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            fw.write("D | 0 | " + super.getDesc() + " | " + by + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
