package sample;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
		Load_data();
	}
	ArrayList<PersonInfo> Infos = new ArrayList<PersonInfo>();
	
	Scanner s = new Scanner(System.in);
	public void start(){
		while(true){
			System.out.println("");
			System.out.print("1.�߰�  2.�˻�  3.�ε�  4.���� 5.����  ");
			try{
				int c = s.nextInt();
				
				if(c==1){
					add_Phone_num();
				}
				else if(c==2){
					search();
				}
				else if(c==3){
					Load_data();
				}
				else if(c==4){
					
				}
				else if(c==5){
					System.out.println("����Ǿ����ϴ�.");
					break;
				}
				else{
					System.out.println("�߸��Է��ϼ̽��ϴ�. ");
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}// swing���� ���� �����ؾ���
	
	public void search_char(){
		
	}
	
	public void search(){
		System.out.print("�˻��Ͻ� �̸��� �Է����ּ���.");
		String f_name = s.next();
		if(f_name.length()==1){	//�ʼ� �Ѵܾ�� �Է��� ���
			search_char();
		}
		int count=0; // �˻��� ��� ������ ����
		int [] same_name = new int [100]; //100����� �������� �˻� ����
		for(int i =0;i<Infos.size();i++){
			if(f_name.equals(Infos.get(i).get_name())){
				same_name[count]=i; //���� �̸����� �˻��� ��� �ν��Ͻ��� ��ü��ȣ�� ����
				count = count+1;
			}
		}
		if (count==0){
			System.out.println("ã���ô� �˻������ �����ϴ�.");
		}
		for(int i=0;i<count;i++){ // �˻������ �ش��ϴ� ��ü��ȣ�� �ش� ��ü���� ����ȣ��
			Infos.get(same_name[i]).showInfo();
		}
	}
	
	public void Load_data(){		//�����ͺ��̽����� ������ �о�� �ν��Ͻ��� �����ϰ� ��ü�迭 Infos�� �����ϴ� �޼ҵ�
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("personInfo.txt"));
			String line;
			while((line=br.readLine()) != null){
				String str[] = line.split("/");
				// ex) �̸�/��ȣ/����/�׷�
				String name = str[0];
				String num = str[1];
				String birth = str[2];
				String group = str[3];
				Infos.add(new PersonInfo(name,num,birth,group));
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
                bw.write(Infos.get(i).get_name()+"/"+Infos.get(i).get_phone_num()+"/"+Infos.get(i).get_birth()
                		+"/"+Infos.get(i).get_group());
                bw.newLine();
            }
            bw.close();
            System.out.println("�����ͺ��̽� ����Ϸ�");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void add_Phone_num(){
		
	}// �������� ����
	
	public void add_Phone_num(String name, String phone_num,String birth, String group){
		try{
			//Load_data();	//������ ������ �����ϱ� ���� ����
    		Infos.add(new PersonInfo(name,phone_num,birth,group));	//��ü����Ʈ�� �ν��Ͻ��� ���� �߰�
    		save_data(); // �߰����Է��� ��ü�� ���Ͽ� ���� 
    		System.out.println("������ �߰��Ǿ����ϴ�.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	
		
	public static void main(String[] args) {
		InfoManager im = new InfoManager();
		im.start();

	}

}
