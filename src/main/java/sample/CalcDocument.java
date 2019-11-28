package sample;

import java.util.ArrayList;
import java.util.List;

import com.sun.star.container.XIndexAccess;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseable;

import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;

public class CalcDocument {

	private XComponent component;
	private XSpreadsheetDocument doc;

	public CalcDocument(XComponent component) {
		this.component = component;

        this.doc = UnoRuntime.queryInterface(
        		XSpreadsheetDocument.class, this.component
        		);
	}

	public List<Sheet> getSheets() {
		List<Sheet> sheets = new ArrayList<>();
        String[] sheetNames = this.doc.getSheets().getElementNames();
        String sheetName;

        for (int i = 0, len = sheetNames.length; i < len; i++) {
            sheetName = sheetNames[i];
            XSpreadsheet sheet = this.getSheetByIndex(i);
            sheets.add(new Sheet(sheet, sheetName));
        }

        return sheets;
	}

	private XSpreadsheet getSheetByIndex(int i) {
		XSpreadsheets sheets = this.doc.getSheets();

		XIndexAccess indexAccess = UnoRuntime.queryInterface(XIndexAccess.class, sheets);

		try {
			return UnoRuntime.queryInterface(
					XSpreadsheet.class, indexAccess.getByIndex(i));
		} catch (IndexOutOfBoundsException | WrappedTargetException e) {
			throw new RuntimeException(e);
		}

	}

	public void close() {
        XCloseable closable = UnoRuntime.queryInterface(
                XCloseable.class, this.component
                );

        try {
            closable.close(true);
        } catch (CloseVetoException e) {
            throw new RuntimeException(e);
        }
	}

}
