package com.osdc.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button btn_one, btn_two, btn_three, btn_four,
	               btn_five, btn_six, btn_seven, btn_eight,
	               btn_nine, btn_zero, btn_div, btn_plus,
	               btn_minus, btn_mul, btn_equals, btn_point,
	               formula, history, clear;
	private EditText screen;
	private Boolean operationBefore = false;
	final int opPlus=99901,opMinus=99902,opMul=99903,opDiv=99904;
	int operationId=0;
	Double ans,Operand;
	Boolean clearFlag=true,pointAppended=false;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	
    	
        btn_one = (Button)findViewById(R.id.btn_one);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_four = (Button)findViewById(R.id.btn_four);
        btn_five = (Button)findViewById(R.id.btn_five);
        btn_six = (Button)findViewById(R.id.btn_six);
        btn_seven = (Button)findViewById(R.id.btn_seven);
        btn_eight = (Button)findViewById(R.id.btn_eight);
        btn_nine = (Button)findViewById(R.id.btn_nine);
        btn_zero = (Button)findViewById(R.id.btn_zero);
        btn_div = (Button)findViewById(R.id.btn_div);
        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_equals = (Button)findViewById(R.id.btn_equals);
        btn_point = (Button)findViewById(R.id.btn_point);
        screen = (EditText)findViewById(R.id.screen);
        formula = (Button)findViewById(R.id.formula);
        history = (Button)findViewById(R.id.history);
        clear = (Button)findViewById(R.id.clear);
        
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_equals.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        //screen.setOnClickListener(this);
        formula.setOnClickListener(this);
        clear.setOnClickListener(this);
        history.setOnClickListener(this);
        
        ans = new Double(0);
        Operand = new Double(0);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
			case R.id.btn_one: btn_num("1");
				break;
			case R.id.btn_two: btn_num("2");
				break;
			case R.id.btn_three: btn_num("3");
				break;
			case R.id.btn_four: btn_num("4");
				break;
			case R.id.btn_five: btn_num("5");
				break;
			case R.id.btn_six:  btn_num("6");
				break;
			case R.id.btn_seven: btn_num("7");
				break;
			case R.id.btn_eight: btn_num("8");
				break;
			case R.id.btn_nine: btn_num("9");
				break;
			case R.id.btn_zero: btn_num("0");
				break;
			case R.id.btn_div: btn_operation(opDiv);
				break;
			case R.id.btn_mul: btn_operation(opMul);
				break;
			case R.id.btn_plus: btn_operation(opPlus);
				break;
			case R.id.btn_minus: btn_operation(opMinus);
				break;
			case R.id.btn_point: btn_point();
				break;
			case R.id.btn_equals: calculate();
				break;
			case R.id.clear: 
				clearFlag=true;
				screen.setText("");
				operationId=0;
				ans=Double.valueOf(0);
				break;
		}
		if(id!=R.id.btn_point)pointAppended=false;
	}

	private void btn_num(CharSequence c) {
		if(operationBefore) screen.setText(c);
		else screen.append(c);
		operationBefore = false;
	}

	private void calculate() {
		if(operationBefore)return;
		Operand = Double.valueOf(screen.getText().toString());
		if (clearFlag) { ans=Operand; operationId=0; clearFlag=false; }
		switch(operationId){
			case opPlus: ans += Operand;
			break;
			case opMinus: ans -= Operand;
			break;
			case opMul: ans *= Operand;
			break;
			case opDiv: ans /= Operand;
			break;
			case 0: break;
		}
		
		screen.setText(ans.toString());
		operationBefore = true;
		operationId=0;
	}

	private void btn_point() {
		// TODO Auto-generated method stub
		if(pointAppended)return;
		screen.append(".");
		pointAppended=true;
	}

	private void btn_operation(int oper) {
		CharSequence operchar = null;
		switch(oper){
		case opPlus: operchar=" +";
			break;
		case opMinus: operchar = " -";
			break;
		case opMul: operchar = " *";
			break;
		case opDiv: operchar = " /";
			break;
		}
		if(operationBefore){
			screen.setText(ans+operchar.toString());
			operationId=oper;
			return;
		}
		calculate();
		screen.append(operchar);
		operationId=oper;
		operationBefore = true;
	}

}