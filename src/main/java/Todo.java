public class Todo extends Task {

    public Todo(String title, int indexNumber) {
        super(title, indexNumber);

    }

    @Override
    public String getInfo() {
        return "[T][ ]" + this.getTitle();
    }


    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format(this.getIndexNumber() + ".[T][ ]" + this.getTitle());
        } else {
            return String.format(this.getIndexNumber() + ".[T][X]" + this.getTitle());
        }

    }
}