package com.hamidul.homework2325;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String reverse = "";
                for (int x =0 ; x<s.length();x++){
                    reverse=s.charAt(x)+reverse;
                }
                int i = Integer.parseInt(reverse);
                int j = Integer.parseInt(editText.getText().toString());
                
                if (i==j){
                    Toast.makeText(MainActivity.this, j+" Is Palindrome", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, j+" Is Not Palindrome", Toast.LENGTH_SHORT).show();
                }

                textView.setText(""+i);

            }
        });

    }
}