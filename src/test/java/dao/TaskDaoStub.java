package dao;

import model.Task;

import java.util.List;

public class TaskDaoStub implements TaskDao {
	@Override
	public void addTask(Task task) {
	
	}
	
	@Override
	public Task deleteTask(int index) throws IndexOutOfBoundsException {
		return null;
	}
	
	@Override
	public void markDone(int index) throws IndexOutOfBoundsException {
	
	}
	
	@Override
	public Task getTask(int index) throws IndexOutOfBoundsException {
		return null;
	}
	
	@Override
	public List<Task> getAll() {
		return null;
	}
	
	@Override
	public int getSize() {
		return 0;
	}
}
