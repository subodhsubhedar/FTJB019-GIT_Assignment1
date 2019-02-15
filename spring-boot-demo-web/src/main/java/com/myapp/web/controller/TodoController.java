package com.myapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.web.model.Todo;
import com.myapp.web.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dtFmt = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dtFmt, false));

	}

	/**
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/todo-listByUser", method = RequestMethod.GET)
	public String showTodoByUser(ModelMap modelMap) {

		String user = getLoggedInUserName(modelMap);

		List<Todo> todoListByUser = todoService.listAllTodoByUser(user);

		modelMap.put("user", user);
		modelMap.put("todoList", todoListByUser);

		return "todo";
	}

	/**
	 * 
	 * @param modelMap
	 * @return
	 */
	private String getLoggedInUserName(ModelMap modelMap) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;

			return userDetails.getUsername();
		} else {
			return principal.toString();
		}

	}

	/**
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap modelMap) {
		String user = getLoggedInUserName(modelMap);
		modelMap.put("name", user);

		modelMap.addAttribute("todo", new Todo(0, user, "Default desc", new Date(), false));

		return "add-update-todo";
	}

	/**
	 * 
	 * @param modelMap
	 * @param todo
	 * @return
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "add-update-todo";
		}

		String user = getLoggedInUserName(modelMap);

		todoService.addTodo(user, todo.getDesc(), todo.getTargetDate(), false);

		modelMap.put("name", user);

		return "redirect:/todo-listByUser";
	}

	/**
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap modelMap) {
		String user = getLoggedInUserName(modelMap);
		modelMap.put("name", user);

		Todo todo = todoService.retrieveTodo(id);

		modelMap.put("todo", todo);

		return "add-update-todo";
	}

	/**
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, ModelMap modelMap, BindingResult result) {

		if (result.hasErrors()) {
			return "add-update-todo";
		}

		String user = getLoggedInUserName(modelMap);

		todoService.updateTodo(todo);

		modelMap.put("name", user);

		return "redirect:/todo-listByUser";
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) throws Exception {

		// TEST CODE
		if (id == 1) {
			throw new Exception("An application exception occured...");
		}

		todoService.deleteTodo(id);

		return "redirect:/todo-listByUser";
	}

}
