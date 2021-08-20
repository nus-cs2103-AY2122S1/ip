package status;

public enum Status {
    COMPLETED("[X]"), NOT_COMPLETED("[]"), STORED("Stored"), NOTSTORED("NotStored");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}