package todo.data;

public class ToDoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String MSG_BASE = "Error: The ToDo with ID %s was not found";

	private Long id;

	public ToDoNotFoundException(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getMessage() {
		return String.format(MSG_BASE, id);
	}

}
