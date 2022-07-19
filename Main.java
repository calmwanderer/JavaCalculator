import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(calc(in.nextLine()));
        return;
    }
    public static String calc(String expression) 
    {
        int number0;
        int number1;
        int exception_count = 0;
        //
        String exp = expression.replaceAll(" ", "");
        //
        String[] ops = new String[] {"+", "-", "*", "/"};
        for (int i = 0; i < ops.length; ++i) 
        {
            int splitter_index = exp.indexOf(ops[i]);
            if (splitter_index >= 0) 
            {
                try { number0 = Integer.parseInt(exp.substring(0, splitter_index)); } 
                catch (NumberFormatException e) { number0 = RomanToArabic(exp.substring(0, splitter_index)); ++exception_count;
                }  
                try { number1 = Integer.parseInt(exp.substring(splitter_index+1)); } 
                catch (NumberFormatException e) { number1 = RomanToArabic(exp.substring(splitter_index +1)); ++exception_count;
                }  
                //

                try {
                if (number0 < 1 || number0 > 10) throw new Exception("Введенное выражение вне условий ТЗ");
                if (number1 < 1 || number1 > 10) throw new Exception("Введенное выражение вне условий ТЗ");
                if (exception_count == 1)        throw new Exception("Введенное выражение вне условий ТЗ");
                } catch(Exception exc) { System.out.println(exc);System.exit(0);}
                //
                if (i == 0) number0 += number1;
                if (i == 1) number0 -= number1;
                if (i == 2) number0 *= number1;
                if (i == 3) number0 /= number1;
                //
                boolean isRoman = exception_count == 2;
                if (isRoman && number0 < 1) {try {throw new Exception("Невозможно записать вывод римскими числами");} catch(Exception exc) { System.out.println(exc);System.exit(0); }}
                return (isRoman) ? ArabicToRoman(number0) : Integer.toString(number0);
            }
        }
        return "";
    }
    
    static int RomanToArabic(String number) 
    {
        int ret = 0;
        int last_cypher = 0;
        int cypher = 0;
        char sym;
        
        for (int i = number.length()-1; i >= 0; --i) {
        sym = number.charAt(i);
        //
        if (sym == 'M') cypher = 1000;
        if (sym == 'D') cypher = 500;
        if (sym == 'C') cypher = 100;
        if (sym == 'L') cypher = 50;
        if (sym == 'X') cypher = 10;
        if (sym == 'V') cypher = 5;
        if (sym == 'I') cypher = 1;
        //
        ret += (cypher >= last_cypher) ? cypher:-cypher;
        last_cypher = cypher;
        }
        return ret;
    }
    
    static String ArabicToRoman(int number) 
    {
        int num = number;
        String ret = new String();
        while (num > 0) 
        {
            if (num < 5 && num >= 4) {ret = ret + "I";num+=1;}//IV 
            if (num < 10 && num >= 9) {ret = ret + "I";num+=1;}//IX
            if (num < 50 && num >= 40) {ret = ret + "X";num+=10;}//XL
            if (num < 100 && num >= 90) {ret = ret + "X";num+=10;}//XC
            if (num < 500 && num >= 400) {ret = ret + "C";num+=100;}//CD
            if (num < 1000 && num >= 900) {ret = ret + "C";num+=100;}//CM
            //
            if (num >= 1000) {num-=1000;ret = ret + "M";continue;}
            if (num >= 500) {num-=500;ret = ret + "D";continue;}
            if (num >= 100) {num-=100;ret = ret + "C";continue;}
            if (num >= 50) {num-=50;ret = ret + "L";continue;}
            if (num >= 10) {num-=10;ret = ret + "X";continue;}
            if (num >= 5) {num-=5;ret = ret + "V";continue;}
            if (num >= 1) {num-=1;ret = ret + "I";continue;}
        }
        return ret;
    }
}













