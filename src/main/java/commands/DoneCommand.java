package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;
import tasks.Task;

/**
 * Command to mark a task as done
 */
public class DoneCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) throws InvalidTaskException {
        Stream<Integer> indices = Arrays.stream(args[0].split(","))
                .map(s -> Integer.parseInt(s.trim()) - 1);
        List<String> messages = new ArrayList<>();
        indices.sorted(Collections.reverseOrder()).peek((i) -> validateArgs(i, bot)).forEach(ind -> {
            Task task = bot.getTaskList().getTaskAt(ind);
            task.markDone();
            messages.addAll(Arrays.asList(getTaskDoneMessage(task)));
            messages.add("");
        });
        return messages.toArray(new String[0]);
    }

    /**
     * Validate command arg
     *
     * @param arg command argument
     * @param bot Bot in context
     * @throws InvalidTaskException invalid command task
     */
    public void validateArgs(int arg, Bot bot) throws InvalidTaskException {
        if (arg < 0 || arg >= bot.getTaskList().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "task dos nat exizt in ze taskz lizt!");
        }
    }

    /**
     * Message for completing a task
     *
     * @param task task that was completed
     * @return task done message
     */
    public String[] getTaskDoneMessage(Task task) {
        return new String[]{
            "diz iz marked donez:",
            Ui.TEXT_BLOCK_MARGIN + task.toString()
        };
    }

}
