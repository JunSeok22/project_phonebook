package sample;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class table_system extends AbstractTableModel {
	private final static String[] COLUMNS = { "�̸�", "��ȭ��ȣ", "����", "�׷�" };

	private List<PersonInfo> Infos;

	public table_system(ArrayList<PersonInfo> Infos) {//arraylist? list?
		this.Infos = Infos;
	}//������

	public int getRowCount() {
		return Infos.size();
	}//��������

	public int getColumnCount() {
		return COLUMNS.length;
	}//�׸� ����

	public String getColumnName(int column) {
		return COLUMNS[column];
	}//�׸� �̸�

	public Object getValueAt(int rowIndex, int columnIndex) {
		PersonInfo pi = Infos.get(rowIndex);		
		switch (columnIndex) {
		case 0:
			return pi.get_name();
		case 1:
			return pi.get_phone_num();
		case 2:
			return pi.get_birth();
		case 3:
			return pi.get_group();
				
		}
		return null;
	}

}