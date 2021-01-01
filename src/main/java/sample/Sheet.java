package sample;

import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.sheet.XCellRangeAddressable;
import com.sun.star.sheet.XSheetCellCursor;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XUsedAreaCursor;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XCell;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.UnoRuntime;

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

    private XCellRange getUsedCellRange() {
        XSheetCellCursor cursor = this.sheet.createCursor();

        XUsedAreaCursor usedAreaCursor = UnoRuntime.queryInterface(
                com.sun.star.sheet.XUsedAreaCursor.class,
                cursor
                );

        usedAreaCursor.gotoEndOfUsedArea(false);

        XCellRange usedRange = UnoRuntime.queryInterface(
                com.sun.star.table.XCellRange.class,
                usedAreaCursor
                );

        return usedRange;
    }

    public int getUsedRowIndexMax() {
        XCellRange usedCellRange = getUsedCellRange();

        XCellRangeAddressable usedCellRangeAddressable = UnoRuntime.queryInterface(
                com.sun.star.sheet.XCellRangeAddressable.class,
                usedCellRange
                );
        CellRangeAddress cellRangeAddress = usedCellRangeAddressable.getRangeAddress();
        return cellRangeAddress.EndRow;
    }

    public int getUsedColumnIndexMax() {
        XCellRange usedCellRange = getUsedCellRange();

        XCellRangeAddressable usedCellRangeAddressable = UnoRuntime.queryInterface(
                com.sun.star.sheet.XCellRangeAddressable.class,
                usedCellRange
                );
        CellRangeAddress cellRangeAddress = usedCellRangeAddressable.getRangeAddress();
        return cellRangeAddress.EndColumn;
    }

}
