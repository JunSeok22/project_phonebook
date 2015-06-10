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
	//추가 검색 수정 삭제기능을 수행, 저장된 정보들은 데이터베이스에 따로 저장(txt) 프로그램을 키거나 번호를 추가 할때마다 자동으로 저장&로드 
	//검색: 한글 글자단위로 인식하여 저장된 정보와 비교하여 글자가포함되었으면 textarea에 이름 목록들을 모두 보여줄것, 동명이인 주의 
	//		이름 목록들중에 찾고자하는 정보를 마우스로 입력하면 해당 정보의 Info를 결과창에 출력할것 이때 아래에 수정 버튼을 만들어서 
	//		클릭하면 수정페이지로 넘어가도록 설정, 삭제도 마찬가지 형식 
	public InfoManager(){
		Load_data();
	}
	ArrayList<PersonInfo> Infos = new ArrayList<PersonInfo>();
	
	Scanner s = new Scanner(System.in);
	public void start(){
		while(true){
			System.out.println("");
			System.out.print("1.추가  2.검색  3.로드  4.삭제 5.종료  ");
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
					System.out.println("종료되었습니다.");
					break;
				}
				else{
					System.out.println("잘못입력하셨습니다. ");
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}// swing으로 구현 삭제해야함
	
	public void search_char(){
		
	}
	
	public void search(){
		System.out.print("검색하실 이름을 입력해주세요.");
		String f_name = s.next();
		if(f_name.length()==1){	//초성 한단어로 입력할 경우
			search_char();
		}
		int count=0; // 검색된 결과 개수를 저장
		int [] same_name = new int [100]; //100명까지 동명이인 검색 가능
		for(int i =0;i<Infos.size();i++){
			if(f_name.equals(Infos.get(i).get_name())){
				same_name[count]=i; //같은 이름으로 검색된 모든 인스턴스의 객체번호를 저장
				count = count+1;
			}
		}
		if (count==0){
			System.out.println("찾으시는 검색결과가 없습니다.");
		}
		for(int i=0;i<count;i++){ // 검색결과에 해당하는 객체번호로 해당 객체들의 정보호출
			Infos.get(same_name[i]).showInfo();
		}
	}
	
	public void Load_data(){		//데이터베이스에서 정보를 읽어와 인스턴스를 생성하고 객체배열 Infos에 저장하는 메소드
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("personInfo.txt"));
			String line;
			while((line=br.readLine()) != null){
				String str[] = line.split("/");
				// ex) 이름/번호/생일/그룹
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
	public void save_data(){ //객체배열로 부터 객체들을 파일에 저장하는 메소드
		try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("personInfo.txt"));
            for(int i=0;i<Infos.size();i++){
                bw.write(Infos.get(i).get_name()+"/"+Infos.get(i).get_phone_num()+"/"+Infos.get(i).get_birth()
                		+"/"+Infos.get(i).get_group());
                bw.newLine();
            }
            bw.close();
            System.out.println("데이터베이스 저장완료");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void add_Phone_num(){
		
	}// 스윙으로 구현
	
	public void add_Phone_num(String name, String phone_num,String birth, String group){
		try{
			//Load_data();	//기존의 데이터 유지하기 위해 실행
    		Infos.add(new PersonInfo(name,phone_num,birth,group));	//객체리스트에 인스턴스를 만들어서 추가
    		save_data(); // 추가로입력한 객체를 파일에 저장 
    		System.out.println("정보가 추가되었습니다.");
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
