package duke.command;

import duke.DukeList;

public class FindCommand extends DukeCommand {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String run(DukeList list) {
        return list.getMatches(keyWord);
    }
}
