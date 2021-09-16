package duke;

public class UiStub extends duke.Ui {
    @Override
    public String notifySuccessfulAdd(duke.TaskList tl) {
        duke.task.Task t = tl.get(0);
        return("You have successfully added " + t);
    }
}