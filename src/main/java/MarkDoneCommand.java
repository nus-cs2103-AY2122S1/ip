import java.io.IOException;

public class MarkDoneCommand implements Command{
    int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String run() throws DukeException {
        try {
            TaskList.getInstance().markDone(index);
        } catch (IOException e) {
            throw new DukeException("Error marking task as done");
        }
        String resultString =
                "Nice, I have marked this task as done:\n" +
                "\t\t" + TaskList.getInstance().get(index).toString();
        return resultString;
    }
}
