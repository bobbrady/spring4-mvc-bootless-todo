package todo.model;

import java.util.Date;
import java.util.Objects;

public class ToDo {
	private Long id;
	private String content;
	private Date time;
	private Double latitude;
	private Double longitude;

	public ToDo() {
	}

	public ToDo(String content, Date time) {
		this(null, content, time, null, null);
	}

	public ToDo(Long id, String content, Date time, Double latitude, Double longitude) {
		this.id = id;
		this.content = content;
		this.time = time == null ? new Date() : time;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getTime() {
		return time;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, time, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ToDo)) {
			return false;
		}
		ToDo toDo = (ToDo) obj;
		return id == toDo.id && Objects.equals(content, toDo.content) && Objects.equals(time, toDo.time)
		    && latitude == toDo.latitude && longitude == toDo.longitude;
	}

}
