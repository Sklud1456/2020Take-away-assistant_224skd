package wm_assistant.model;

public class product {
	private int product_no;
	private int productsort_no;
	private String product_name;
	private double product_price;
	private double product_sellprice;
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public int getProductsort_no() {
		return productsort_no;
	}
	public void setProductsort_no(int productsort_no) {
		this.productsort_no = productsort_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getProduct_sellprice() {
		return product_sellprice;
	}
	public void setProduct_sellprice(double product_sellprice) {
		this.product_sellprice = product_sellprice;
	}
	
	public static final String[] tblproductTitle={"序号","名称","价格","会员价"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0) return String.valueOf(product_no);
		else if(col==1) return product_name;
		else if(col==2) return String.valueOf(product_price);
		else if(col==3) return String.valueOf(product_sellprice);
		else return "";
	}
	
	public static final String[] tblproductTitleforuser={"名称","价格","会员价"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCelluser(int col){
		if(col==0) return product_name;
		else if(col==1) return String.valueOf(product_price);
		else if(col==2) return String.valueOf(product_sellprice);
		else return "";
	}
	

}
