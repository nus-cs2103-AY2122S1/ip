package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.task.Task;

public interface Command {

   void execute(TaskList taskList, Ui ui, Storage storage);

}
