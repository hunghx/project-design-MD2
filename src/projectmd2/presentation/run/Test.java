package projectmd2.presentation.run;

import org.mindrot.jbcrypt.BCrypt;
import projectmd2.business.designImpl.UserImpl;
import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.util.IOFile;
import projectmd2.business.util.InputMethods;
import projectmd2.business.util.ShopConstants;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("admin");
        u.setPassword(BCrypt.hashpw("admin",BCrypt.gensalt(5)));
        u.setFullName("admin");
        u.setRoleName(RoleName.ADMIN);
        List<User> userList = IOFile.readFromFile(ShopConstants.USER_PATH);
        userList.add(u);
        IOFile.writeToFile(ShopConstants.USER_PATH,userList);
    }
}
