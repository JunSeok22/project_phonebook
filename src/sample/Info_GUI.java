package sample;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.CardLayout;
import java.awt.Color;

public class Info_GUI  {
	JFrame fr = new JFrame("PersonInfo Book");
	JPanel pan = new JPanel();		//기본패널
	JPanel searchPan = new JPanel();//검색패널
	JPanel addPan = new JPanel();	//추가패널
	JButton search_btn = new JButton("SEARCH");	//검색 기능을 수행하는 버튼
	JButton save_btn = new JButton("저장");		//저장기능을 수행하는 버튼 
	JButton search_Pan_btn = new JButton("검색"); //검색패널 화면으로 전환하는 버튼
	JButton add_Pan_btn = new JButton("추가하기");	//추가패널 화면으로 전환하는 버튼
	JPanel search_BF = new JPanel();	//검색화면에서 검색필드와 검색버튼이 있는패널
	JTextField searchBar = new JTextField(15);	//검색 텍스트 필드 + 길이
	JTextArea resultArea = new JTextArea(20,25);	//검색결과 표시할 textarea + 크기
	private JTextField[] fields; //여러개 필드를 배열에 저장(추가 패널에서 이름,번호,생일,그룹 / 검색화면에선 검색필드)
	CardLayout cl = new CardLayout();	//화면 전환 할때 사용 

	public Info_GUI(){	//생성자
		run();
	}
	
	private void run(){
		pan.setLayout(cl); //카드레이아웃으로 배치관리자 지정
		//검색화면 (메인화면)
		search_BF.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		search_BF.add(searchBar);
		search_BF.add(search_btn);
		
		searchPan.setLayout(new BorderLayout());
		searchPan.add(search_BF,BorderLayout.NORTH);
		searchPan.add(resultArea,BorderLayout.CENTER);
		
		searchPan.add(add_Pan_btn,BorderLayout.SOUTH);
		
		
		
		//////////////////////////////여기까지 검색화면 설정
		//추가화면 설정
		
		String[] labels = { "이름", "번호", "생일", "그룹" }; //라벨 이름 지정
		int[] widths = { 10, 10, 10, 10 }; //길이 지정
		fields = new JTextField[labels.length];	//라벨길이만큼 입력필드생성
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));	//라벨을 똑같이 분할해서 세로로 붙일 패널 생성
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel btnPanel = new JPanel(new GridLayout(1,2));
		
		btnPanel.add(save_btn);
		btnPanel.add(search_Pan_btn);
		
		for (int i = 0; i < labels.length; i += 1) { 
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));//필드패널 생성 왼쪽정렬 
			
			fields[i] = new JTextField(); 	//각각의 필드 생성
			fields[i].setColumns(widths[i]); //텍스트필드 길이설정
			p.add(fields[i]);//필드패널에 각각의 필드 입력
			
			fieldPanel.add(p);
			
			JLabel lab = new JLabel(labels[i],JLabel.RIGHT);//각각의 라벨 생성
			//lab.setLabelFor(fields[i]); //라벨 이름설정
			labelPanel.add(lab);	//라벨패널
			
		}
					
		addPan.setLayout(new BorderLayout());
		addPan.add(btnPanel,BorderLayout.SOUTH);
		addPan.add(labelPanel, BorderLayout.WEST);
		addPan.add(fieldPanel,BorderLayout.CENTER);
		//////////////////////////////////////////////////////////여기까지 추가화면 설정
		
		// add_screen(); 	//추가화면을 메소드로 따로 구현가능? 생성자는 한번만 호출되는데 문제는 없는지?
		
		
		pan.add(searchPan,"SP");	//검색패널 붙히기 + 호출은 SP
		pan.add(addPan,"AP");		//추가패널
		
		cl.show(pan, "SP"); //첫화면은 검색화면
		
		//여기서부터 이벤트기능 구현 메소드
		save_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = getText(0); //첫번째 필드 텍스트 입력값 
			    String num = getText(1);
			    String birth = getText(2);
			    String group = getText(3);
				saveEvent(name,num,birth,group);	//데이터 저장 메소드
				
			}
		});
		
		add_Pan_btn.addActionListener(new ActionListener(){	// 검색팬호출버튼에 이벤트 기능 부여
			public void actionPerformed(ActionEvent e){
				cl.show(pan, "AP");	//btn을 누르면 추가화면을 보여준다 (cardlayout)
			}
		});
		
		search_Pan_btn.addActionListener(new ActionListener(){	// 검색팬호출버튼에 이벤트 기능 부여
			public void actionPerformed(ActionEvent e){
				cl.show(pan, "SP");	//btn을 누르면 검색화면(메인)을 보여준다 (cardlayout)
			}
		});
		
		fr.add(pan);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//종료버튼누르면 fr종료 - 프로그램종료
		fr.pack();
		fr.setSize(1000,1000);
		fr.setVisible(true);
	}//run 메소드
	
	
	
	public void add_screen(){
	}
	
	// textfields에서 사용자가 입력한 값을 받아오기위한 함수 
	public String getText(int i) {  
		return (fields[i].getText()); 
	} 
	
	public void saveEvent(String name, String num, String birth, String group){	//저장 이벤트
		
		
		InfoManager i = new InfoManager(); //infomanager의 메소드와 변수를 사용하기위해 인스턴스화
	    i.add_Phone_num(name,num,birth,group);	//Infos에 인스턴스 추가
		
	}

	public static void main(String[] args) {
		Info_GUI ig = new Info_GUI();//인스턴스화 하면서 생성자실행
		
		/*
		ig.fr.add(ig.pan);
		ig.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//종료버튼누르면 fr종료 - 프로그램종료
		ig.fr.pack();
		ig.fr.setSize(500,500);
		ig.fr.setVisible(true);
		*/
		
	}
		


}


