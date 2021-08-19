public class ToDos extends Task {
    String type;

    ToDos(String title) {
        super(title.substring(5));
        this.type = "[T]";
    }

    @Override
    String printTask() {
        return type + super.printTask();
    }
}
