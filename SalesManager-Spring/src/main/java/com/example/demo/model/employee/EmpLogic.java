package com.example.demo.model.employee;

/**
 * 従業員関係の処理を担当する
 * @author yuta
 *
 */
public class EmpLogic {

	private EmpLogic() {
		
	}
	
	/**
	 * 検索用SQLを作成する
	 * @param name 氏名
	 * @param branchID 支社ID
	 * @param departmentId 部署ID
	 * @param trueFalse 削除者を含めるか？
	 * @return 検索用SQL
	 */
	public static String createSql(String name,String branchID,String departmentId,String trueFalse) {
		
		String baseSQL = "  from MST_Employee ";
		
		//何も入力せず検索
		if(name == "" && branchID== "" && departmentId=="" && trueFalse ==null) {
			return baseSQL+" where enable=true";
		}
		//氏名、カナのみで検索
		else if(name!= "" && branchID == ""&& departmentId=="" && trueFalse== null) {
			return baseSQL+String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and enable=true",name,name);
		}
		//支社IDのみで検索
		else if(branchID != "" && name==""  && departmentId=="" && trueFalse== null) {
			return baseSQL +String.format(" where branchId=%s and enable=true",branchID);
		}
		//部署IDのみで検索
		else if(departmentId != "" &&  name== "" &&  branchID == "" && trueFalse == null ) {
			return baseSQL +String.format(" where departmentId= %s and enable=true", departmentId);
		}
		//削除者のみで検索
		else if(trueFalse != null && name == "" && branchID== "" && departmentId=="" ) {
			return baseSQL+" ";
		}
		//氏名と支社ID
		else if(name != "" && branchID !="" && trueFalse== null && departmentId== "") {
			return baseSQL+ String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and branchId=%s and enable=true",name,name,branchID);
		}
		//氏名と部署ID
		else if(name != "" && departmentId != "" && branchID == "" && trueFalse == null) {
			return baseSQL + String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and departmentId=%s and enable=true", name,name,departmentId);
		}
		//氏名と削除者
		else if(name != "" && trueFalse != null && departmentId=="" && branchID=="") {
			return baseSQL + String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') ", name,name,departmentId);
		}
		//支社IDと部署ID
		else if(branchID != "" && departmentId != "" && name == "" && trueFalse == null) {
			return baseSQL + String.format(" where branchID=%s and departmentId=%s and enable=true",branchID,departmentId);
		}
		//支社IDと削除者
		else if(branchID != "" && trueFalse != null && departmentId == "" && name == "") {
			return baseSQL +String.format(" where branchId =%s ",branchID);
		}
		//部署IDと削除者
		else if(departmentId != "" && trueFalse != null && name == "" && branchID == "") {
			return baseSQL + String.format(" where departmentId =%s", departmentId);
		}
		//氏名と部署と所属
		else if(name != "" && departmentId != "" && branchID != "" && trueFalse ==null) {
			return baseSQL+ String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and departmentId=%s and branchId=%s and enable=true",name,name,departmentId,branchID);
		}
		//氏名と部署と削除者
		else if(name != "" && departmentId != "" && trueFalse !=null && branchID =="") {
			return baseSQL+String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and departmentId=%s",name,name,departmentId);
		}
		//氏名と所属と削除者
		else if(name != "" && branchID != "" && trueFalse != null && departmentId == "") {
			return baseSQL+String.format(" where (fullName like '%%%s%%' or kanaName like '%%%s%%') and branchId=%s ",name,name,branchID);
		}
		//部署と所属と削除者
		else if(departmentId !="" && branchID != "" && trueFalse != null && name=="") {
			return baseSQL+String.format(" where departmentId=%s  and branchId=%s",departmentId,branchID);
		}
		//すべてで検索
		else{
			return baseSQL+String.format("where (fullName like '%%%s%%' or kanaName like '%%%s%%') and branchId=%s and departmentId=%s ",name,name,branchID,departmentId);
		}
		
	}
	
}
