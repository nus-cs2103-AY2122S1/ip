public class Todo extends Task {



    public Todo(String title)  {
        super(title);
        if (title.replaceAll("\\s+", "").length() == 4) {

        }
//        if (title.length() <= 0) {
//            throw new DukeException("Woops! your todo does not have a title!");
//        }
    }

    @Override
    public String getInfo() {
        return "[T][ ]" + this.getTitle();
    }


    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format("[T][ ]" + this.getTitle());
        } else {
            return String.format("[T][X]" + this.getTitle());
        }

    }
}