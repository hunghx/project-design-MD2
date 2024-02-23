package projectmd2.presentation.user;

import projectmd2.business.util.InputMethods;
import projectmd2.presentation.run.Main;

public class HomePageView {
    public  static  void showHomePage(){
        System.out.println("HOME PAGE");
        System.out.printf("Chaof mưng ban %s den vơi trang web\n", Main.userLogin.getFullName());
        InputMethods.pressAnyKey();

    }
}
