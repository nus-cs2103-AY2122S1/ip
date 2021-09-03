package duke;

public class Parser {
    private String command;
    private String[] twoPart;
    private String[] time;

    public Parser(String command) {
        this.command = command;
        this.twoPart = this.command.split(" ", 2);
    }

    public String getCommand() {
        return this.command;
    }

    public String firstPart() {
        return this.twoPart[0];
    }

    public int secondPartInInt() throws DukeException {
        try {
            return Integer.valueOf(this.twoPart[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("which specific task from the list? Give a number!");
        }
    }

    public String secondPart() throws DukeException {
        try {
            return this.twoPart[1];
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DukeException("do remember to add your description!");
        }
    }

    public String[] deadline() {
        return this.twoPart[1].split(" /by ", 2);
    }

    public String[] event() {
        return this.twoPart[1].split(" /at ", 2);
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
