public class ToDo extends Task{
    ToDo(String description) {
        super(description);
    }

    @Override
    public String typeOfTask() {
        return "T";
    }


}
