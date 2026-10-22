package io.github.dunanttonfack;

public class ReverseString {
    public static String revereString (String s){
        String result = "";
        if( s != null){
            for(int i = s.length() - 1; i >= 0; i--){
                result += s.charAt(i); // Es muss kein StringBuilder verwendet werden.
            }
        }
        return  result;
    }

    public static void main(String[] args) {

        String s = "java";

        System.out.println(revereString(s));
    }
}
