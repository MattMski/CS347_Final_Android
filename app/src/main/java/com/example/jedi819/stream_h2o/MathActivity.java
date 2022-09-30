package com.example.jedi819.stream_h2o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by jedi819 on 2/19/2018.
 */

public class MathActivity extends MainActivity {
    private String[] levelNames = {"Addition", "Subtraction", "NEVER MIND!"};
    private String[] operatorNames = {"+", "-"};
    private Random random;
    private TextView question, answerTxt, answerSubmit;
    private int answer = 0, entered = 0, operand1 = 0, operand2 = 0,enteredAnswer;

    private Button btnAnswer, btnAnother, btnSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.math_layout, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_math);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose Your Operation:")
                .setSingleChoiceItems(levelNames, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //start gameplay

                        startPlay(which);
                    }
                });

        AlertDialog ad = builder.create();
        ad.show();



//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Hello First Activity", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        Snackbar snackbar = Snackbar
//                .make(contentView, "S C I E N C E !!!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null);
//
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(getResources().getColor(R.color.colorRed));
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(getResources().getColor(R.color.colorWhite));
//        snackbar.show();
////         Set drawer nav highlight
//        navIndex = 0;
//        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.getMenu().getItem(navIndex).setChecked(true);





    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
        }
    }

    private void startPlay(int chosenLevel)
    {

        View parentLayout = findViewById(android.R.id.content);
        //start gameplay
        if(chosenLevel == 0){
            if(entered == 0) {
                Snackbar.make(parentLayout, "", Snackbar.LENGTH_LONG)
                        .setAction("ADDITION  +  ", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
            }
            chooseQuestion(chosenLevel);
        }
        else if (chosenLevel == 1){
            if(entered == 0) {
                Snackbar.make(parentLayout, "", Snackbar.LENGTH_LONG)
                        .setAction("SUBTRACTION  -  ", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();

//            Toast.makeText(this    , "Multiply / Divide",
//                    Toast.LENGTH_LONG).show();
            }
                chooseQuestion(chosenLevel);
        }
        else {
            onBackPressed();
        }



    }


    private void chooseQuestion(final int chosenLevel){
//get a question

        random = new Random();

        question =  (TextView)findViewById(R.id.question);
        answerTxt = (TextView)findViewById(R.id.answer);
        answerSubmit = (TextView)findViewById(R.id.answerSubmit);
        answerTxt.setText("= ?");
        answerSubmit.setText("");
        btnAnother = (Button)findViewById(R.id.btnAnother);
        btnAnother.setVisibility(View.INVISIBLE);
        btnSwitch= (Button)findViewById(R.id.btnswitch);
        //operator = random.nextInt(3);
        //chosenLevel 0 = ADD_OPERATOR;
        operand1 = getOperand(0);
        operand2 = getOperand(0);
        entered = 1;
        if(chosenLevel > 0){
            while(operand2>operand1){
                operand1 = getOperand(1);
                operand2 = getOperand(0);
            }
        }


        switch(chosenLevel)
        {
            case 0:
                answer = operand1+operand2;
                btnSwitch.setText("GO TO SUBTRACTION");
                break;
            case 1:
                answer = operand1-operand2;
                btnSwitch.setText("GO TO ADDITION");
                break;
            default:
                break;
        }

        question.setText(operand1+" "+operatorNames[chosenLevel]+" "+operand2);


        btnAnswer = (Button)findViewById(R.id.btnSubmit);

        answerSubmit = (TextView)findViewById(R.id.answerSubmit);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    enteredAnswer = Integer.parseInt(answerSubmit.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                //String answerContent = answerTxt.getText().toString();
                //int answerContent = Integer.parseInt(answerTxt.getText().toString());
                //int userAnswer = Integer.parseInt(answerTxt.getText().toString());

                if(enteredAnswer==answer){
                //correct
                    String answerContent = answerSubmit.getText().toString();
                Toast.makeText(MathActivity.this    , "Correct! ",
                        Toast.LENGTH_LONG).show();

                    btnAnother.setVisibility(View.VISIBLE);
                    answerTxt.setText("= "+answerContent);
                 }
                 else {
                    Toast.makeText(MathActivity.this    , "Try Again! ",
                            Toast.LENGTH_LONG).show();
                    answerSubmit.setText("");
                }


            }

        });
        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPlay(chosenLevel);


            }

        });

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entered = 0;
                if(chosenLevel == 0){
                    startPlay(1);
                }
                else
                    startPlay(0);


            }

        });

    }


    private int getOperand(int chosenLevel) {
//return operand number
        if (chosenLevel > 0) {
            //subtract up to 20s
            return random.nextInt(20);
        } else
            // add up to 20s == 10+10
            return random.nextInt(10);
    }
}
