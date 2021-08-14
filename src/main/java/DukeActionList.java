public class DukeActionList extends DukeAction {
    private DukeTaskList list;

    public DukeActionList() {}

    public static DukeActionList createAction() {
        return new DukeActionList();
    }

    public void executeAction(DukeTaskList list) {
        this.list = list;
    }

    public DukeListMessage getOutputMessage() {
        return new DukeListMessage(this.list.toString());
    }
}
