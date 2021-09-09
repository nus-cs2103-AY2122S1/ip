package duke.logic.tasks;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

public class TaskStub extends Task {
    private final int stubNo;

    public TaskStub(int stubNo) {
        super("Stub task " + stubNo, true);
        this.stubNo = stubNo;
    }

    @Override
    public Task createUpdatedCopy(UpdateTaskDescriptor updateDescriptor) {
        return new TaskStub(stubNo + 10);
    }

    @Override
    public String getStatusIcon() {
        return "X";
    }

    @Override
    public String getSaveFormat() {
        return "S | 1 | Stub task " + stubNo;
    }

    @Override
    public String toString() {
        return "[S][X] Stub task " + stubNo;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof TaskStub && stubNo == ((TaskStub) obj).stubNo);
    }
}
