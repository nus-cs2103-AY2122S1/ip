package domain;

public class Deadline extends Task {
    
    private String dueDate;
    public Deadline(String name, String dueDate) {
        super(name);
        typeString = "D";
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (by: %s)", base, dueDate);
        return result;
    }
}
