package sample;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class table_system extends AbstractTableModel {
	private final static String[] COLUMNS = { "이름", "전화번호", "생일", "그룹" };

	private List<PersonInfo> Infos;

	public table_system(ArrayList<PersonInfo> Infos) {//arraylist? list?
		this.Infos = Infos;
	}//생성자

	public int getRowCount() {
		return Infos.size();
	}//정보개수

	public int getColumnCount() {
		return COLUMNS.length;
	}//항목 길이

	public String getColumnName(int column) {
		return COLUMNS[column];
	}//항목 이름

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