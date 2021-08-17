public class ToDo extends Task{

    public ToDo(String task) {
        super(task);
    }

    @Override
    String printTask() {
         String result = "";
        if(this.complete) {
            result = "[T][X] ";
         } else {
            result = "[T][ ] ";
        }
        return result + this.task;
    }
}
