package cn.videoworks.threadpool;

public class Task implements Runnable {
	private Integer id;
	private String name;
	
	public Task(Integer id, String name) {
		this.id= id;
		this.name = name;
	}

	public void run() {
		System.out.println("ÔËÐÐ"+name);
	}
	
	@Override
	public String toString() {
		return "id="+id+";name="+name;
	}

}
