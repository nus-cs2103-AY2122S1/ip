package jarvis.task;

import jarvis.message.OutputMessage;
import jarvis.output.Output;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(String task) {
        taskList.add(new Task(task));
        OutputMessage taskAddedMessage = new OutputMessage("added: " + task);
        Output.showFormattedOutputMessage(taskAddedMessage);
    }

    public void markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        OutputMessage doneMessage = new OutputMessage("Nice! I've marked this task as done: \n\t\t" + task.toString());
        Output.showFormattedOutputMessage(doneMessage);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Here are the tasks in your list:\n\t");

        if (taskList.size() == 0) {
            return stringBuilder.toString();
        }

        for (int i = 0; i < taskList.size() - 1; i++) {
            stringBuilder.append(i + 1).append(".").append(taskList.get(i).toString()).append("\n\t");
        }

        stringBuilder.append(taskList.size()).append(".").append(taskList.get(taskList.size() - 1));

        return stringBuilder.toString();
    }
}
