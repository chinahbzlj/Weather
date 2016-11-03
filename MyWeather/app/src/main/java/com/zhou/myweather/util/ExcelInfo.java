package com.zhou.myweather.util;

public class ExcelInfo {
	public String col1, col2, col3, col4, col5;

		public ExcelInfo(String col1, String col2, String col3, String col4) {
			this.col1 = col1;
			this.col2 = col2;
			this.col3 = col3;
			this.col4 = col4;
		}

		public ExcelInfo(String col1, String col2, String col3, String col4, String col5) {
			this.col1 = col1;
			this.col2 = col2;
			this.col3 = col3;
			this.col4 = col4;
			this.col5 = col5;
		}
	}