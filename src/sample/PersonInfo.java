package sample;
public class PersonInfo {
	private int count;
	private String name;
	private String phone_num;
	private String birth;
	private String group;
	private String memo;
	public PersonInfo(int count,String name, String phone_num, String birth, String group,String memo){
		this.count = count;
		this.name = name;
		this.phone_num = phone_num;
		this.birth = birth;
		this.group = group;
		this.memo = memo;
		
	} //생성자
	public String showInfo(){
		return "이름 : "+name+"\n"+"전화번호 : "+ phone_num +"\n"+"생일 : "+birth+"\n"+"그룹 : "+group+"\n"+"MEMO : "+"\n"+memo+"\n";
//		System.out.println("이름 : "+name);
//		System.out.println("전화번호 : "+phone_num);
//		System.out.println("생일 : "+birth);
//		System.out.println("그룹 : "+group);
//		System.out.println("MEMO : ");
//		System.out.println(memo);
//		System.out.println("");
	}//정보 보여주기 메소드 
	public int get_count(){
		return count;
	}
	public String get_memo(){
		return memo;
	}
	public String get_group(){
		return group;
	}
	public String get_name(){
		return name;
	}
	public String get_phone_num(){
		return phone_num;
	}
	public String get_birth(){
		return birth;
	}
	public void set_name(String name){
		this.name = name;
	}
	public void set_phone_num(String phone_num){
		this.phone_num = phone_num;
	}
	public void set_birth(String birth){
		this.birth = birth;
	}
	public void set_group(String group){
		this.group = group;
	}
	public void set_memo(String memo){
		this.memo = memo;
	}
}
