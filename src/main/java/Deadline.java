public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("deadline ", "");
        if (temp.contains("/by")) {
            temp = temp.replace("/by", "(by");
            return temp + ")";
        }
        return "(" + temp + ")";
    }

    @Override
    public void markedDone() {
        super.markedDone();
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
