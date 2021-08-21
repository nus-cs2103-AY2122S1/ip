public class ToDo extends Task {

    public ToDo(String information){
        super(information);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}