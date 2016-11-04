package todo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import todo.data.ToDoRepository;
import todo.model.ToDo;

public class ToDoControllerTest {

	private ToDoRepository mockToDoRepo;
	private ToDoController controller;

	@Before
	public void setUp() {
		mockToDoRepo = Mockito.mock(ToDoRepository.class);
		controller = new ToDoController(mockToDoRepo);

	}

	@Test
	public void ToDoRendersList() throws Exception {
		// Setup Mock Repo
		List<ToDo> toDoList = createToDoList(3);
		Mockito.when(mockToDoRepo.findToDo()).thenReturn(toDoList);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
		    .setSingleView(new InternalResourceView("/WEB-INF/views/todos.jsp")).build();

		// Assert results
		mockMvc.perform(MockMvcRequestBuilders.get("/todos")).andExpect(MockMvcResultMatchers.view().name("todos"))
		    .andExpect(MockMvcResultMatchers.model().attributeExists("todoList"))
		    .andExpect(MockMvcResultMatchers.model().attribute("todoList", toDoList));

		Mockito.verify(mockToDoRepo, Mockito.times(1)).findToDo();
	}

	@Test
	public void ToDoRendersListFromRequestParams() throws Exception {
		Long maxId = 25l;
		// Setup Mock Repo
		List<ToDo> toDoList = createToDoList(30);
		toDoList = toDoList.subList(6, 26);
		Mockito.when(mockToDoRepo.findToDos(maxId)).thenReturn(toDoList);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
		    .setSingleView(new InternalResourceView("/WEB-INF/views/todos.jsp")).build();

		// Assert results
		String url = String.format("/todos?maxId=%s", maxId);
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.view().name("todos"))
		    .andExpect(MockMvcResultMatchers.model().attributeExists("todoList"))
		    .andExpect(MockMvcResultMatchers.model().attribute("todoList", toDoList));

		Mockito.verify(mockToDoRepo, Mockito.times(1)).findToDos(maxId);
	}

	@Test
	public void ToDoRendersListFromPathParam() throws Exception {
		Long id = 25l;
		// Setup Mock Repo
		List<ToDo> toDoList = createToDoList(30);
		ToDo toDo = toDoList.get(id.intValue());
		Mockito.when(mockToDoRepo.findOne(id)).thenReturn(toDo);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
		    .setSingleView(new InternalResourceView("/WEB-INF/views/todos.jsp")).build();

		// Assert results
		String url = String.format("/todos/%s", id);
		mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.view().name("todo"))
		    .andExpect(MockMvcResultMatchers.model().attributeExists("todo"))
		    .andExpect(MockMvcResultMatchers.model().attribute("todo", toDo));

		Mockito.verify(mockToDoRepo, Mockito.times(1)).findOne(id);
	}

	@Test
	public void ToDoPost() throws Exception {
		// Setup Mock Repo
		ToDo toDo = new ToDo(25l, "ToDo Content", new Date(), null, null);
		Mockito.when(mockToDoRepo.save(Mockito.isA(ToDo.class))).thenReturn(toDo);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
		    .setSingleView(new InternalResourceView("/WEB-INF/views/todos.jsp")).build();

		// Assert results
		mockMvc.perform(MockMvcRequestBuilders.post("/todos").param("content", toDo.getContent()))
		    .andExpect(MockMvcResultMatchers.view().name("redirect:/todos/" + toDo.getId()))
		    .andExpect(MockMvcResultMatchers.model().attributeExists("todo"))
		    .andExpect(MockMvcResultMatchers.model().attribute("todo", toDo));

		Mockito.verify(mockToDoRepo, Mockito.times(1)).save(Mockito.isA(ToDo.class));
	}

	private List<ToDo> createToDoList(int count) {
		List<ToDo> todos = new ArrayList<>();
		for (int idx = 0; idx < count; idx++) {
			todos.add(new ToDo(Long.valueOf(idx), String.valueOf(idx), new Date(), Double.valueOf(idx), Double.valueOf(idx)));
		}
		return todos;
	}

}
