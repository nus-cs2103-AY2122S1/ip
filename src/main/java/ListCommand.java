public class ListCommand extends DukeCommand {

    public ListCommand() {
        super();
    }

    @Override
    public String run(DukeList list) {
        return list.toString();
    }
}
