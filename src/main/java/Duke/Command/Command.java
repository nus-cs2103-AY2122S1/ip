package Duke.Command;

public abstract class Command {
    public enum Type {
        LIST, DONE, DELETE, ADD, FIND
    }

    private String description;
    private Type type;
    public Command(String description, Type type) {
        this.description = description;
        this.type = type;
    }

//    public void execute() {
//        switch (type) {
//            case ADD:
//            case DELETE:
//            case DONE:
//            case FIND:
//            case LIST:
//            default:
//        }
//    }
    public abstract Command execute();
}
