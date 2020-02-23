package com.qiyuesuo.entity;
/*
 *Administrator
 *2020年2月21日
 *
*/

public class File {
	private int f_id;
	private int f_size;
	private String f_type;
	private String f_oldName;
	/* private Date f_buildDate; */
	private String f_savePath;
	private String f_date;
	private String f_key;

	// 构造方法
	public File(int f_size, String f_type, String f_oldName, String f_savePath, String date, String f_key) {
		super();
		this.f_size = f_size;
		this.f_type = f_type;
		this.f_oldName = f_oldName;
		this.f_savePath = f_savePath;
		this.f_date = date;
		this.f_key = f_key;
	}

	// 用于查询操作
	public File(int f_id, int f_size, String f_type, String f_oldName, String f_savePath, String f_date, String key) {
		super();
		this.f_id = f_id;
		this.f_size = f_size;
		this.f_type = f_type;
		this.f_oldName = f_oldName;
		this.f_savePath = f_savePath;
		this.f_date = f_date;
		this.f_key = key;
	}

	public String getF_key() {
		return f_key;
	}

	public void setF_key(String f_key) {
		this.f_key = f_key;
	}

	public String getF_date() {
		return f_date;
	}

	public void setF_date(String f_date) {
		this.f_date = f_date;
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public int getF_size() {
		return f_size;
	}

	public void setF_size(int f_size) {
		this.f_size = f_size;
	}

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getF_oldName() {
		return f_oldName;
	}

	public void setF_oldName(String f_oldName) {
		this.f_oldName = f_oldName;
	}

	/*
	 * public Date getF_buildDate() { return f_buildDate; } public void
	 * setF_buildDate(Date f_buildDate) { this.f_buildDate = f_buildDate; }
	 */
	public String getF_savePath() {
		return f_savePath;
	}

	public void setF_savePath(String f_savePath) {
		this.f_savePath = f_savePath;
	}

	/*
	 * public String getDigitalEnvelope() { return digitalEnvelope; } public
	 * void setDigitalEnvelope(String digitalEnvelope) { this.digitalEnvelope =
	 * digitalEnvelope; }
	 */
	@Override
	public String toString() {
		return "File [f_size=" + f_size + ", f_type=" + f_type + ", f_oldName=" + f_oldName + ", f_buildDate="
				+ ", f_savePath=" + f_savePath + ", digitalEnvelope=" + "]";
	}

}
