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
	//추가 검색 수정 삭제기능을 수행, 저장된 정보들은 데이터베이스에 따로 저장(txt) 프로그램을 키거나 번호를 추가 할때마다 자동으로 저장&로드 
	//검색: 한글 글자단위로 인식하여 저장된 정보와 비교하여 글자가포함되었으면 textarea에 이름 목록들을 모두 보여줄것, 동명이인 주의 
	//		이름 목록들중에 찾고자하는 정보를 마우스로 입력하면 해당 정보의 Info를 결과창에 출력할것 이때 아래에 수정 버튼을 만들어서 
	//		클릭하면 수정페이지로 넘어가도록 설정, 삭제도 마찬가지 형식 
	public InfoManager(){
		
	}//생성자
	ArrayList<PersonInfo> Infos = new ArrayList<PersonInfo>();
	
	public void Load_data(){		//데이터베이스에서 정보를 읽어와 인스턴스를 생성하고 객체배열 Infos에 저장하는 메소드
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("personInfo.txt"));
			String line;
			while((line=br.readLine()) != null){
				String str[] = line.split("/");
				// ex) 이름/번호/생일/그룹
				int count = Integer.parseInt(str[0]); //강제 형변환 
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
	public void save_data(){ //객체배열로 부터 객체들을 파일에 저장하는 메소드
		try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("personInfo.txt"));
            for(int i=0;i<Infos.size();i++){
                bw.write(Infos.get(i).get_count()+"/"+Infos.get(i).get_name()+"/"+Infos.get(i).get_phone_num()+"/"+Infos.get(i).get_birth()
                		+"/"+Infos.get(i).get_group()+"/"+Infos.get(i).get_memo());
                bw.newLine();
            }
            bw.close();
            System.out.println("데이터베이스 저장완료");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void add_Phone_num(String name, String phone_num,String birth, String group, String memo){
		try{
			int count = Infos.size()+1;
    		Infos.add(new PersonInfo(count,name,phone_num,birth,group,memo));	//객체리스트에 인스턴스를 만들어서 추가
    		save_data(); // 추가로입력한 객체를 파일에 저장 
    		System.out.println("정보가 추가되었습니다.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
		
	public static void main(String[] args) {

	}

}
