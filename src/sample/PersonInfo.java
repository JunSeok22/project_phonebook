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
		
	} //������
	public void showInfo(){
		System.out.println("�̸� : "+name);
		System.out.println("��ȭ��ȣ : "+phone_num);
		System.out.println("���� : "+birth);
		System.out.println("�׷� : "+group);
		System.out.println("");
	}//���� �����ֱ� �޼ҵ� 
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
}
/*
class Friend extends PersonInfo{		//ģ�� �׷�(����б�, ���б�)
	private String kind_f; //���ģ���� ���ģ���� �����ϱ����� ���� �߰�
	public Friend(String name, String phone_num, String birth, String kind){
		super(name, phone_num, birth, group);		//����Ŭ������ �����ڸ� ȣ�� �ϸ鼭 �Ķ���͸� ����
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

class Relative extends PersonInfo{		//ģô�׷�(�ܰ� , ģ��)
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