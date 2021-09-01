package duke;

public class Parser {
    private String command;
    private String[] twoPart;

    public Parser(String command) {
        this.command = command;
        this.twoPart = this.command.split(" ", 2);
    }

    public String[] parse() {
        return this.twoPart;
    }

    public boolean isBye() {
        if (this.command.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isList() {
        if (this.command.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDone() {
        if (this.twoPart[0].equals("done")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isToDo() {
        if (this.twoPart[0].equals("todo")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDeadline() {
        if (this.twoPart[0].equals("deadline")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEvent() {
        if (this.twoPart[0].equals("event")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDelete() {
        if (this.twoPart[0].equals("delete")) {
            return true;
        } else {
            return false;
        }
    }
}
