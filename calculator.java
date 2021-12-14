/**
 * Corey Klevan
 * December 2nd, 2021
 */
import java.util.*;
public class calculator
{
    private static int priority(char c) 
    {
        // Gives operators a priority number 
        switch(c)
        {
            case '+':
            case '-':
                return 1;
            
            case '*':
            case '/':
                return 2;
            
            case '^':
                return 3;
        }// End Switch
        return -1;
    }// End priority
    public static ArrayList<String> before(String input)
    {
        ArrayList<String> result = new ArrayList<>(); // Starts an Empty String
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            String acc = "";
            while(Character.isLetterOrDigit(c))
            {
                acc = acc + c;
                if (i + 1 >= input.length()) 
                {
                    break;
                }// End if
                c = input.charAt(++i);
            }// End while
            if(!acc.isEmpty())
            {
                result.add(acc);
            }// End if
            if(c == '(')
            {
                stack.push(c);
            }// End if
            else if(c == ')')
            {
                while(!stack.isEmpty() && stack.peek() != '(')
                {
                    result.add(stack.pop() + "");
                }// End while
                stack.pop();
            }// End else if
            else if(!Character.isDigit(c))// Character is + - * / ^
            {
                while(!stack.isEmpty() && priority(c) <= priority(stack.peek()))
                {
                    result.add(stack.pop() + "");
                } // End while
                stack.push(c);
            }// End Else if
        }// End For
        while(!stack.isEmpty())
        {
            result.add(stack.pop() + "");
        }// End while
        return result;
    }// End before

    public static double after(ArrayList<String> afterFix)
    {
        Stack<Double> stack = new Stack<>(); // Temp Stack
        for(int i = 0; i < afterFix.size(); i++)
        {
            String c = afterFix.get(i);
            if(Character.isDigit(c.charAt(0)))
            {
                stack.push(Double.parseDouble(c));
            }// End If
            else
            {
                double num1 = stack.pop();
                double num2 = stack.pop();

                switch(c)
                {
                    case "+":
                        stack.push(num1 + num2);
                    break;

                    case "-":
                        stack.push(num2 - num1);
                    break;

                    case "*":
                        stack.push(num1 * num2);
                    break;

                    case "/":
                        stack.push(num2 / num1);
                    break;
                }// End switch
            }//End Else
        }// End for
        return stack.pop();
    }// End after
    public static void main(String[] args)
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your equation");
            String input = sc.nextLine();
            input = input.replace(" ", "");
            //System.out.print("Equation: " + before(input));
            ArrayList<String> afterFix = before(input);
            System.out.println(afterFix);
            System.out.println(after(afterFix));
        }
    }
}// End calculator
