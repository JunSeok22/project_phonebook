package sample;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Info_GUI extends javax.swing.JFrame {
	JFrame fr = new JFrame("PersonInfo Book");
	JPanel pan = new JPanel(); // 기본패널
	JPanel searchPan = new JPanel();// 검색패널
	JPanel addPan = new JPanel(); // 추가화면
	JButton search_btn = new JButton("SEARCH"); // 검색 기능을 수행하는 버튼
	JButton save_btn = new JButton("저장하기"); // 저장기능을 수행하는 버튼
	JButton add_btn = new JButton("추가하기"); // 추가하기
	JButton Info_btn = new JButton("보기"); // 보기패널 화면으로 전환하는 버튼
	JButton del_btn = new JButton("삭제하기");
	JButton modify_btn = new JButton("수정하기"); 
	JButton com_btn = new JButton("완료"); 
	JPanel search_BP = new JPanel(); // 검색화면에서 검색필드와 검색버튼이 있는패널
	JPanel menu_btn_Pan = new JPanel(); // 검색화면에서 하단의 여러메뉴버튼이 있는 패널
	JPanel Login = new JPanel(); // 맨처음 로그인 패널
	JTextField searchBar = new JTextField(15); // 검색 텍스트 필드 + 길이
	JComboBox<String> c = new JComboBox<String>(); // 그룹종류 표시
	private JTextField[] fields; // 여러개 필드를 배열에 저장(추가 패널에서 이름,번호,생일)
	private JTextField[] field; // 여러개 필드를 배열에 저장(수정 패널에서 이름,번호,생일)
	JTable table = new JTable();// 검색결과를 테이블형식으로 만듬
	JTextArea ja; // memo area
	JTextArea jta;	// modify memo area
	Border bd; // 테두리 표시
	
	
	public Info_GUI() { // 생성자
		run();
	}

	private void run() {
		CardLayout cl = new CardLayout(); // 화면 전환 할때 사용
		pan.setLayout(cl); // 카드레이아웃으로 배치관리자 지정
		// 로그인화면
		Login.setLayout(new BorderLayout());
		JLabel id = new JLabel("ID : ");
		JTextField f = new JTextField(15);
		JLabel pw = new JLabel("PW : ");
		JTextField ff = new JPasswordField(15);
		JPanel logn = new JPanel(); // id panel
		JPanel logc = new JPanel(); // pw panel
		logc.setLayout(new FlowLayout());
		logn.setLayout(new FlowLayout());
		logn.add(id);
		logn.add(f);
		logc.add(pw);
		logc.add(ff);
		Login.add(logn, BorderLayout.NORTH);
		Login.add(logc, BorderLayout.CENTER);
		JPanel log_btnP = new JPanel();// 로그인화면의 버튼 패널
		JButton login = new JButton("로그인으로 들어가기");
		JButton enter = new JButton("들어가기");
		log_btnP.add(login);
		log_btnP.add(enter);
		Login.add(log_btnP, BorderLayout.SOUTH);
		// /////////////로그인화면 설정

		// 검색화면 (메인화면)
		JScrollPane scrollP = new JScrollPane(); // 테이블이 들어갈 스크롤 패널
		table.setModel(new table_system(new ArrayList<PersonInfo>()));//
		scrollP.setViewportView(table);
		bd = BorderFactory.createEtchedBorder();
		bd = BorderFactory.createTitledBorder(bd, " 검색하기 ");
		search_BP.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); //버튼 패널 배치관리자 , 수평 수직 갭크기
		search_BP.add(searchBar);
		search_BP.add(search_btn);
		search_BP.setBorder(bd);
		
		menu_btn_Pan.add(add_btn);
		menu_btn_Pan.add(Info_btn);
		menu_btn_Pan.add(del_btn);
		menu_btn_Pan.add(modify_btn);

		table.setModel(new table_system(new ArrayList<PersonInfo>()));

		searchPan.setLayout(new BorderLayout());
		searchPan.add(search_BP, BorderLayout.NORTH);
		searchPan.add(scrollP, BorderLayout.CENTER);

		searchPan.add(menu_btn_Pan, BorderLayout.SOUTH);
		
		// ////////////////////////////여기까지 검색화면 설정

		pan.add(searchPan, "SP"); // 검색패널 붙히기 + 호출은 SP
		pan.add(Login, "LP"); // 정보패널 전환
		cl.show(pan, "LP"); // 첫화면은 로그인화면

		// 여기서부터 이벤트기능 구현 메소드
		add_btn.addActionListener(new ActionListener() { // 추가 버튼
			public void actionPerformed(ActionEvent e) {
				JFrame j = new JFrame("추가하기");
				// 추가화면 설정
				String[] labels = { "이름 : ", "번호 : ", "생일 : " }; // 라벨 이름 지정
				int[] widths = { 10, 10, 10 }; // 길이 지정
				fields = new JTextField[labels.length]; // 라벨길이 만큼 입력필드생성 그룹은
														// combo
				JPanel fieldPanel = new JPanel(new GridLayout(
						labels.length + 2, 1)); // 그룹필드 + memo area 까지 5개 들어감
				JPanel btnPanel = new JPanel();

				btnPanel.add(save_btn);

				for (int i = 0; i < labels.length; i += 1) {
					JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
					JLabel lab = new JLabel(labels[i], JLabel.RIGHT);// 각각의 라벨
																		// 생성
					p.add(lab); // 라벨패널
					fields[i] = new JTextField(); // 각각의 필드 생성
					fields[i].setColumns(widths[i]); // 텍스트필드 길이설정
					p.add(fields[i]);// 필드패널에 각각의 필드 입력
					fieldPanel.add(p);
				}
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel lab = new JLabel("그룹 : ", JLabel.RIGHT);
				p.add(lab);
				c.addItem("친구");
				c.addItem("친척");
				c.addItem("학교");
				c.addItem("지인");
				c.addItem("가족");
				p.add(c);
				fieldPanel.add(p);

				JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));// memo textarea panel
				ja = new JTextArea("MEMO", 7, 20);
				jp.add(ja);
				fieldPanel.add(jp);// memo

				addPan.setLayout(new BorderLayout());
				addPan.add(btnPanel, BorderLayout.SOUTH);
				addPan.add(fieldPanel, BorderLayout.CENTER);
				j.add(addPan);
				j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // 종료버튼누르면 프로그램종료
				j.setSize(300, 400);
				j.setVisible(true);
			}
		});// 추가기능 메소드
		
		
		search_btn.addActionListener(new ActionListener() { // 검색버튼
			public void actionPerformed(ActionEvent e) {
				String search_quary = searchBar.getText();	//검색 값
				try {
					BufferedReader br = new BufferedReader(new FileReader("personInfo.txt"));
					ArrayList<PersonInfo> infodata = new ArrayList<PersonInfo>(); 
					String current = null;
					while ((current = br.readLine()) != null) {
						String temp[] = current.split("/");
						int in = Integer.parseInt(temp[0]);
						if (search_quary.contains(",")) {	//	,가 입력된 경우
							String spt[] = search_quary.split(",");
							if((temp[1].equals(spt[0]) && temp[4].equals(spt[1])) || (temp[4].equals(spt[0]) && temp[1].equals(spt[1]))){
								// (이름,그룹 )검색  또는 (그룹,이름 )검색
								infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
							}			
						}
						else if(search_quary.equals("all")) {	//모든 정보표시
							infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
						}
						else {	//이름만 입력 또는 그룹만 입력
							if(temp[1].equals(search_quary) || temp[4].equals(search_quary)){
								infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
							}
						}

					}
					br.close();
						table.setModel(new table_system(infodata));
					
				} catch (FileNotFoundException e1) {
					System.out.println("해당 파일이 없습니다.");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		////////////////////////
		modify_btn.addActionListener(new ActionListener() {	//수정버튼 - 추가 프레임에서 내용을 채워서 보여주고 내용을 완료버튼을 누르면  setter로 값을 바꾼다.
			public void actionPerformed(ActionEvent e) {
				InfoManager im = new InfoManager();
				im.Load_data();
				
				int row = table.getSelectedRow();
				
				JFrame j = new JFrame("수정하기");
				field = new JTextField[3]; 					
				JPanel fieldPanel = new JPanel(new GridLayout(5, 1)); // 그룹필드 + memo area 까지 5개 들어감
				JPanel btnPanel = new JPanel();

				btnPanel.add(com_btn); // 완료버튼
				JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel lab1 = new JLabel("이름 : ", JLabel.RIGHT);// 각각의 라벨
																		// 생성
				p1.add(lab1); // 라벨패널
				
				field[0] = new JTextField(im.Infos.get(row).get_name()); // 이름필드에 선택된 객체의 이름값 넣어서 생성
				field[0].setColumns(10); // 텍스트필드 길이설정
				p1.add(field[0]);// 필드패널에 각각의 필드 입력
				fieldPanel.add(p1);
				//이름
				
				JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel lab2 = new JLabel("번호 : ", JLabel.RIGHT);// 각각의 라벨
																		// 생성
				p2.add(lab2); // 라벨패널
				field[1] = new JTextField(im.Infos.get(row).get_phone_num()); 
				field[1].setColumns(10); // 텍스트필드 길이설정
				p2.add(field[1]);// 필드패널에 각각의 필드 입력
				fieldPanel.add(p2);
				//번호
				
				JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel lab3 = new JLabel("생일 : ", JLabel.RIGHT);
				p3.add(lab3); // 라벨패널
				field[2] = new JTextField(im.Infos.get(row).get_birth()); 
				field[2].setColumns(10); // 텍스트필드 길이설정
				p3.add(field[2]);// 필드패널에 각각의 필드 입력
				fieldPanel.add(p3);
				//생일
				
				
				//}
				JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel label = new JLabel("그룹 : ", JLabel.RIGHT);
				p4.add(label);
				c.addItem("친구");
				c.addItem("친척");
				c.addItem("학교");
				c.addItem("지인");
				c.addItem("가족");
				p4.add(c);
				fieldPanel.add(p4);

				JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));// memo textarea panel
				jta = new JTextArea(im.Infos.get(row).get_memo(), 7, 20);
				jp.add(jta);
				fieldPanel.add(jp);// memo

				addPan.setLayout(new BorderLayout());
				addPan.add(btnPanel, BorderLayout.SOUTH);
				addPan.add(fieldPanel, BorderLayout.CENTER);
				j.add(addPan);
				j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // 종료버튼누르면 프로그램종료
				j.setSize(300, 400);
				j.setVisible(true);
				
			}
		});
		
		com_btn.addActionListener(new ActionListener() {	// 완료버튼 수정화면에서 입력된각각의 값들을 setter
			public void actionPerformed(ActionEvent e) {
				String name = get_Text(0); // 첫번째 필드 텍스트 입력값
				String num = get_Text(1);
				String birth = get_Text(2);
				String group = (String) c.getSelectedItem();// combobox's item
				String memo = jta.getText();
				saveEvent(name, num, birth, group, memo); // 데이터 저장 메소드
			}
		});

		del_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				InfoManager i = new InfoManager();
				i.Load_data();
				i.Infos.remove(row); // 삭제
				i.save_data();	//데이터 베이스 다시 씀
				table.setModel(new table_system(i.Infos));
			}
		});

		save_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = getText(0); // 첫번째 필드 텍스트 입력값
				String num = getText(1);
				String birth = getText(2);
				String group = (String) c.getSelectedItem();// combobox's item
				String memo = ja.getText();
				saveEvent(name, num, birth, group, memo); // 데이터 저장 메소드

			}
		});
		
		
		///													
		Info_btn.addActionListener(new ActionListener() { // 버튼입력을 하면 목록에서 선택된
															// 객체의 정보를 표시
			public void actionPerformed(ActionEvent e) {
				// 정보화면 설정
				bd = BorderFactory.createEtchedBorder();
				bd = BorderFactory.createTitledBorder(bd, " 세부정보 ");
				JPanel Info_Pan = new JPanel();
				Info_Pan.setLayout(new BorderLayout());
				JPanel IbtnP = new JPanel();
				JPanel jp = new JPanel();	//textarea 들어갈 패널
				JTextArea show = new JTextArea(10,18);	//텍스트에어리어는 자동으로 스크롤이 생김
				jp.add(show);
				jp.setBorder(bd);
				IbtnP.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20)); // 버튼 오른정렬
				IbtnP.add(del_btn);
				Info_Pan.add(jp, BorderLayout.CENTER);// 자세한 정보 보여주기
				Info_Pan.add(IbtnP, BorderLayout.SOUTH);// 버튼패널
				// /////////////////////////여기까지 정보화면 설정
				int row = table.getSelectedRow();
			
				InfoManager i = new InfoManager();
				i.Load_data();
				
				show.append(i.Infos.get(row).showInfo());
				
				JFrame j = new JFrame("자세히보기");
				j.add(Info_Pan);
				j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
				j.setSize(245, 325);
				j.setVisible(true);
			}
		});

		
		enter.addActionListener(new ActionListener() { // 검색팬호출버튼에 이벤트 기능 부여 ..
														// 비로그인
			public void actionPerformed(ActionEvent e) {
				cl.show(pan, "SP"); // btn을 누르면 검색화면(메인)을 보여준다 (cardlayout)
				fr.setSize(800, 900);
			}
		});

		login.addActionListener(new ActionListener() { // 메인호출버튼에 이벤트 기능 부여..
														// 로그인
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader br = new BufferedReader(new FileReader("LoginInfo.txt"));
					String current = null;
					while ((current = br.readLine()) != null) {
						String temp[] = current.split("/");
						if(f.getText().equals(temp[0]) && ff.getText().equals(temp[1])){
							cl.show(pan, "SP"); // btn을 누르면 검색화면(메인)을 보여준다 (cardlayout)
							fr.setSize(800, 900);
						}
						else{
							JFrame a = new JFrame();
							JPanel b = new JPanel();
							b.setLayout(new FlowLayout(FlowLayout.CENTER));
							JLabel c = new JLabel("아이디 또는 패스위드 잘못입력");
							b.add(c);
							a.add(b);
							a.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
							a.pack();
							a.setSize(300, 150);
							a.setVisible(true);
							System.out.println("아이디 또는 패스워드 잘못입력");
						}
					}
				}
				catch (FileNotFoundException e1) {
					System.out.println("해당 파일이 없습니다.");
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});

		fr.add(pan);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setSize(380, 180);
		fr.setVisible(true);
	}// run 메소드

	// textfields에서 사용자가 입력한 값을 받아오기위한 함수
	public String getText(int i) {	// 추가에서 텍스트값 리턴
		return (fields[i].getText());
	}
	
	public String get_Text(int i) {	// 수정화면에서 텍스트값 리턴
		return (field[i].getText());
	}

	public void saveEvent(String name, String num, String birth, String group,
			String memo) { // 저장 이벤트

		InfoManager i = new InfoManager();
		i.Load_data();
		i.add_Phone_num(name, num, birth, group, memo); // Infos에 인스턴스 추가
		table.setModel(new table_system(i.Infos));																		
	}

	public static void main(String[] args) {
		Info_GUI ig = new Info_GUI();// 인스턴스화 하면서 생성자실행

	}

}
