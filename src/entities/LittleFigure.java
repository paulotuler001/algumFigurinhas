package entities;

public class LittleFigure {

	Integer id;
	String name;
	String tag;
	String photo;
	String description;
	String page;
	Integer numberInAlbum;
	Integer idAlbum;

	public LittleFigure(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
//		this.tag = tag;
//		this.photo = photo;
//		this.description = description;
//		this.page = page;
//		this.numberInAlbum = numberInAlbum;
//		this.idAlbum = idAlbum;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getNumberInAlbum() {
		return numberInAlbum;
	}

	public void setNumberInAlbum(Integer numberInAlbum) {
		this.numberInAlbum = numberInAlbum;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

}
