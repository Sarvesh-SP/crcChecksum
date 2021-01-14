package crcChecksum;
import java.util.*;
public class crc {
    static String msg;
    static String genPoly = "10001000000100001";
    static char t[] = new char[128];
    static char cs[] = new char[128];
    static char g[] = new char[128];

    static int mlen, glen, x, c, test, flag = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the message: ");
        msg = in.nextLine();
        mlen = msg.length();
        for (int i = 0; i<mlen;i++){
            t[i] = msg.charAt(i);
        }
        System.out.println("Predefined Generator Polynomial is" + genPoly);
        g = genPoly.toCharArray();
        glen = genPoly.length();

        for (int i = mlen; i < (mlen+glen - 1); i++){
            t[i] = '0';
        }

        System.out.println("Zero extented msg = "+ new String(t));
        crc();
        System.out.println("Checksum is :" + new String(cs));

        for (x = mlen; x < (mlen+glen - 1); x++) {
            t[x] = cs[x-mlen];
        }
        System.out.println("Final codeWord generated is : "+ new String(t));

        System.out.println("Test error detection: ");
        test = in.nextInt();

        if (test == 1) {
            System.out.println("Position Error");
            x = in.nextInt();

            t[x] = (t[x] == '0') ? '1' : '0';

            System.out.println("Errorness data: " + new String(t));
        }

        crc();

        for (x = 0;x < (glen-1); x++){
            if (cs[x] == '1'){
                flag = 1;
                break;
            }
        }
        if (flag == 1){
            System.out.println("Error was dected");
        }else {
            System.out.println("No Error");
        }
    }

    public static void crc(){
        for (x = 0; x < glen; x++){
            cs[x] = t[x];
            do {
                if (cs[0] == '1'){
                    xor();
                }
                for( c = 0; c < glen-1; c++) {
                    cs[c] = cs[c + 1];
                }
                cs[c] = t[x++];
            }while (x <= mlen+glen-1);

        }
    }

    public static void xor() {
        for (c = 1;c < glen; c++){
            cs[c] = ((cs[c] == g[c])?'0':'1');
        }
    }
}
