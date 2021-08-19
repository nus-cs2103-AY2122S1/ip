public class ToDos extends Task {
    String type;

    ToDos(String title) {
        super(title);
        this.type = "[T]";
    }

    @Override
    String printTask() {
        return type + super.printTask();
    }
}
