package me.yukun99.ip.core;

import me.yukun99.ip.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TaskFinder {
	private final HashMap<String, List<Task>> wordTaskMap = new HashMap<>();

	public void addTask(Task task, String name) {
		for (String word : parseWords(name)) {
			List<Task> tasks = new ArrayList<>();
			if (wordTaskMap.containsKey(word)) {
				tasks = wordTaskMap.get(word);
			}
			tasks.add(task);
			wordTaskMap.put(word, tasks);
		}
	}

	public void deleteTask(Task task, String name) {
		for (String word : parseWords(name)) {
			if (!wordTaskMap.containsKey(word)) {
				continue;
			}
			List<Task> tasks = wordTaskMap.get(word);
			tasks.remove(task);
			wordTaskMap.put(word, tasks);
		}
	}

	private List<String> parseWords(String name) {
		return new ArrayList<>(Arrays.asList(name.split(" ")));
	}

	public String findTasksByWord(String word) {
		StringBuilder message = new StringBuilder("\nHere are your tasks that contain the word '" + word + "'.");
		List<Task> tasks = new ArrayList<>();
		if (wordTaskMap.containsKey(word)) {
			tasks = wordTaskMap.get(word);
		}
		for (int i = 0; i < tasks.size(); ++i) {
			message.append("\n ").append(i + 1).append(".").append(tasks.get(i));
		}
		if (tasks.size() == 0) {
			message
					.append("\n  Is this a joke? You have no tasks containing the word '")
					.append(word)
					.append("', idiot.");
		}
		return message.toString();
	}
}
