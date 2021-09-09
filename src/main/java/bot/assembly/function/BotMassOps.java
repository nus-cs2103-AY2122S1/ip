package bot.assembly.function;

import java.util.List;

import bot.assembly.task.Task;


public class BotMassOps {


    public BotMassOps() {
    }

    public void deleteAll(List<Task> taskList) {
        taskList.clear();
    }

    public void doneAll(List<Task> taskList) {
        taskList.stream().forEach(x -> x.markAsDone());
    }
}
