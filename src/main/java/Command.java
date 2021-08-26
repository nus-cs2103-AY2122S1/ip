public class Command {
    private String type;
    private String details;

    public Command(String type, String details) {
        this.type = type;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }
}
