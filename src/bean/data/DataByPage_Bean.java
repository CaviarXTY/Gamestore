package bean.data;

import com.sun.rowset.*;

public class DataByPage_Bean {

	private CachedRowSetImpl rowSet = null;//�������ȫ����¼���м�����
	private int pageSize = 8;//ÿҳ��ʾ��¼��
	private int totalPages = 1;//��ҳ����ҳ��
	private int currentPage = 1;//��ǰ��ʾҳ��
	
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
