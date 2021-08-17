public class Task {
    private String name;
    private Boolean status;

    public Task (String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String toString() {
        String output = "";
        if (this.getStatus()) {
            output += "[x] ";
        } else {
            output += "[ ] ";
        }

        output += this.getName();
        return output;
    }
}
