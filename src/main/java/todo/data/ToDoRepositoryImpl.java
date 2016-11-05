package todo.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import todo.model.ToDo;

@Repository
public class ToDoRepositoryImpl implements ToDoRepository {

	private final List<ToDo> items;

	public static long ID_SEQUENCE = 0;

	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int DEFAULT_PAGE_DELTA = DEFAULT_PAGE_SIZE - 1;

	public ToDoRepositoryImpl() {
		items = new ArrayList<>();
	}

	@Override
	public List<ToDo> findToDos(long maxId) throws ToDoNotFoundException {
		int maxIdx = findIndex(maxId);
		int minIdx = 0;
		if (maxIdx - DEFAULT_PAGE_DELTA > 0) {
			minIdx = maxIdx - DEFAULT_PAGE_DELTA;
		}
		List<ToDo> subList = items.subList(minIdx, maxIdx + 1);
		return subList;
	}

	@Override
	public List<ToDo> findToDo() {
		if (items.size() < DEFAULT_PAGE_SIZE) {
			return items.subList(0, items.size());
		} else {
			return items.subList(items.size() - DEFAULT_PAGE_DELTA, items.size());
		}
	}

	@Override
	public ToDo findOne(long id) {
		int idx = findIndex(id);
		ToDo foundOne = idx == -1 ? null : items.get(idx);
		return foundOne;
	}

	@Override
	public ToDo save(ToDo toDo) {
		ToDo savedToDo = new ToDo(ID_SEQUENCE++, toDo.getContent(), toDo.getTime(), toDo.getLatitude(),
		    toDo.getLongitude());
		items.add(savedToDo);
		return savedToDo;
	}

	private int findIndex(long id) throws ToDoNotFoundException {
		for (int idx = 0; idx < items.size(); idx++) {
			if (items.get(idx).getId() == id) {
				return idx;
			}
		}
		throw new ToDoNotFoundException(id);
	}

}
