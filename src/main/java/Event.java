public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("event ", "");

        String oldDate = temp.substring(temp.lastIndexOf("at") + 3);
        DateTimeConverter converter = new DateTimeConverter();
        String newDate = converter.convertDateAndTime(oldDate);
        temp = temp.replace(oldDate, newDate);

        int intBeforeBy = temp.lastIndexOf("at") - 1;
        if (temp.charAt(intBeforeBy) != ' ') {
            char tempChar = temp.charAt(intBeforeBy);
            temp = temp.replace(String.valueOf(tempChar), "(");
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
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
