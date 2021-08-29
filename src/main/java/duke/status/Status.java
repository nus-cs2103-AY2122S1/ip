package duke.status;

public enum Status {
    COMPLETED("[X]"), NOT_COMPLETED("[]");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}