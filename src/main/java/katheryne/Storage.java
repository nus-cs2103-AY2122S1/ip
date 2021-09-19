package katheryne;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import katheryne.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    /**
     * Basic constructor for storage
     */
    public Storage() {
        mapper = new ObjectMapper().enableDefaultTyping();
        mapper.registerModule(new JavaTimeModule());

        // give object mapper the ability to write the properties of tasks to json
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer = mapper.writer(new DefaultPrettyPrinter());
    }

    /**
     * Loads tasks from a given file, provided that said file exists and is readable.
     *
     * @param lst The TaskList to load the tasks into
     * @param pathName The path name relative to Katheryne of the saved file
     * @throws KatheryneException
     */
    void loadTasks(TaskList lst, String pathName) throws KatheryneException {
        assert !pathName.isEmpty() : "Cannot load from a filepath which is blank!";
        if (Files.isReadable(Paths.get(pathName))) {
            assert Files.isReadable(Paths.get(pathName));
            try {
                lst.addAll(Arrays.asList(this.mapper.readValue(
                        Paths.get(pathName).toFile(),
                        Task[].class)));
            } catch (IOException e) {
                throw new KatheryneException("I can't seem to find your files... Let's start over afresh.");
            }
        }
    }

    /**
     * Tries to save the tasks to a file.
     *
     * @param lst TaskList
     * @param pathName Location to store tasks.
     */
    public void saveTasks(TaskList lst, String pathName) throws KatheryneException {
        assert !pathName.isEmpty() : "Cannot save to a filepath which is blank!";
        try {
            Files.write(Paths.get(pathName), writer.writeValueAsString(lst.getList()).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new KatheryneException("Oh wait! Traveller, I couldn't catch that... Your file wasn't saved");
        }
    }
}
