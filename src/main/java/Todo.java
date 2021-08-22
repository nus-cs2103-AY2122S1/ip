public class Todo extends Task {

    public Todo(String name) throws DukeTodoException {
        super(name);
        if (name.equals("")) {
            throw  new DukeTodoException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
