package handlers;

public class PasswordHandler{
    final private static String keys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final private static int range = 3;

    public static String encode(String pass) {
        String encoded = "";
        
        for (char c: pass.toCharArray()) {    
            if (Character.isLetter(c)) {
                int ind = keys.indexOf(Character.toUpperCase(c));
                String to_pass;
                if (ind + range < keys.length()) to_pass = String.valueOf(keys.charAt(ind + range));
                else to_pass = String.valueOf(keys.charAt(ind + range - keys.length()));
                if (Character.isLowerCase(keys.charAt(ind))) to_pass = to_pass.toLowerCase();
                encoded += to_pass;
            } else {
                if (Character.isDigit(c)) {
                    int tmp = ((int)c + range) % 10;
                    encoded += String.valueOf(tmp);
                } else encoded += String.valueOf(c);
            }
        }

        return encoded;
    }

    public static String decode(String pass) {
        String decoded = "";
        
        for (char c: pass.toCharArray()) {
            if (Character.isLetter(c)) {
                int ind = keys.indexOf(Character.toUpperCase(c));
                String to_pass;
                if (ind - range >= 0) to_pass = String.valueOf(keys.charAt(ind - range));
                else to_pass = String.valueOf(keys.charAt(keys.length() - (ind - range)));
                decoded += to_pass;
            } else {
                if (Character.isDigit(c)) {
                    int tmp;
                    if ((int)c < 3) tmp = 10 + (int)c - range;
                    else tmp = (int)c - range;
                    decoded += String.valueOf(tmp);
                } else decoded += String.valueOf(c);
            }
        }

        return decoded;
    }
}