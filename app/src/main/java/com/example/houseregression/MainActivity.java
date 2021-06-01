package com.example.houseregression;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText crim, zone, indus, chas, nox, room, age, dis, rad, tax, pratio, black, stat;
    double[] input = new double[13];
    Button predict;
    double result;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of your trained model
        model = new Model();

        // Declare button
        predict = (Button)findViewById(R.id.predict);

        // Declare fields
        crim = (EditText)findViewById(R.id.crim);
        zone = (EditText)findViewById(R.id.zn);
        indus = (EditText)findViewById(R.id.indus);
        chas = (EditText)findViewById(R.id.chas);
        nox = (EditText)findViewById(R.id.nox);
        room = (EditText)findViewById(R.id.rm);
        age = (EditText)findViewById(R.id.age);
        dis = (EditText)findViewById(R.id.dis);
        rad = (EditText)findViewById(R.id.rad);
        tax = (EditText)findViewById(R.id.tax);
        pratio = (EditText)findViewById(R.id.ptratio);
        black = (EditText)findViewById(R.id.black);
        stat = (EditText)findViewById(R.id.lstat);

        // Set the button action
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make sure there are no empty fields
                if (crim.getText().toString().equals("") ||
                        crim.getText().toString().equals("") ||
                        zone.getText().toString().equals("") ||
                        indus.getText().toString().equals("") ||
                        chas.getText().toString().equals("") ||
                        nox.getText().toString().equals("") ||
                        room.getText().toString().equals("") ||
                        age.getText().toString().equals("") ||
                        dis.getText().toString().equals("") ||
                        rad.getText().toString().equals("") ||
                        tax.getText().toString().equals("") ||
                        pratio.getText().toString().equals("") ||
                        black.getText().toString().equals("") ||
                        stat.getText().toString().equals("")){
                    // We do not accept empty fields
                    Toast.makeText(MainActivity.this, "Please fill in all input fields.",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    // Get input values
                    input[0] = Double.parseDouble(crim.getText().toString());
                    input[1] = Double.parseDouble(zone.getText().toString());
                    input[2] = Double.parseDouble(indus.getText().toString());
                    input[3] = Double.parseDouble(chas.getText().toString());
                    input[4] = Double.parseDouble(nox.getText().toString());
                    input[5] = Double.parseDouble(room.getText().toString());
                    input[6] = Double.parseDouble(age.getText().toString());
                    input[7] = Double.parseDouble(dis.getText().toString());
                    input[8] = Double.parseDouble(rad.getText().toString());
                    input[9] = Double.parseDouble(tax.getText().toString());
                    input[10] = Double.parseDouble(pratio.getText().toString());
                    input[11] = Double.parseDouble(black.getText().toString());
                    input[12] = Double.parseDouble(stat.getText().toString());

                    // run the inference
                    result = model.score(input);
                    // show result in a dialog box
                    showResult(String.valueOf(result));
                }
            }
        });

    }
    // method to show the dialog box
    private void showResult(String result) {
        AlertDialog.Builder pred = new AlertDialog.Builder(this);
        pred.setTitle("Inference Result");
        pred.setMessage("Median Value : " + result);
        pred.create().show();
        // clear fields
        crim.setText("");
        zone.setText("");
        indus.setText("");
        chas.setText("");
        nox.setText("");
        room.setText("");
        age.setText("");
        dis.setText("");
        rad.setText("");
        tax.setText("");
        pratio.setText("");
        black.setText("");
        stat.setText("");
    }
}