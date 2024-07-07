package entities;

public class Album {
	
	private Integer id;
	private String name;
	private Integer pages;
	private byte[] cape;
	
	public Album(Integer id, String name, Integer totalLFigures, byte[] cape) {
		super();
		this.id = id;
		this.name = name;
		this.pages = totalLFigures;
		this.cape = cape;
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
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public byte[] getCape() {
		return cape;
	}
	public void setPages(byte[] cape) {
		this.cape = cape;
	}
	
	
}
