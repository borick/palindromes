package palindrome;

import java.io.*;
import org.json.*;
import java.util.*;

public class Palindrome {
    
    public Vector<String> list = new Vector<String>();
    
    protected void loadData() {
        
        try {
            FileInputStream fstream = new FileInputStream("src/main/java/palindrome/input.txt");  
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                if (!strLine.equals("")) { // ignore some obviously bad strings
                    JSONObject obj;
                    System.out.println ("Reading: " + strLine);
                    try {
                        obj = new JSONObject(strLine);
                        if (obj.has("key")) {
                            String tmpKey = (String)obj.get("key");
                            if (checkPalindrome(tmpKey)) {
                                list.add(tmpKey);
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Loaded " + list.size() + " elements.");
    }
    
    private static boolean checkPalindrome(String original) {
        int length = original.length();
        String reverse = "";
        original = original.toLowerCase();
        for ( int i = length - 1; i >= 0; i-- ) {
            reverse = reverse + original.charAt(i);
        }
        return original.equals(reverse);
    }
    
    public static void main(String args[]) throws IOException
    {
        Palindrome p = new Palindrome();
        p.loadData();
    }
}