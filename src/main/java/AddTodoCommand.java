import java.io.FileWriter;
import java.io.IOException;

public class AddTodoCommand extends Command{

    public AddTodoCommand(String desc) {
        super(desc);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Storage storage) {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            Todo todo = new Todo(super.getDesc(), false);
            tasks.add(todo);

            System.out.println("Got it. I've added this task:");
            System.out.println(todo);

            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            fw.write("T | 0 | " + super.getDesc() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
