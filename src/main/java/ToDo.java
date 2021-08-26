public class ToDo extends Task {
    protected String type;

    public ToDo(String information,String type){
        super(information);
        this.type = type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getType() {
        return type;
    }

}