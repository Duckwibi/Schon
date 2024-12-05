package OtherTable;

public class VoteOfReviewAVG {
	private int productID;
	private int vote;
	public VoteOfReviewAVG() {
		super();
	}
	public VoteOfReviewAVG(int productID, int vote) {
		super();
		this.productID = productID;
		this.vote = vote;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	
}
