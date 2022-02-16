package javabook;


import java.util.Date;
import java.util.GregorianCalendar;

public class PalindromeCheck {


    private static boolean palindromeCheck(String s) {
        String word=s;
        int wordLen = word.length();
        boolean palindrom=true;
        for(int i =0 ; i< wordLen/2; i++) {
            if( word.charAt(i) != word.charAt(wordLen -i-1) ) {
                palindrom=false;
                break;
            }
        }
        return palindrom;
    }


    private static int longestPalindromeInText(String s,int currentPalindromeLength) {

        int palindromeLenVar = currentPalindromeLength;
        if(s.length() < currentPalindromeLength) {
            return palindromeLenVar;
        }
        if(palindromeCheck(s)){
            return Math.max(s.length(),currentPalindromeLength);
        }else {
            for(int i = 0; i < s.length(); i++) {
                palindromeLenVar = Math.max(longestPalindromeInText(new StringBuilder(s).deleteCharAt(i).toString(),currentPalindromeLength),palindromeLenVar);
            }
        }
        return palindromeLenVar;
    }

    private static String longerString(String s1 , String s2) {
        return s1.length() > s2.length() ? s1 : s2;
    }

    private static String longestPalindromeInText(String s,String currentLongestPalindrome) {

        int currentPalindromeLen = currentLongestPalindrome.length();
        String tempPalindromeVar = currentLongestPalindrome;
        if(s.length() <= currentPalindromeLen) {
            return currentLongestPalindrome;
        }
        if(palindromeCheck(s)){
            return s ;
        }else {
            for(int i = 0; i < s.length(); i++) {
                tempPalindromeVar = longerString(longestPalindromeInText(new StringBuilder(s).deleteCharAt(i).toString(),tempPalindromeVar),tempPalindromeVar);
            }
        }
        return tempPalindromeVar;
    }

    public static void main(String a[]) {
        System.out.println("PC :"+palindromeCheck("raamaar"));
        System.out.println("PC :"+longestPalindromeInText("agaggapp",1));

        //System.out.println("PC TEXT :"+longestPalindromeInText("eeegeeksforskeeggeeks",""));

        String valueToTest = "eeegeeksforskeeggeeks";

        long multipleOfPalindrome = 1;
        LogUtil.log("Start : "+ new Date());

        for (int i = 0; i<  valueToTest.length() - 1;i++) {
            String string1 = valueToTest.substring(0,i+1);
            String string2 = valueToTest.substring(i+1);


//            multipleOfPalindrome = Math.max(longestPalindromeInText(string1,"").length()
//                    * longestPalindromeInText(string2,"").length(),multipleOfPalindrome);

            multipleOfPalindrome = Math.max(longestPalindromeInText(string1,1)
                    * longestPalindromeInText(string2,1),multipleOfPalindrome);

        }
        System.out.println("Max Multiple of Palindome :"+ multipleOfPalindrome);
        LogUtil.log("END : "+ new Date());


    }
}
