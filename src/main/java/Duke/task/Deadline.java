package Duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String content, String by){
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    public String toStorageString(){
        String s1 = super.toStorageString();
        String s2 = String.format("D %s| %s", s1, by);
        return s2;
    }
}
