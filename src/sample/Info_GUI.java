package sample;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

public class Info_GUI extends javax.swing.JFrame {
	JFrame fr = new JFrame("PersonInfo Book");
	JPanel pan = new JPanel(); // �⺻�г�
	JPanel searchPan = new JPanel();// �˻��г�
	JPanel addPan = new JPanel(); // �߰�ȭ��
	JButton search_btn = new JButton("SEARCH"); // �˻� ����� �����ϴ� ��ư
	JButton save_btn = new JButton("�����ϱ�"); // �������� �����ϴ� ��ư
	JButton add_btn = new JButton("�߰��ϱ�"); // �߰��ϱ�
	JButton Info_btn = new JButton("����"); // �����г� ȭ������ ��ȯ�ϴ� ��ư
	JButton del_btn = new JButton("�����ϱ�");
	JButton modify_btn = new JButton("�����ϱ�"); 
	JPanel search_BP = new JPanel(); // �˻�ȭ�鿡�� �˻��ʵ�� �˻���ư�� �ִ��г�
	JPanel menu_btn_Pan = new JPanel(); // �˻�ȭ�鿡�� �ϴ��� �����޴���ư�� �ִ� �г�
	JPanel Login = new JPanel(); // ��ó�� �α��� �г�
	JTextField searchBar = new JTextField(15); // �˻� �ؽ�Ʈ �ʵ� + ����
	JComboBox<String> c = new JComboBox<String>(); // �׷����� ǥ��
	private JTextField[] fields; // ������ �ʵ带 �迭�� ����(�߰� �гο��� �̸�,��ȣ,����)
	JTable table = new JTable();// �˻������ ���̺��������� ����
	JTextArea ja; // memo area
	
	public Info_GUI() { // ������
		run();
	}

	private void run() {
		CardLayout cl = new CardLayout(); // ȭ�� ��ȯ �Ҷ� ���
		pan.setLayout(cl); // ī�巹�̾ƿ����� ��ġ������ ����
		// �α���ȭ��
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
		JPanel log_btnP = new JPanel();// �α���ȭ���� ��ư �г�
		JButton login = new JButton("�α������� ����");
		JButton enter = new JButton("����");
		log_btnP.add(login);
		log_btnP.add(enter);
		Login.add(log_btnP, BorderLayout.SOUTH);
		// /////////////�α���ȭ�� ����

		// �˻�ȭ�� (����ȭ��)
		JScrollPane scrollP = new JScrollPane(); // ���̺��� �� ��ũ�� �г�
		table.setModel(new table_system(new ArrayList<PersonInfo>()));//
		scrollP.setViewportView(table);

		search_BP.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); //��ư �г� ��ġ������ , ���� ���� ��ũ��
		search_BP.add(searchBar);
		search_BP.add(search_btn);
		menu_btn_Pan.add(add_btn);
		menu_btn_Pan.add(Info_btn);
		menu_btn_Pan.add(del_btn);
		menu_btn_Pan.add(modify_btn);

		table.setModel(new table_system(new ArrayList<PersonInfo>()));

		searchPan.setLayout(new BorderLayout());
		searchPan.add(search_BP, BorderLayout.NORTH);
		searchPan.add(scrollP, BorderLayout.CENTER);

		searchPan.add(menu_btn_Pan, BorderLayout.SOUTH);
		// ////////////////////////////������� �˻�ȭ�� ����

		pan.add(searchPan, "SP"); // �˻��г� ������ + ȣ���� SP
		pan.add(Login, "LP"); // �����г� ��ȯ
		cl.show(pan, "LP"); // ùȭ���� �α���ȭ��

		// ���⼭���� �̺�Ʈ��� ���� �޼ҵ�
		add_btn.addActionListener(new ActionListener() { // �߰� ��ư
			public void actionPerformed(ActionEvent e) {
				JFrame j = new JFrame("�߰��ϱ�");
				// �߰�ȭ�� ����
				String[] labels = { "�̸� : ", "��ȣ : ", "���� : " }; // �� �̸� ����
				int[] widths = { 10, 10, 10 }; // ���� ����
				fields = new JTextField[labels.length]; // �󺧱��� ��ŭ �Է��ʵ���� �׷���
														// combo
				JPanel fieldPanel = new JPanel(new GridLayout(
						labels.length + 2, 1)); // �׷��ʵ� + memo area ���� 5�� ��
				JPanel btnPanel = new JPanel();

				btnPanel.add(save_btn);

				for (int i = 0; i < labels.length; i += 1) {
					JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
					JLabel lab = new JLabel(labels[i], JLabel.RIGHT);// ������ ��
																		// ����
					p.add(lab); // ���г�
					fields[i] = new JTextField(); // ������ �ʵ� ����
					fields[i].setColumns(widths[i]); // �ؽ�Ʈ�ʵ� ���̼���
					p.add(fields[i]);// �ʵ��гο� ������ �ʵ� �Է�
					fieldPanel.add(p);
				}
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel lab = new JLabel("�׷� : ", JLabel.RIGHT);
				p.add(lab);
				c.addItem("ģ��");
				c.addItem("ģô");
				c.addItem("�б�");
				c.addItem("����");
				c.addItem("����");
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
				j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // �����ư������ ���α׷�����
				j.setSize(300, 400);
				j.setVisible(true);
			}
		});// �߰���� �޼ҵ�

		search_btn.addActionListener(new ActionListener() { // �˻���ư
			public void actionPerformed(ActionEvent e) {
				String search_quary = searchBar.getText();	//�˻� ��
				try {
					BufferedReader br = new BufferedReader(new FileReader("personInfo.txt"));
					ArrayList<PersonInfo> infodata = new ArrayList<PersonInfo>(); 
					String current = null;
					while ((current = br.readLine()) != null) {
						String temp[] = current.split("/");
						int in = Integer.parseInt(temp[0]);
						if (search_quary.contains(",")) {	//	,�� �Էµ� ���
							String spt[] = search_quary.split(",");
							if((temp[1].equals(spt[0]) && temp[4].equals(spt[1])) || (temp[4].equals(spt[0]) && temp[1].equals(spt[1]))){
								// (�̸�,�׷� )�˻�  �Ǵ� (�׷�,�̸� )�˻�
								infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
							}			
						}
						else if(search_quary.equals("all")) {	//��� ����ǥ��
							infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
						}
						else {	//�̸��� �Է� �Ǵ� �׷츸 �Է�
							if(temp[1].equals(search_quary) || temp[4].equals(search_quary)){
								infodata.add(new PersonInfo(in,temp[1],temp[2],temp[3],temp[4],temp[5]));
							}
						}

					}
					br.close();
						table.setModel(new table_system(infodata));
					
				} catch (FileNotFoundException e1) {
					System.out.println("�ش� ������ �����ϴ�.");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		modify_btn.addActionListener(new ActionListener() {	//������ư
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
			}
		});

		del_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				InfoManager i = new InfoManager();
				i.Load_data();
				i.Infos.remove(row); // ����
				i.save_data();	//������ ���̽� �ٽ� ��
				table.setModel(new table_system(i.Infos));
			}
		});

		save_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = getText(0); // ù��° �ʵ� �ؽ�Ʈ �Է°�
				String num = getText(1);
				String birth = getText(2);
				String group = (String) c.getSelectedItem();// combobox's item
				String memo = ja.getText();
				saveEvent(name, num, birth, group, memo); // ������ ���� �޼ҵ�

			}
		});
		
		
		///																							������!!!!!1
		Info_btn.addActionListener(new ActionListener() { // ��ư�Է��� �ϸ� ��Ͽ��� ���õ�
															// ��ü�� ������ ǥ��
			public void actionPerformed(ActionEvent e) {
				// ����ȭ�� ����
				JPanel Info_Pan = new JPanel();
				Info_Pan.setLayout(new BorderLayout());
				JPanel IbtnP = new JPanel();
				JScrollPane js = new JScrollPane();
				JTextArea show = new JTextArea();
				js.add(show);
				IbtnP.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20)); // ��ư ��������
				JButton bb = new JButton();
				IbtnP.add(bb);
				Info_Pan.add(js, BorderLayout.CENTER);// �ڼ��� ���� �����ֱ�
				Info_Pan.add(IbtnP, BorderLayout.SOUTH);// ��ư�г�
				// /////////////////////////������� ����ȭ�� ����
				
				JFrame j = new JFrame();
				j.add(Info_Pan);
				j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
				j.setSize(500, 500);
				j.setVisible(true);
			}
		});

		
		enter.addActionListener(new ActionListener() { // �˻���ȣ���ư�� �̺�Ʈ ��� �ο� ..
														// ��α���
			public void actionPerformed(ActionEvent e) {
				cl.show(pan, "SP"); // btn�� ������ �˻�ȭ��(����)�� �����ش� (cardlayout)
				fr.setSize(800, 900);
			}
		});

		login.addActionListener(new ActionListener() { // ����ȣ���ư�� �̺�Ʈ ��� �ο�..
														// �α���
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader br = new BufferedReader(new FileReader("LoginInfo.txt"));
					String current = null;
					while ((current = br.readLine()) != null) {
						String temp[] = current.split("/");
						if(f.getText().equals(temp[0]) && ff.getText().equals(temp[1])){
							cl.show(pan, "SP"); // btn�� ������ �˻�ȭ��(����)�� �����ش� (cardlayout)
							fr.setSize(800, 900);
						}
						else{
							JFrame a = new JFrame();
							JPanel b = new JPanel();
							b.setLayout(new FlowLayout(FlowLayout.CENTER));
							JLabel c = new JLabel("���̵� �Ǵ� �н����� �߸��Է�");
							b.add(c);
							a.add(b);
							a.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
							a.pack();
							a.setSize(300, 150);
							a.setVisible(true);
							System.out.println("���̵� �Ǵ� �н����� �߸��Է�");
						}
					}
				}
				catch (FileNotFoundException e1) {
					System.out.println("�ش� ������ �����ϴ�.");
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
	}// run �޼ҵ�

	// textfields���� ����ڰ� �Է��� ���� �޾ƿ������� �Լ�
	public String getText(int i) {
		return (fields[i].getText());
	}

	public void saveEvent(String name, String num, String birth, String group,
			String memo) { // ���� �̺�Ʈ

		InfoManager i = new InfoManager();
		i.Load_data();
		i.add_Phone_num(name, num, birth, group, memo); // Infos�� �ν��Ͻ� �߰�
		table.setModel(new table_system(i.Infos));																		
	}

	public static void main(String[] args) {
		Info_GUI ig = new Info_GUI();// �ν��Ͻ�ȭ �ϸ鼭 �����ڽ���

	}

}
