package tree.moe.epet.entity;

public class ItemVO {
	private int page;
	private long cat_id;
	private int left;
	private int right;
	private String keywords;
	private String orderedKey;
	private String sequence;
	
	
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public long getCat_id() {
		return cat_id;
	}


	public void setCat_id(long cat_id) {
		this.cat_id = cat_id;
	}


	public int getLeft() {
		return left;
	}


	public void setLeft(int left) {
		this.left = left;
	}


	public int getRight() {
		return right;
	}


	public void setRight(int right) {
		this.right = right;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getOrderedKey() {
		return orderedKey;
	}


	public void setOrderedKey(String orderedKey) {
		this.orderedKey = orderedKey;
	}


	public String getSequence() {
		return sequence;
	}


	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


	@Override
	public String toString() {
		return "ItemVO [page=" + page + ", cat_id=" + cat_id + ", left=" + left + ", right=" + right + ", keywords="
				+ keywords + ", OrderedKey=" + orderedKey + ", Sequence=" + sequence + "]";
	}

	
}
