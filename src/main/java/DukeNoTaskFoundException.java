public class DukeNoTaskFoundException extends DukeException{
    private int taskNum;

    public DukeNoTaskFoundException(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return String.format("%s I cannot find the task with number %d!",
                super.toString(),
                this.taskNum
        );
    }
}
