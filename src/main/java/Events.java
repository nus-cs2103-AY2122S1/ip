public class Events extends Task {
    String type;
    String time;

    Events (String title, String time) {
        super(title.substring(6));
        this.type = "[E]";
        this.time = time;
    }

    @Override
    String printTask() {
        return type + super.printTask() + "(at:" + time + ")";
    }
}