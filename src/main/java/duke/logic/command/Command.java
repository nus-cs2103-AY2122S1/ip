//@@author {yourGithubUsername}-reused
//{Code is from LimPy1000 repo}

package duke.logic.command;

import duke.logic.tasks.TaskList;

public abstract class Command {
    public abstract String executeCommand(TaskList taskList);
}
