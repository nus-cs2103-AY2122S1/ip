public abstract class DatedTask extends Task {
    protected String date;

    protected DatedTask(String description, String date) {
        super((description));
        this.date = date;
    }


}
