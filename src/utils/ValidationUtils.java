package utils;
public class ValidationUtils {
    public static boolean isVEmail(String email) {
        if (email == null || email.trim().isEmpty()){
            return false;
        }
        if (email.contains("@") && email.contains(".") &&
            email.indexOf("@") > 0 && email.indexOf("@") < email.length() - 1) {
            return true;
        }
        return false;
    }
    
    public static boolean isVScore(double score) {
        if  (score >= 0.0 && score <= 10.0){
            return true;
        }
        return false;
    }
}
