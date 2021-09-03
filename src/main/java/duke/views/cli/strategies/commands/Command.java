package duke.views.cli.strategies.commands;

import duke.interfaces.StringProducer;

public abstract class Command implements StringProducer {
    protected final String list = "list";
    protected final String done = "done";
    protected final String todo = "todo";
    protected final String deadline = "deadline";
    protected final String event = "event";
    protected final String delete = "delete";
    protected final String on = "on";
    protected final String find = "find";
}
