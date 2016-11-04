package todo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import todo.data.ToDoRepository;
import todo.model.ToDo;
import todo.model.ToDoForm;

@Controller
@RequestMapping("/todos")
public class ToDoController {

	private ToDoRepository toDoRepo;

	@Autowired
	public ToDoController(ToDoRepository toDoRepo) {
		this.toDoRepo = toDoRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String todos(Model model, @RequestParam(value = "maxId", required = false) Long maxId) {
		List<ToDo> toDos = new ArrayList<>();
		if (maxId == null) {
			toDos = toDoRepo.findToDo();
		} else {
			toDos = toDoRepo.findToDos(maxId);
		}
		model.addAttribute("todoList", toDos);
		return "todos";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String todos(Model model, ToDoForm toDoForm) {
		ToDo toDo = new ToDo(null, toDoForm.getContent(), new Date(), 10.0, 20.0);
		toDo = toDoRepo.save(toDo);
		model.addAttribute("todo", toDo);
		return "redirect:/todos/" + toDo.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String todo(Model model, @PathVariable("id") Long id) {
		ToDo toDo = toDoRepo.findOne(id);
		model.addAttribute("todo", toDo);
		return "todo";
	}
}
