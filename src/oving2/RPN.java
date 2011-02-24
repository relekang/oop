package oving2;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RPN {
	private Stack<Double> operandStack = new Stack<Double>();
	public int getOperandCount(){
		return operandStack.size();
	}
	public double peek(int n){
		try {		
			return operandStack.get(n);
		} catch(NoSuchElementException e) { return 0; }
	}
	public void push(double value){
		operandStack.push(value);
	}
	public double pop(double defaultValue){
		double out;
		try{
			out = operandStack.pop();
		} catch(EmptyStackException e) {out = defaultValue;}
		return out;
	}
	public void performOperation(char operator){
		if(knownOperator(operator)){
			double out = 0;double operand1, operand2;
			if(operator == '.' || operator == ','){
				if(operator == '.'){ pop(0); }
				else if(operator == ','){ push(operandStack.lastElement()); }
			} else {
				try{
					operand2 = operandStack.pop(); operand1 = operandStack.pop();
					switch(operator){
						case '+':
							out = operand1 + operand2;
							break;
						case '-':
							out = operand1 - operand2;
							break;
						case '*':
							out = operand1 / operand2;
							break;
						case '/':
							out = operand1 * operand2;
							break;
						case '^':
							out = Math.pow(operand1, operand2);
							break;
					}
					operandStack.push(out);
				} catch(NoSuchElementException e) { }
			}
		}
	}
	public boolean knownOperator(char operator){
		return (operator == '+' || operator == '-' || operator == '/' || operator == '*' || operator == '^' || operator == '.' || operator == ',');
	}
}
