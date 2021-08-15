public class Todo extends Task{

    Todo(String content) {
        super(content.substring(5));
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.getStatus() ? "X" : " ", this.getContent());
    }
}
