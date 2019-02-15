package com.myapp.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.web.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todoList = new ArrayList<Todo>();

	private static int todoCount = 4;

	static {
		todoList.add(new Todo(1, "Subodh", "Complete Reporting use case development.", new Date(), false));
		todoList.add(new Todo(2, "Amit", "Test Reporting use case.", new Date(), false));
		todoList.add(new Todo(3, "Amit", "Sign off.", new Date(), false));
		todoList.add(new Todo(4, "Subodh", "Clean up sonar issues for Reporting use case .", new Date(), false));
	}

	/**
	 * 
	 * @return
	 */
	public List<Todo> listAllTodo() {

		return todoList;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Todo> listAllTodoByUser(String user) {

		List<Todo> listByUser = new ArrayList<Todo>();

		for (Todo todo : todoList) {

			if (todo != null && (todo.getUser().equalsIgnoreCase(user))) {
				listByUser.add(todo);
			}
		}

		return listByUser;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Todo retrieveTodo(int id) {
		Todo todo = null;

		for (Todo currTodo : todoList) {

			if (currTodo != null && (currTodo.getId() == (id))) {
				todo = currTodo;

			}
		}

		return todo;
	}

	/**
	 * 
	 * @param user
	 * @param desc
	 * @param targetDate
	 * @param isDone
	 */
	public void addTodo(String user, String desc, Date targetDate, boolean isDone) {

		Todo todo = new Todo((todoCount + 1), user, desc, targetDate, isDone);
		todoList.add(todo);

		todoCount++;
	}

	/**
	 * 
	 * @param todo
	 */
	public void updateTodo(Todo todo) {
		todoList.remove(todo);
		todoList.add(todo);

	}

	/**
	 * 
	 * @param id
	 */
	public void deleteTodo(int id) {

		Iterator<Todo> itr = todoList.iterator();

		while (itr.hasNext()) {
			Todo todo = itr.next();

			if (todo != null && (todo.getId() == id)) {
				itr.remove();
				todoCount--;
			}
		}

	}

}
