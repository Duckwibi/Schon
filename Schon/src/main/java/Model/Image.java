package Model;

public class Image {
	private int imageID;
	private String link;
	public Image() {
		super();
	}
	public Image(int imageID, String link) {
		super();
		this.imageID = imageID;
		this.link = link;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
