package sample;

import sample.Model;

public class Main {

    public static void main(String[] args) {
        try {
            String path = args[0];
            Model model = new Model();
            model.main(path);

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
