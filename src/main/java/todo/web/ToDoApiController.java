package todo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todo.data.ToDoNotFoundException;
import todo.data.ToDoRepository;
import todo.model.ToDo;

@RestController
@RequestMapping("/api/todos")
public class ToDoApiController {

	private ToDoRepository toDoRepo;

	@Autowired
	public ToDoApiController(ToDoRepository toDoRepo) {
		this.toDoRepo = toDoRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ToDo>> todos(@RequestParam(value = "maxId", required = false) Long maxId) {
		List<ToDo> toDos = new ArrayList<>();
		if (maxId == null) {
			toDos = toDoRepo.findToDo();
		} else {
			toDos = toDoRepo.findToDos(maxId);
		}
		return new ResponseEntity<>(toDos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ToDo> todo(@PathVariable("id") Long id) {
		ToDo toDo = toDoRepo.findOne(id);
		return new ResponseEntity<>(toDo, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ToDo> todos(@RequestBody ToDo toDo) {
		toDo = toDoRepo.save(toDo);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.CREATED);
	}

	@ExceptionHandler(ToDoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String toDoNotFound(ToDoNotFoundException e) {
		return e.getMessage();
	}
}
