package sample;
public class PersonInfo {
	private String name;
	private String phone_num;
	private String birth;
	private String group;
	public PersonInfo(String name, String phone_num, String birth, String group){
		this.name = name;
		this.phone_num = phone_num;
		this.birth = birth;
		this.group = group;
		
	} //생성자
	public void showInfo(){
		System.out.println("이름 : "+name);
		System.out.println("전화번호 : "+phone_num);
		System.out.println("생일 : "+birth);
		System.out.println("그룹 : "+group);
		System.out.println("");
	}//정보 보여주기 메소드 //
	
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
}
/*
class Friend extends PersonInfo{		//친구 그룹(고등학교, 대학교)
	private String kind_f; //고딩친구와 대딩친구를 구분하기위한 변수 추가
	public Friend(String name, String phone_num, String birth, String kind){
		super(name, phone_num, birth, group);		//상위클래스의 생성자를 호출 하면서 파라미터를 전달
		this.kind_f = kind;
	}
	public void showInfos(){
		super.showInfo();		
		System.out.println(kind_f);
	}
	public String get_kind(){
		return kind_f;
	}
	public void set_kind_f(String kind){
		this.kind_f = kind;
	}
}

class Relative extends PersonInfo{		//친척그룹(외가 , 친가)
	private String kind_r;
	public Relative(String name, String phone_num, String birth, String kind){
		super(name, phone_num, birth);
		this.kind_r = kind;
	}
	public void showInfos(){
		super.showInfo();
		System.out.println(kind_r);
	}
	public String get_kind_r(){
		return kind_r;
	}
	public void set_kind_r(String kind){
		this.kind_r = kind;
	}
}
*/
