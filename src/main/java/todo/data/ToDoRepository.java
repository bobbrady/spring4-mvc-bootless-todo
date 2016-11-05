package todo.data;

import java.util.List;

import todo.model.ToDo;

public interface ToDoRepository {

	List<ToDo> findToDos(long maxId) throws ToDoNotFoundException;

	List<ToDo> findToDo();

	ToDo findOne(long id) throws ToDoNotFoundException;

	ToDo save(ToDo toDo);

}
