package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Todo;
import duke.utils.Ui;

public class AddTodoCommand extends Command {

    private String[] keyWords;
    public AddTodoCommand(String[] keywords) {
        this.keyWords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(this.keyWords[0]);
        tasks.addTask(todo);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                "    " + todo.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize())
        };
        ui.printOut(messages);
        storage.save(tasks);
    }
}
