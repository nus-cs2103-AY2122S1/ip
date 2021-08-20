public class Todo extends Task{

    public final String code = "T";

    public Todo(String description) throws DukeException{
        super(description);
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString();
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
