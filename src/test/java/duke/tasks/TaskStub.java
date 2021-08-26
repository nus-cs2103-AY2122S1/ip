package duke.tasks;

public class TaskStub extends Task {
    int stubNo;

    public TaskStub(int stubNo) {
        super("Stub task " + stubNo, true);
        this.stubNo = stubNo;
    }

    @Override
    public String getStatusIcon() {
        return "X";
    }

    @Override
    public String toSaveFormat() {
        return "S | 1 | Stub task " + stubNo;
    }

    @Override
    public String toString() {
        return "[S][X] Stub task " + stubNo;
    }

}
