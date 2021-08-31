import lebron.Lebron;
import lebron.task.Task;
import lebron.Ui;

import java.util.ArrayList;

public class UiStub extends Ui {
    /**
     * Constructor
     *
     * @param lebron
     */
    public UiStub(Lebron lebron) {
        super(lebron);
    }

    @Override
    public void replyAdd(ArrayList<Task> lst, Task task) {

    }

}
