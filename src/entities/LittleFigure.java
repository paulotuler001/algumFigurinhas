package entities;

public class LittleFigure {

	Integer id;
	String name;
	String tag;
	byte[] photo;
	String description;
	Integer page;
	Integer number;
	Integer ownerId;

	public LittleFigure(Integer id, String name, String tag, byte[] photo, String description, Integer page, Integer number, Integer ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.tag = tag;
		this.photo = photo;
		this.description = description;
		this.page = page;
		this.number = number;
		this.ownerId = ownerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setIdAlbum(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
