

public class Calculator
{

// Q1.b)evalution of postfix expression

public int evaluatePostFix(String s) throws InvalidPostfixException
   { 
       try{
           s+=" ";
        MyStack stack = new MyStack(); //Intializing a new array
       try {
        for(int i = 0; i < s.length();i++) // for loop for chekcing each digit
        {
            char c = s.charAt(i);
            char sh=s.charAt(0);
            if(sh=='-'|| sh=='+' || sh=='*'){  // checking if first character is operator or not if operator return InvalidPostfixException
                throw new InvalidPostfixException();
            }
 
            if( c == ' ')  //Skiping white spaces
            continue;
 
            else if (Character.isDigit(c))   // Collecting whole number between white spaces
            {
                int n=0;
                while(Character.isDigit(c))
                {
                    n = n*10 + (int)(c - '0'); //Adding number from unit digit to respective 10 th decimal number digit
                    i++;
                    c =s.charAt(i);
                }
 
                //push the number in stack
                stack.push(n);
            }
            else 
            { 
             //Invoking last two digit prior to operator
             int v1= (int) stack.pop();
             int v2= (int) stack.pop();
             switch(c) 
             {
                 case '+':
                 stack.push(v2+v1);
                 break;
                 case '-':
                 stack.push(v2-v1);
                 break;
                 case '*':
                 stack.push(v2*v1);
                 break;
             }
 
 
        }
        }
    
       int res=(int)stack.pop(); //Since our array is object type I type casted it into integer
       //returning our result of postfix
   
        return res;
    }catch(Exception e)
    {
        throw new EmptyStackException();
    }
    
       } catch( Exception e){
           throw new InvalidPostfixException(); //If Expression of postfix is invalid throw this exception
       }

    
    
   }






//1.C)
//Infix to Prefix Conversion 



public String convertExpression(String exp) throws InvalidExprException {
    MyStack s = new MyStack();
    String postfix = "";  
    try{   
                                                                     // Initialize postfix as empty string.
    for (int i=0;i<exp.length();i++)
    {                                                                   // Scanning each character from left to right.
        if (isOperator(exp.charAt(i))){                                 // If the character is an operator, pop two elements from stack, perform operation and push the result back.
            try {
                while (!s.isEmpty() && (char)s.top() != '(' && hasHigherPrecedence((char)s.top(), exp.charAt(i))){  //After operator is encountered check precedence and top element of stack
                    try {
                        postfix +=(char) s.top()+" ";
                    } catch (EmptyStackException e1) {
                        
                        e1.printStackTrace();
                    } try {
                        s.pop();
                    } catch (EmptyStackException e) {
                       
                        e.printStackTrace();
                    }
                }
            } catch (EmptyStackException e) {
               
                e.printStackTrace();
            }
            s.push(exp.charAt(i));
        }

        else if (isOperand(exp.charAt(i)) && postfix!=(exp+" ")) {  // If operand is encountered
            int j=i;
            while(isOperand(exp.charAt(j)) && j<exp.length()){     // I added all non white space separated element to postfix
            postfix += exp.charAt(j);
            j++;
            }
            postfix+=" ";
        }

        else if (exp.charAt(i) == '('){       // If opening bracket is encountered push it into the stack
            s.push(exp.charAt(i));
        }

        else if (exp.charAt(i) == ')'){      // If closing bracket is encountered perform all operations until opening brackets get encountered
            try {
                while (!s.isEmpty() &&(char)s.top() != '('){
                    postfix += (char)s.top()+" "; try {
                        s.pop();
                    } catch (EmptyStackException e) {
                      
                        e.printStackTrace();
                    }
                }
            } catch (EmptyStackException e) {
                
                e.printStackTrace();
            }
            try {
                s.pop();
            } catch (EmptyStackException e) {
               
                e.printStackTrace();
            }
        }
    }


    while (!s.isEmpty()){                // Until stack is not empty add all remaining elements of stack into postfix
        if((char)s.top()=='('){
            throw new InvalidExprException();
        }
        try {
            postfix += (char)s.top()+" ";
        } catch (EmptyStackException e1) {
           
            e1.printStackTrace();
        }try {
            s.pop();
        } catch (EmptyStackException e) {
            
            e.printStackTrace();
        }
    }
}catch(Exception e){
throw new InvalidExprException();
}

    return postfix.substring(0,postfix.length()-1); // skipped the last white space
                                                    // returned the postfix string which will be the result.
}


// All USEFULL METHOD I DECLARE BELOW
public static boolean isOperator(char C) {                         // Method for checking whether char is operator or not
    if (C == '+' || C == '-' || C == '*' || C == '/' || C == '%' )
        return  true;
    return false;
}

public static boolean isOperand(char C){                                // Method for checking whether given character is operand or not
    if (C >= '0' && C<='9' || C >= 'a' && C<='z' || C >= 'A' && C<='Z')
        return true;
    return false;
}

public static boolean hasHigherPrecedence(char op1, char op2){       // Method for checking precedence of operator
    int op1Weight = GetOperatorWeight(op1); 
    int op2Weight = GetOperatorWeight(op2);
    if (op1Weight == op2Weight){
        return true;
    }
    return op1Weight>op2Weight? true : false;
}

public static int GetOperatorWeight(char op){                    // Geting weightage of operator
    int weight = -1;
    switch (op)
    {
        case '+':
        case '-':
            weight = 1;
            break;
        case '*':
            weight = 2;
    }
    return weight;
}
}

class InvalidPostfixException extends Exception
{ 
}
class InvalidExprException extends Exception
{

}