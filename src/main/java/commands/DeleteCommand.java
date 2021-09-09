package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;

/**
 * Command to delete a command from the bot
 */
public class DeleteCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) throws InvalidTaskException {
        System.out.println(args[0]);
        Stream<Integer> indices = Arrays.stream(args[0].split(","))
                                        .map(s -> Integer.parseInt(s.trim()) - 1);
        List<String> messages = new ArrayList<>();
        indices.sorted(Collections.reverseOrder()).peek((i) -> validateArgs(i, bot)).forEach(ind -> {
            messages.addAll(Arrays.asList(bot.getTaskList().removeTask(ind)));
            messages.add("");
        });
        return messages.toArray(new String[0]);
    }

    /**
     * Validate command arg
     *
     * @param args command argument
     * @param bot Bot in context
     * @throws InvalidTaskException invalid command task
     */
    public void validateArgs(Integer ind, Bot bot) throws InvalidTaskException {
        if (ind < 0 || ind >= bot.getTaskList().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
    }

}
