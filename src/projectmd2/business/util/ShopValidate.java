package projectmd2.business.util;

import java.util.regex.Pattern;

public class ShopValidate {
    // username phải ít nhât 6 kí tự, không có kí ự đặc biệt , không trung lặp
    public  static  boolean checkUserNameIsValid(String username){
        return Pattern.matches("^[0-9a-zA-Z]{6,}$",username);
    }
}
