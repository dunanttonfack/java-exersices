package io.github.dunant74;

public class ReverseString {
    public static String reverString (String s){
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

        System.out.println(reverString(s));
    }
}
