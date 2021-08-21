package action;

import entity.message.ListMessage;
import entity.list.DukeTaskList;

public class DukeActionList extends DukeAction {
    private DukeTaskList list;

    public DukeActionList() {}

    public static DukeActionList createAction() {
        return new DukeActionList();
    }

    public void executeAction(DukeTaskList list) {
        this.list = list;
    }

    public ListMessage getOutputMessage() {
        return new ListMessage(this.list.toString());
    }
}
