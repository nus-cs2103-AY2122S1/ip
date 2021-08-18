public class UiStub extends duke.Ui {
    @Override
    public void notifySuccessfulAdd(duke.TaskList tl) {
        duke.task.Task t = tl.get(0);
        System.out.println("You have successfully added " + t);
    }
}