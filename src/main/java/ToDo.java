public class ToDo extends Task {

    ToDo(String information) {
        super(information);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}