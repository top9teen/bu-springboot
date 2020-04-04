package com.it.app.constant;

public class MasterConstant {
	
	public interface status{
		public static final String active = "A";
		public static final String inActive = "I";	
		public static final String error = "error";
		public static final String success = "success";	
	}
	public interface role{
		public static final String admin = "1";
		public static final String user = "2";	
		
		public static final String adminTh = "ผู้ดูแลระบบ";
		public static final String userTh = "ผู้สูงอายุ";	
		public static final String empTh = "พนักงาน";	
	}
	
	public interface titleName{
		public static final String mr = "นาย";
		public static final String mrs= "นาง";	
		public static final String miss = "นางสาว";
	}

}
