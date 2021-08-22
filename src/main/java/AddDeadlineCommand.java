import java.io.FileWriter;
import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final String by;
    public AddDeadlineCommand(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Storage storage) {
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
