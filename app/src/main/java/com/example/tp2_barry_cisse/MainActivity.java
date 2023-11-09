package com.example.tp2_barry_cisse;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double number = 0;
    double previous = 0;
    double next = 0;
    boolean decimal = false;
    boolean neg = false;
    String op = "=";
    int decimalStep=1;
    double power=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView screenResult = findViewById(R.id.screenResult);

        Button b0 = findViewById(R.id.button0);
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);

        Button bCancel= findViewById(R.id.buttonCancel);
        Button bDot = findViewById(R.id.buttonDot);

        Button plus = findViewById(R.id.buttonPlus);
        Button moins = findViewById(R.id.buttonMoins);
        Button mul = findViewById(R.id.buttonMul);
        Button div = findViewById(R.id.buttonDiv);
        Button egal = findViewById(R.id.buttonEgal);


        b0.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(0,screenResult,op); }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(1, screenResult, op); }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(2, screenResult, op); }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(3, screenResult, op); }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(4, screenResult, op); }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(5, screenResult, op); }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(6, screenResult, op); }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(7, screenResult, op); }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(8, screenResult, op); }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { chiffre(9, screenResult, op); }
        });


        bDot.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {decimal=true;}
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (op!="="){
                    operator("=", screenResult);

                }
                op="+";operator(op,screenResult); }
        });
        moins.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {


                if (previous==0 && number ==0 && op=="="){//cas d'un nombre négatif dès le début
                    //op="-";
                    neg=true;
                    screenResult.setText("-");
                    operator(op, screenResult);
                }
                else if( number !=0 && op=="="){ //cas de l'operateur -
                    op="-";
                    neg=false;
                    operator(op, screenResult);
                }
                else if (op!="=" && number !=0){//cas d'un calculs avec un 2 operateur

                    operator("=", screenResult);
                    neg = false;
                    op = "-";
                    operator(op, screenResult);

                }else if (op!="=" && number ==0){//cas d'un calculs avec un nombre negatif
                    if (op == "+" || op=="-" ) {
                        operator("=", screenResult);
                        neg = false;
                        op = "-";
                        operator(op, screenResult);
                    }else{

                        neg=true;
                    }
                }
                else{
                    neg=true;
                }

            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (op!="="){
                    operator("=", screenResult);
                }
                op="*";
                operator(op, screenResult);}
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (op!="="){
                    operator("=", screenResult);
                }
                op="/"; operator(op, screenResult);}
        });
        egal.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {  operator("=", screenResult); }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {  operator("C", screenResult); }
        });
    }

    private void chiffre(double i, TextView screenResult, String op) {
        System.out.println("chiffre");

        if (neg){
            i = -i ;
            neg=false;
        }

        if (decimal){

            i = i/Math.pow(10,decimalStep);
            number = (number/10);
            decimalStep +=1;

        }

        number = (number * 10) + i;
        number = roundAvoid(number,decimalStep);

        if (previous != 0) {screenResult.setText("" + previous + op + number); }
        //else{screenResult.setText(""+ Math.pow(number,power)); }
        else{screenResult.setText(""+ number); }

    }
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    private void operator(String b, TextView screenResult) {

        decimal = false;
        decimalStep =10;
        switch (b){
            case "=":
                next = number;
                switch (op) {
                    case "+":
                        number = previous + next;
                        break;
                    case "-":
                        number = previous - next;
                        break;
                    case "/":
                        number =  previous / next;
                        break;
                    case "*":
                        number = previous * next;
                        break;
                }

                if (previous!=0) {
                    screenResult.setText(""+previous+op+ next+b+number);
                }
                else{screenResult.setText(""+ number);}

                if (neg){screenResult.setText("-"); }

                previous = 0;
                op="=";
                break;

            case "C" :
                number = 0;
                previous = 0;
                next = 0;
                op = "=";
                neg = false;
                //power = 0;
                decimalStep = 1;
                screenResult.setText("");
                break;

            default:
                previous = number;
                number = 0;
                decimalStep = 1;
                if (previous != 0) {screenResult.setText(""+previous+b+ number);}
                else{screenResult.setText(""+ number);}
                break;
        }
    }
}
