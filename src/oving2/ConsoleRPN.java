package oving2;

import java.util.*;
import java.util.Stack;
import acm.program.ConsoleProgram;
//import oving2.RPN;

/*
 * @startuml
 * class ConsoleProgram {
 * }
 * class ConsoleRPN {
 * 	Stack<Double> operandStack
 * 	+void init()
 * 	+void run()
 * }
 * ConsoleProgram <|-- ConsoleRPN
 * @enduml
 */
public class ConsoleRPN extends ConsoleProgram {
	Stack<Double> operandStack;
	RPN rpn;
	public void init(){
		operandStack = new Stack<Double>();
		rpn = new RPN();
	}
	public void run(){
		while(true){
			getInput();
		}
	}
	
	private void getInput() {
		String inputString = readLine();
		double input;
		if(rpn.knownOperator(inputString.charAt(0))){
			performOperation(inputString.charAt(0));
		} else {
			try{
				input = Double.parseDouble(inputString);
				operandStack.add(input);
				
			} catch(NumberFormatException e){ badInput("Your input is not a number or a known operator."); }
		}
		printStack();
	}
	private void performOperation(char operator){
		double out = 0;double operand1, operand2;
		if(operator == '.' || operator == ','){
			if(operator == '.'){
				try{
				double popvalue = operandStack.pop();
				println("Removed the number " + popvalue);
				} catch(EmptyStackException e){ badInput("No number to remove");}
			}
			else if(operator == ','){ 
				operandStack.push(operandStack.lastElement());
				println("Duplicated: " + operandStack.lastElement());
			}
		} else {
			try{
				operand2 = operandStack.pop();
				operand1 = operandStack.pop();
				if(operator == '+'){ out = operand1 + operand2; }
				else if(operator == '-'){ out = operand1 - operand2; }
				else if(operator == '/'){ out = operand1 / operand2; }
				else if(operator == '*'){ out = operand1 * operand2; }
				else if(operator == '^'){ out = Math.pow(operand1, operand2); }
				operandStack.push(out);
				println(" "+out);
			} catch(NoSuchElementException e) { badInput("Need more numbers"); }
		}
		
	}
	private void printStack() {
		for (int i = 0; i < operandStack.size(); i++) {
			println(operandStack.get(i));
		}		
	}
	private void badInput(String output) {
		// TODO style output
		println(output);
	}
}