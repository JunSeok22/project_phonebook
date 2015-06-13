package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoManager {
	//�߰� �˻� ���� ��������� ����, ����� �������� �����ͺ��̽��� ���� ����(txt) ���α׷��� Ű�ų� ��ȣ�� �߰� �Ҷ����� �ڵ����� ����&�ε� 
	//�˻�: �ѱ� ���ڴ����� �ν��Ͽ� ����� ������ ���Ͽ� ���ڰ����ԵǾ����� textarea�� �̸� ��ϵ��� ��� �����ٰ�, �������� ���� 
	//		�̸� ��ϵ��߿� ã�����ϴ� ������ ���콺�� �Է��ϸ� �ش� ������ Info�� ���â�� ����Ұ� �̶� �Ʒ��� ���� ��ư�� ���� 
	//		Ŭ���ϸ� ������������ �Ѿ���� ����, ������ �������� ���� 
	public InfoManager(){
		
	}//������
	ArrayList<PersonInfo> Infos = new ArrayList<PersonInfo>();
	
	public void Load_data(){		//�����ͺ��̽����� ������ �о�� �ν��Ͻ��� �����ϰ� ��ü�迭 Infos�� �����ϴ� �޼ҵ�
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("personInfo.txt"));
			String line;
			while((line=br.readLine()) != null){
				String str[] = line.split("/");
				// ex) �̸�/��ȣ/����/�׷�
				int count = Integer.parseInt(str[0]); //���� ����ȯ 
				String name = str[1];
				String num = str[2];
				String birth = str[3];
				String group = str[4];
				String memo = str[5];
				Infos.add(new PersonInfo(count,name,num,birth,group,memo));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void save_data(){ //��ü�迭�� ���� ��ü���� ���Ͽ� �����ϴ� �޼ҵ�
		try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("personInfo.txt"));
            for(int i=0;i<Infos.size();i++){
                bw.write(Infos.get(i).get_count()+"/"+Infos.get(i).get_name()+"/"+Infos.get(i).get_phone_num()+"/"+Infos.get(i).get_birth()
                		+"/"+Infos.get(i).get_group()+"/"+Infos.get(i).get_memo());
                bw.newLine();
            }
            bw.close();
            System.out.println("�����ͺ��̽� ����Ϸ�");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void add_Phone_num(String name, String phone_num,String birth, String group, String memo){
		try{
			int count = Infos.size()+1;
    		Infos.add(new PersonInfo(count,name,phone_num,birth,group,memo));	//��ü����Ʈ�� �ν��Ͻ��� ���� �߰�
    		save_data(); // �߰����Է��� ��ü�� ���Ͽ� ���� 
    		System.out.println("������ �߰��Ǿ����ϴ�.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
		
	public static void main(String[] args) {

	}

}
