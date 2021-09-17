package duke;

import java.util.Arrays;
import java.util.HashSet;

public class Parser {
    String command;
    Action action;

    public Parser(String var1) {
        this.command = var1;
        this.action = this.action(var1);
    }

    public Action action(String var1) {
        String[] var2 = var1.split(" ", 2);
        String var3 = var2[0];
        if (var3.equals("list")) {
            return Action.LIST;
        } else if (var3.equals("done")) {
            return Action.DONE;
        } else if (var3.equals("todo")) {
            return Action.TODO;
        } else if (var3.equals("deadline")) {
            return Action.DEADLINE;
        } else if (var3.equals("event")) {
            return Action.EVENT;
        } else if (var3.equals("delete")) {
            return Action.DELETE;
        } else if (var3.equals("bye")) {
            return Action.BYE;
        } else {
            return var3.equals("find") ? Action.FIND : Action.UNKNOWN;
        }
    }

    public Action getAction() {
        return this.action;
    }

    public int getCommandSize() {
        String[] var1 = this.command.split(" ", 2);
        return var1.length;
    }

    public String getActionList() {
        String[] var1 = this.command.split(" ", 2);
        return var1[1];
    }

    public boolean isValid() throws InputNotValidError {
        HashSet var1 = new HashSet(Arrays.asList(Action.TODO, Action.DONE, Action.DONE, Action.DEADLINE, Action.EVENT));
        if (this.action == Action.UNKNOWN) {
            throw new InputNotValidError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (var1.contains(this.action) && this.getCommandSize() <= 1) {
            throw new InputNotValidError("☹ OOPS!!! The description of a " + this.getActionList() + " cannot be empty.");
        } else {
            return true;
        }
    }
}
