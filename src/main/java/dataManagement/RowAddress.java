package dataManagement;

public class RowAddress {
	private int PageId;
	private int RowIndex;

	public RowAddress(int PgId, int RowIndex) {
		this.PageId=PgId;
		this.RowIndex=RowIndex;
	}

	public int getPageId() {
		return PageId;
	}

	public int getRowIndex() {
		return RowIndex;
	}
}
