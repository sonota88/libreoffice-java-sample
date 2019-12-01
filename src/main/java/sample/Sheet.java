package sample;

import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.table.XCell;

public class Sheet {

	private XSpreadsheet sheet;

	private String name;

	public Sheet(XSpreadsheet sheet, String name) {
	    this.sheet = sheet;
	    this.name = name;
	}

	public String get(int ci, int ri) {
        XCell cell;

        try {
			cell = this.sheet.getCellByPosition(ci, ri);
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		}

		return cell.getFormula().toString();
	}

    public Integer getInt(int ci, int ri) {
        XCell cell;

        try {
            cell = this.sheet.getCellByPosition(ci, ri);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }

        return Integer.valueOf(cell.getFormula());
    }

    public Double getDouble(int ci, int ri) {
        XCell cell;

        try {
            cell = this.sheet.getCellByPosition(ci, ri);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }

        return Double.valueOf(cell.getFormula());
    }

    public void set(int ci, int ri, String val) {
        XCell cell;
        try {
            cell = this.sheet.getCellByPosition(ci, ri);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
        cell.setFormula(val);
    }

    public String getName() {
        return this.name;
    }

}
