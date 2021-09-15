package duke.views.strategies.commands;

import duke.interfaces.StringProducer;

public abstract class Command implements StringProducer {
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String ON = "on";
    public static final String FIND = "find";
}
