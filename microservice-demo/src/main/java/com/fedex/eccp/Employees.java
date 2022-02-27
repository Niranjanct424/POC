package com.fedex.eccp;

public class Employees {

//	private Integer EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees(String fIRST_NAME, String lAST_NAME) {
		super();
		FIRST_NAME = fIRST_NAME;
		LAST_NAME = lAST_NAME;
	}

//	public Integer getCount() {
//		return count;
//	}
//
//	public void setCount(Integer count) {
//		this.count = count;
//	}
//
//	public Employees(Integer count) {
//		super();
//		this.count = count;
//	}
//
//	public Employees() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

}
