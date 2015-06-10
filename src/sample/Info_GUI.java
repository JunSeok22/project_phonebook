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
	JPanel pan = new JPanel();		//�⺻�г�
	JPanel searchPan = new JPanel();//�˻��г�
	JPanel addPan = new JPanel();	//�߰��г�
	JButton search_btn = new JButton("SEARCH");	//�˻� ����� �����ϴ� ��ư
	JButton save_btn = new JButton("����");		//�������� �����ϴ� ��ư 
	JButton search_Pan_btn = new JButton("�˻�"); //�˻��г� ȭ������ ��ȯ�ϴ� ��ư
	JButton add_Pan_btn = new JButton("�߰��ϱ�");	//�߰��г� ȭ������ ��ȯ�ϴ� ��ư
	JPanel search_BF = new JPanel();	//�˻�ȭ�鿡�� �˻��ʵ�� �˻���ư�� �ִ��г�
	JTextField searchBar = new JTextField(15);	//�˻� �ؽ�Ʈ �ʵ� + ����
	JTextArea resultArea = new JTextArea(20,25);	//�˻���� ǥ���� textarea + ũ��
	private JTextField[] fields; //������ �ʵ带 �迭�� ����(�߰� �гο��� �̸�,��ȣ,����,�׷� / �˻�ȭ�鿡�� �˻��ʵ�)
	CardLayout cl = new CardLayout();	//ȭ�� ��ȯ �Ҷ� ��� 

	public Info_GUI(){	//������
		run();
	}
	
	private void run(){
		pan.setLayout(cl); //ī�巹�̾ƿ����� ��ġ������ ����
		//�˻�ȭ�� (����ȭ��)
		search_BF.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		search_BF.add(searchBar);
		search_BF.add(search_btn);
		
		searchPan.setLayout(new BorderLayout());
		searchPan.add(search_BF,BorderLayout.NORTH);
		searchPan.add(resultArea,BorderLayout.CENTER);
		
		searchPan.add(add_Pan_btn,BorderLayout.SOUTH);
		
		
		
		//////////////////////////////������� �˻�ȭ�� ����
		//�߰�ȭ�� ����
		
		String[] labels = { "�̸�", "��ȣ", "����", "�׷�" }; //�� �̸� ����
		int[] widths = { 10, 10, 10, 10 }; //���� ����
		fields = new JTextField[labels.length];	//�󺧱��̸�ŭ �Է��ʵ����
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));	//���� �Ȱ��� �����ؼ� ���η� ���� �г� ����
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		JPanel btnPanel = new JPanel(new GridLayout(1,2));
		
		btnPanel.add(save_btn);
		btnPanel.add(search_Pan_btn);
		
		for (int i = 0; i < labels.length; i += 1) { 
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));//�ʵ��г� ���� �������� 
			
			fields[i] = new JTextField(); 	//������ �ʵ� ����
			fields[i].setColumns(widths[i]); //�ؽ�Ʈ�ʵ� ���̼���
			p.add(fields[i]);//�ʵ��гο� ������ �ʵ� �Է�
			
			fieldPanel.add(p);
			
			JLabel lab = new JLabel(labels[i],JLabel.RIGHT);//������ �� ����
			//lab.setLabelFor(fields[i]); //�� �̸�����
			labelPanel.add(lab);	//���г�
			
		}
					
		addPan.setLayout(new BorderLayout());
		addPan.add(btnPanel,BorderLayout.SOUTH);
		addPan.add(labelPanel, BorderLayout.WEST);
		addPan.add(fieldPanel,BorderLayout.CENTER);
		//////////////////////////////////////////////////////////������� �߰�ȭ�� ����
		
		// add_screen(); 	//�߰�ȭ���� �޼ҵ�� ���� ��������? �����ڴ� �ѹ��� ȣ��Ǵµ� ������ ������?
		
		
		pan.add(searchPan,"SP");	//�˻��г� ������ + ȣ���� SP
		pan.add(addPan,"AP");		//�߰��г�
		
		cl.show(pan, "SP"); //ùȭ���� �˻�ȭ��
		
		//���⼭���� �̺�Ʈ��� ���� �޼ҵ�
		save_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = getText(0); //ù��° �ʵ� �ؽ�Ʈ �Է°� 
			    String num = getText(1);
			    String birth = getText(2);
			    String group = getText(3);
				saveEvent(name,num,birth,group);	//������ ���� �޼ҵ�
				
			}
		});
		
		add_Pan_btn.addActionListener(new ActionListener(){	// �˻���ȣ���ư�� �̺�Ʈ ��� �ο�
			public void actionPerformed(ActionEvent e){
				cl.show(pan, "AP");	//btn�� ������ �߰�ȭ���� �����ش� (cardlayout)
			}
		});
		
		search_Pan_btn.addActionListener(new ActionListener(){	// �˻���ȣ���ư�� �̺�Ʈ ��� �ο�
			public void actionPerformed(ActionEvent e){
				cl.show(pan, "SP");	//btn�� ������ �˻�ȭ��(����)�� �����ش� (cardlayout)
			}
		});
		
		fr.add(pan);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//�����ư������ fr���� - ���α׷�����
		fr.pack();
		fr.setSize(1000,1000);
		fr.setVisible(true);
	}//run �޼ҵ�
	
	
	
	public void add_screen(){
	}
	
	// textfields���� ����ڰ� �Է��� ���� �޾ƿ������� �Լ� 
	public String getText(int i) {  
		return (fields[i].getText()); 
	} 
	
	public void saveEvent(String name, String num, String birth, String group){	//���� �̺�Ʈ
		
		
		InfoManager i = new InfoManager(); //infomanager�� �޼ҵ�� ������ ����ϱ����� �ν��Ͻ�ȭ
	    i.add_Phone_num(name,num,birth,group);	//Infos�� �ν��Ͻ� �߰�
		
	}

	public static void main(String[] args) {
		Info_GUI ig = new Info_GUI();//�ν��Ͻ�ȭ �ϸ鼭 �����ڽ���
		
		/*
		ig.fr.add(ig.pan);
		ig.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//�����ư������ fr���� - ���α׷�����
		ig.fr.pack();
		ig.fr.setSize(500,500);
		ig.fr.setVisible(true);
		*/
		
	}
		


}


