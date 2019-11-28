package sample;

import java.io.File;
import java.util.List;

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
        Sheet sh0 = sheets.get(0);

        System.out.println(sh0.get(0, 0));
        System.out.println(sh0.get(1, 0));
        System.out.println(sh0.get(0, 1));
        System.out.println(sh0.get(1, 1));

        doc.close();
    }

    PropertyValue arg(String name, Object value) {
    	PropertyValue arg = new PropertyValue();
        arg.Name = name;
        arg.Value = value;
        return arg;
    }

}
