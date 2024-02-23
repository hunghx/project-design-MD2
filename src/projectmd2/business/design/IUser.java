package projectmd2.business.design;

import projectmd2.business.entity.User;

public interface IUser extends IDesign<User> {
    void login();
    void register();
    boolean existByUsername(String username);
}
