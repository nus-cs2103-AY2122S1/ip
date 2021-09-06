package dao;

import configuration.Setting;
import model.Task;
import model.TimedItem;
import storage.FileListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Implementation of the TaskDao using a FileListStorage.
 */
public class TaskDaoImpl implements TaskDao {
    private final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());
    
    /** Invalid tasks file size */
    private final int INVALID_SIZE = -1;
    
    /** File containing the ArrayList of Task */
    private final FileListStorage<Task> taskFileListStorage =
            new FileListStorage<>(Setting.DATA_DIR_PATH + "/" + Setting.DATA_TASKS);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(Task task) {
        assert task != null;
        
        synchronized (this) {
            try {
                ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
                tasks.add(task);
                taskFileListStorage.writeArrayListToFile(tasks);
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Task deleteTask(int index) {
        assert index >= 0;
        
        if (isInvalidIndex(index)) {
            throw new IndexOutOfBoundsException("non valid index for deletion");
        }
        
        synchronized (this) {
            try {
                ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
                Task removedTask = tasks.remove(index);
                taskFileListStorage.writeArrayListToFile(tasks);
                
                return removedTask;
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
        
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void markDone(int index) {
        assert index >= 0;
        
        if (isInvalidIndex(index)) {
            throw new IndexOutOfBoundsException("non valid index for marking the task done");
        }
        
        synchronized (this) {
            try {
                ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
                Task task = tasks.get(index);
                task.markDone();
                taskFileListStorage.writeArrayListToFile(tasks);
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(int index) {
        assert index >= 0;
        
        if (isInvalidIndex(index)) {
            throw new IndexOutOfBoundsException("non valid index for marking the task done");
        }
        
        try {
            ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
            return tasks.get(index);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAll() {
        try {
            return taskFileListStorage.readArrayListFromFile();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return List.of();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getByKeyword(String keyword) {
        assert keyword != null;
        
        try {
            ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
            return tasks.stream()
                    .filter(x -> Arrays.asList(x.getDesc().split(" ")).contains(keyword))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return List.of();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getByTiming(LocalDate time) {
        assert time != null;
        
        try {
            ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
            return tasks.stream()
                    .filter(x -> x instanceof TimedItem)
                    .filter(x -> ((TimedItem) x).getTime().toLocalDate() == time)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return List.of();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        try {
            ArrayList<Task> tasks = taskFileListStorage.readArrayListFromFile();
            return tasks.size();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return INVALID_SIZE;
        }
    }
    
    private boolean isInvalidIndex(int index) {
        int size = getSize();
        boolean isIndexOutOfBound = index >= getSize() || index < 0;
        
        return size == INVALID_SIZE || isIndexOutOfBound;
    }
}
