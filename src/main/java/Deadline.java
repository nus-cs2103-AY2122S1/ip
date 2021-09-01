//Level-4 -> A-Inheritance: Deadline Task Class
public class Deadline extends Task {

    public Deadline(String description, String at, TASK_TYPE type) {
        super(description,at,type);
    }

    @Override
    public String toString() {
        return super.toString().concat(" (by: ".concat(this.by).concat(")"));
    }
}


