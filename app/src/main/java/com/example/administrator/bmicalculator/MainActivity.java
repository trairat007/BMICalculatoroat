package com.example.administrator.bmicalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private Button mCalculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeightEditText = (EditText) findViewById(R.id.height_edit_text);
        mWeightEditText = (EditText) findViewById(R.id.weight_edit_text);
        mCalculateButton = (Button) findViewById(R.id.calculate_button);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightText = mHeightEditText.getText().toString();
                Double height = Double.valueOf(heightText);
                Double weight = Double.valueOf(mWeightEditText.getText().toString());
                Double bmi = weight / ((height / 100) * (height / 100));

                String bmiText = getBmiText(bmi);

                String result = String.format("ค่า BMI ที่ได้คือ %.2f\n\nอยู่ในเกณฑ์ : %s", bmi, bmiText);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("BMI Result");
                dialog.setMessage(result);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //โค้ดที่ต้องการให้ทำงานเมื่อปุ่ม OK บน dialog ถูกคลิก
                        //finish(); //ปิด แอคทิวตี้ ปัจจุบัน
                    }
                });
                dialog.show();
            }

            private String getBmiText(Double bmi) {
        /*
            bmi < 18.5 : น้ำหนักน้อยกว่าปกติ
            18.5 <= bmi < 25 : น้ำหนักปกติ
            25 <= bmi < 30 : น้ำหนักมากวกว่าปกติ(ท้วม)
            bmi >= 30 : น้ำหนักมากกว่าปกติมาก(อ้วน)
        */
                String bmiText = "";

                if (bmi < 18.5) {
                    bmiText = "น้ำหนักน้อยกว่าปกติ";
                } else if (bmi < 25) {
                    bmiText = "น้ำหนักปกติ";
                } else if (bmi < 30) {
                    bmiText = "น้ำหนักมากกว่าปกติ(ท้วม)";
                } else {
                    bmiText = "น้ำหนักมากกว่าปกติ(อ้วน)";
                }
                return bmiText;
            }
        });
        // mCalculateButton.setOnClickListener(this);
        //สร้างออปเจ็ค listener ของปุ่ม
        // MyListener listener = new MyListener();
        //mCalculateButton.setOnClickListener(listener); // กำหนดออปเจค ที่เป็น ลิสเทนเนอ ให้กับปุ่ม
    }

    /*@Override
    public void onClick(View v) {

    }*/

    /*private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast t = Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT);
            t.show();
        }

    }*/
}
