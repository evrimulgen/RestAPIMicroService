package ie.martin.model;

public class Share {
	
	private String name;
	private double value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(Double v) {
		this.value = v;
	}
	@Override
	public String toString() {
		return "Share [name=" + name + ", value=" + value + "]";
	}
	
	

}
