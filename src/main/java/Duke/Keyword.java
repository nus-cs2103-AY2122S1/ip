package Duke;

public enum Keyword {
    LIST ("list"),
    DONE ("done"),
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DELETE ("delete"),
    FIND("find");

    private String command;
    Keyword(String command) {
        this.command = command;
    }
}

