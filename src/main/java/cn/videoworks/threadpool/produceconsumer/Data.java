package cn.videoworks.threadpool.produceconsumer;

public class Data {

	private Integer id;
	private String name;
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
	
	@Override
	public String toString() {
		return "id="+id+";name="+name;
	}
	
	public Data(int id, String name) {
		this.id = id;
		this.name= name;
	}
}
