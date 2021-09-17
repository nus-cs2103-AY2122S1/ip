package test;

import lebron.Lebron;
import lebron.Ui;
import lebron.task.Task;
import java.util.ArrayList;


public class UiStub extends Ui {

    @Override
    public String replyAdd(ArrayList<Task> lst, Task task) {
        return "hi";
    }

}
