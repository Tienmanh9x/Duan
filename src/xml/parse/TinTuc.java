package xml.parse;

public class TinTuc {
	
	private String title;
	private String description;
	private String link;
	private String pubDate;
	private String image;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public TinTuc tintucCopy(){
		TinTuc copy = new TinTuc();
		copy.title = this.title;
		copy.description = this.description;
		copy.link = this.link;
		copy.pubDate = this.link;
		copy.image = this.image;
		return copy;
	}

}
