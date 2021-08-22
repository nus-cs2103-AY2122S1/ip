package abyss;

public abstract class Command {
    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        LIST,
        EXIT,
    }

    private Type type;

    protected Command(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }
}
