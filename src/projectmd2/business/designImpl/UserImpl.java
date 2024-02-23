package projectmd2.business.designImpl;

import org.mindrot.jbcrypt.BCrypt;
import projectmd2.business.design.IUser;
import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.util.*;
import projectmd2.presentation.admin.DashBoardView;
import projectmd2.presentation.run.Main;
import projectmd2.presentation.user.HomePageView;

import java.util.Date;
import java.util.List;

public class UserImpl implements IUser {
    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void displayAll() {

    }

    @Override
    public User inputData() {
        User user = new User();
        user.setId(getNewId());
        System.out.println("ID : "+user.getId());
        System.out.println("Nhập username ");
        while (true){
            String username = InputMethods.getString();
            if (ShopValidate.checkUserNameIsValid(username)){
                // kiểm tra  trùng lặp
                if (!existByUsername(username)){
                    user.setUsername(username);
                    break; // thoat khoi vong lawp
                }
                System.err.println(ShopMessage.ERROR_USERNAME_EXIST);
            }else {
                System.err.println(ShopMessage.ERROR_USERNAME_INVALID);
            }
        }
        System.out.println("Nhâp password");
        String pass = InputMethods.getString();
        user.setPassword(BCrypt.hashpw(pass,BCrypt.gensalt(5))); // mã hóa pass
        System.out.println("Nhâp fullname");
        user.setFullName(InputMethods.getString());
        System.out.println("Nhâp address");
        user.setAddress(InputMethods.getString());
        System.out.println("Nhâp sdt");
        user.setPhone(InputMethods.getString());
        System.out.println("Nhâp email");
        user.setEmail(InputMethods.getString());
        System.out.println("Nhâp Ngay sinh");
        user.setBirthday(InputMethods.getDate());
        System.out.println("Nhâp gioi tính(true/ false)");
        user.setSex(InputMethods.getBoolean());
        user.setCreatedAt(new Date());
        user.setRoleName(RoleName.USER);
        return user;
    }

    @Override
    public void displayData() {

    }

    @Override
    public void login() {
        System.out.println("Nhập username");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password= InputMethods.getString();
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        User userLogin = userList.stream().filter(u->u.getUsername().equals(username) && BCrypt.checkpw(password,u.getPassword())).findFirst().orElse(null);
        if (userLogin==null){
            System.err.println(ShopMessage.ERROR_ACCOUNT_NOTFOUND);
            System.out.println("Bạn có muốn nhập lại không ? (Y,N)");
            if (InputMethods.getChar()=='Y'){
                login();
            }
        }else {
            switch (userLogin.getRoleName()){
                case ADMIN:
                case MOD:
                    Main.userLogin = userLogin;
                    DashBoardView.showDashBoardView();
                    break;
                case USER:
                    if (userLogin.isBlock()){
                        System.err.println(ShopMessage.ERROR_ACCOUNT_ISBLOCK);
                    }else {
                        System.out.println("Đăng nhập thành công");
                        Main.userLogin = userLogin;
                        HomePageView.showHomePage();
                    }
                    break;
            }
        }

    }

    @Override
    public void register() {
        User user = inputData();
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        userList.add(user);
        IOFile.writeToFile(ShopConstants.USER_PATH,userList);
        System.out.println("Đăng kí thành cng");
        login();
    }

    @Override
    public int getNewId() {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        int idMax = 0;
        for (User u : userList){
            if (u.getId()>idMax){
                idMax = u.getId();
            }
        }
        return idMax+1;
    }

    @Override
    public boolean existByUsername(String username) {
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        return userList.stream().anyMatch(t -> t.getUsername().equals(username));
    }
}
