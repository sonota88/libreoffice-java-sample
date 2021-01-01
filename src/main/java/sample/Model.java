package sample;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class Model {

    public void main(String path) throws Exception {
    	XComponentContext context = Bootstrap.bootstrap();
    	XMultiComponentFactory mcf = context.getServiceManager();

    	Object desktop = mcf.createInstanceWithContext(
                "com.sun.star.frame.Desktop", context
                );

    	XComponentLoader componentLoader = UnoRuntime.queryInterface(XComponentLoader.class, desktop);

        // GUI表示なし
        PropertyValue[] args = {
        		arg("Hidden", true)
        		};

        String fileUrl = "file://" + new File(path).getCanonicalPath();
        
        XComponent component = componentLoader.loadComponentFromURL(
            fileUrl, "_blank", 0, args
            );

        CalcDocument doc = new CalcDocument(component);

        // -------------
        
        List<Sheet> sheets = doc.getSheets();
        for (int si=0; si < sheets.size(); si++) {
            Sheet sheet = sheets.get(si);
            System.out.println("----------------");
            System.out.println(
                    String.format("sheets[%d] (%s)", si, sheet.getName())
                    );
        }

        System.out.println("----------------");
        // 名前でシートを取得
        Sheet sheet = doc.getSheetByName("Sheet1");
        System.out.println("(1, 1) => " + sheet.get(1, 1));
        sheet.set(1, 1, "(1, 1) " + new Date());
        System.out.println("(1, 1) => " + sheet.get(1, 1));

        System.out.println("----------------");
        // 行インデックス・列インデックスの最大値
        System.out.println("Sheet1 row index max: " + sheet.getUsedRowIndexMax());
        System.out.println("Sheet1 column index max: " + sheet.getUsedColumnIndexMax());

        System.out.println("----------------");
        sheet.set(1, 2, "123");
        sheet.set(1, 3, "12.34");

        // 整数として取得
        System.out.println("getInt   => " + sheet.getInt(1, 2));
        // 浮動小数として取得
        System.out.println("getFloat => " + sheet.getDouble(1, 3));

        System.out.println("----------------");
        String str = sheet.get(1, 4);
        int count;
        if (Objects.equals(str, "")) {
            count = 0;
        } else {
            count = sheet.getInt(1, 4);
        }
        System.out.println(count);

        sheet.set(1, 4, String.valueOf(count + 1));
        System.out.println("count => " + sheet.get(1, 4));

        // 上書き保存
        doc.save();

        doc.close();
    }

    PropertyValue arg(String name, Object value) {
    	PropertyValue arg = new PropertyValue();
        arg.Name = name;
        arg.Value = value;
        return arg;
    }

}
