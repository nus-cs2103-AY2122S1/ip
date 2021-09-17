package duke;

public enum DukeStatus {
    ACTIVE("active"), INACTIVE ("Bye. Hope to see you again soon!"),
            ERROR ("Error message here."), MESSAGE("Message to user here.");

    private String response;

    DukeStatus(String response) {
        this.response = response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
