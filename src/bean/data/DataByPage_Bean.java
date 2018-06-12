package bean.data;

import com.sun.rowset.*;

public class DataByPage_Bean {

	private CachedRowSetImpl rowSet = null;//储存表中全部记录的行集对象
	private int pageSize = 8;//每页显示记录数
	private int totalPages = 1;//分页后总页数
	private int currentPage = 1;//当前显示页数
	
	public CachedRowSetImpl getRowSet() {
		return rowSet;
	}
	
	public void setRowSet(CachedRowSetImpl rowSet) {
		this.rowSet = rowSet;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
