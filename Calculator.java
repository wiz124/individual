
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;

    public boolean intchecker(String n){
 try{
        double a = Double.parseDouble(n);

 }catch(NumberFormatException e){
     return false;
 }
 return true;
    }

    private void rpnToResult()
    {
        // Stack used to hold calculation while process RPN
        Stack calculation = new Stack();

        for(int i=0; i< reverse_polish.size(); i++)
        {

            if(intchecker(reverse_polish.get(i)))// If the token is a number
            {
                // Push number to stack
                calculation.push(reverse_polish.get(i));
            }
            // else
            else
            {
                // Pop the two top entries
                Double fin = Double.parseDouble(calculation.pop().toString());
                // Based off of Token operator calculate result

                if(reverse_polish.get(i).equals("+")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                    result= fin + fin2;
                }
               else if(reverse_polish.get(i).equals("-")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                    result=fin-fin2;
                }
               else if(reverse_polish.get(i).equals("*")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                   result=fin*fin2;
                }
               else if(reverse_polish.get(i).equals("/")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                   result = fin/fin2;
                }
               else if(reverse_polish.get(i).equals("%")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                   result = fin%fin2;
                }
               else if(reverse_polish.get(i).equals("^")){
                    Double fin2 = Double.parseDouble(calculation.pop().toString());
                   result = Math.pow(fin2, fin);
                }
                else if(reverse_polish.get(i).equals("SQRT")){
                    result = Math.sqrt(fin);
                }


                // Push result back onto the stack
                calculation.push(result);
            }
        }
        // Pop final result and set as final result for expression
        this.result=Double.parseDouble(calculation.pop().toString());
    }
    // Print the expression, terms, and result
    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }
    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    private void tokensToReversePolishNotation () {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (!tokenStack.empty() && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (!tokenStack.empty() && isOperator((String) tokenStack.peek()))
                    {
                        if ( isPrecedent(token, (String) tokenStack.peek() )) {
                            reverse_polish.add((String)tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default:    // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (!tokenStack.empty()) {
            reverse_polish.add((String)tokenStack.pop());
        }

    }
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
        OPERATORS.put("^",2);
        OPERATORS.put("SQRT", 2);
    }
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeperator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0) ;
    }

    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0;  // term split starting index
        StringBuilder multiCharTerm = new StringBuilder();    // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeperator(c.toString())  ) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }
    public static void main(String[] args) {
        Calculator simpleMath = new Calculator("100 + 200  * 3");
        System.out.println("Simple Math\n" + simpleMath);

        Calculator parenthesisMath = new Calculator("(100 + 200)  * 3");
        System.out.println("Parenthesis Math\n" + parenthesisMath);

        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);

        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);

        Calculator allMath4 = new Calculator("(3) ^ 2 + 1");
        System.out.println("Exponents\n" + allMath4);

        Calculator allMath3 = new Calculator("16 SQRT + 1");
        System.out.println("Square root\n" + allMath3);

        Calculator var = new Calculator("a = 2 a + 1");
        System.out.println("Square root\n" + var);

    }
}
