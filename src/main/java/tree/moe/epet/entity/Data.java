package tree.moe.epet.entity;


public class Data {
	private String name;
	private double value;
	public double getValue() {
		return value;
	}
	public Data(String name , double value) {
		this.name = name;
		this.value = value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
