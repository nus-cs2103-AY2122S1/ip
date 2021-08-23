import java.util.ArrayList;

public abstract class Command {
    private boolean isActive = true;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isActive() {
        return this.isActive;
    }

    public void exit() {
        this.isActive = false;
    }

    public static String arrayToString(ArrayList<Task> list) {
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
