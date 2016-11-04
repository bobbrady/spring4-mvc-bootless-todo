package todo.data;

import java.util.List;

import todo.model.ToDo;

public interface ToDoRepository {

	List<ToDo> findToDos(long maxId);

	List<ToDo> findToDo();

	ToDo findOne(long id);

	ToDo save(ToDo toDo);

}
