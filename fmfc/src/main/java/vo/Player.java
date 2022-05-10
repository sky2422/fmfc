package vo;

import java.util.Date;

public class Player {

	private int back_no;
	private String name;
	private String position;
	
	private String birth;
	
	private int height;
	private int weight;
	
	private String team;
	private String image;
	
	public Player() {}

	public Player(int back_no, String name, String position, String birth, int height, int weight, String team,
			String image) {
		super();

		this.back_no = back_no;
		this.name = name;
		this.position = position;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
		this.team = team;
		this.image = image;
	}

	public int getBack_no() {
		return back_no;
	}

	public void setBack_no(int back_no) {
		this.back_no = back_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
}
