import java.util.*;
public class calculator
{
    /**
     *Defines the operators and their class numbers
     */
    public static String output;// Makes it so I don't need to use an arraylist
     static int operator(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }

    static String position(String output)// runs through the output
    {
        String result = new String("");
        // Intializes a stack    
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < output.length(); ++i)
        {
            char c = output.charAt(i);

            // Checks to see if the character is a number
            if(Character.isDigit(c))
            {
                result += c;
            }// End first if

            else if(c == '(' )
            {
                stack.push(c);
            }// End else if 1

            else if(c == ')')
            {
               while(!stack.isEmpty() && stack.peek() != '(')
               {
                   result += stack.pop();
               }

               stack.pop();
            } // End else if 2

            else
            {
                while(!stack.isEmpty() && operator(c) <= operator(stack.peek()))
                {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty())
        {
            if(stack.peek() == '(')
            {
                return "No";
            }
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        output = sc.next();
        System.out.println(position(output));
    }
}