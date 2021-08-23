public class Todo extends Task {

    public Todo(String name) throws DukeTodoException {
        super(name);
        if (name.equals("")) {
            throw  new DukeTodoException();
        }
    }

    public Todo(String[] input) {
        super(input[2], input[1].equals("T") ? true : false );
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString() {
        return "T|" + super.toDataString();
    }

}
