package wm_assistant.model;

public class productsort {
	private int productsort_no;
	private int merchat_no;
	private String productsort_name;
	private int productsort_number;
	public int getProductsort_no() {
		return productsort_no;
	}
	public void setProductsort_no(int productsort_no) {
		this.productsort_no = productsort_no;
	}
	public int getMerchat_no() {
		return merchat_no;
	}
	public void setMerchat_no(int merchat_no) {
		this.merchat_no = merchat_no;
	}
	public String getProductsort_name() {
		return productsort_name;
	}
	public void setProductsort_name(String productsort_name) {
		this.productsort_name = productsort_name;
	}
	public int getProductsort_number() {
		return productsort_number;
	}
	public void setProductsort_number(int productsort_number) {
		this.productsort_number = productsort_number;
	}
	
	public static final String[] tblproductsortTitle={"序号","名称","数量"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0) return String.valueOf(productsort_no);
		else if(col==1) return productsort_name;
		else if(col==2) return String.valueOf(productsort_number);
		else return "";
	}
	
	public static final String[] tblproductsortTitleforuser={"名称","数量"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCelluser(int col){
		if(col==0) return productsort_name;
		else if(col==1) return String.valueOf(productsort_number);
		else return "";
	}

}
