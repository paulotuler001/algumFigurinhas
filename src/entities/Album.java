package entities;

public class Album {
	
	private Integer id;
	private String name;
	private Integer totalLFigures;
	
	public Album(Integer id, String name, Integer totalLFigures) {
		super();
		this.id = id;
		this.name = name;
		this.totalLFigures = totalLFigures;
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
	public Integer getTotalLFigures() {
		return totalLFigures;
	}
	public void setTotalLFigures(Integer totalLFigures) {
		this.totalLFigures = totalLFigures;
	}
	
	
}
