import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaOneEncryptor {

    private String stringToEncrypt;

    public ShaOneEncryptor(String theString) {
        this.stringToEncrypt = theString;
    }

    public String convertStringToShaOne() throws NoSuchAlgorithmException {
        MessageDigest shaOne = MessageDigest.getInstance("SHA-1");
        byte[] byteArray = shaOne.digest(this.stringToEncrypt.getBytes());
        String hashedString = "";
        for (int currentByte = 0; currentByte < byteArray.length; currentByte++) {
            hashedString += Integer.toString((byteArray[currentByte] & 0xff) + 0x100, 16).substring(1);
        }
        return hashedString;
    }
}
