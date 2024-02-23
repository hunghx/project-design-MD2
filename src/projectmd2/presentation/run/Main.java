package projectmd2.presentation.run;

import projectmd2.business.design.IUser;
import projectmd2.business.designImpl.UserImpl;
import projectmd2.business.entity.User;
import projectmd2.business.util.InputMethods;
import projectmd2.business.util.ShopMessage;

public class Main {
    private static IUser userIpml = new UserImpl();
    public static User userLogin = null;
    public static void main(String[] args) {

        while (true){
            System.out.println("--------------Hùng Store---------------");
            System.out.println("1. Xem shop");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Đăng kí");
            System.out.println("4. Thóat");
            System.out.println("Nhap lưa chọn");
            byte choice   = InputMethods.getByte();
            switch (choice){
                case 1:
                    break;
                    case 2:
                        userIpml.login();
                    break;
                    case 3:
                        System.out.println("========DDang ki tai khoản===========");
                        userIpml.register();
                    break;
                    case 4:
                    break;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }

        }
    }
}
