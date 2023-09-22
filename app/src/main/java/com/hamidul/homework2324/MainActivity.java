package com.hamidul.homework2324;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    int square;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        editText.addTextChangedListener(watcher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                int sum = 0;
                int input = Integer.parseInt(editText.getText().toString());
                for (int x = 1; x<=input; x++){
                    if (x<input){
                        square = (int) x*x;
                        sum = sum+square;
                        textView.append(square+", ");
                    }else {
                        square=x*x;
                        sum = sum+square;
                        textView.append(""+square);
                    }

                }
                textView.append("\n\n Total = "+sum);
            }
        });
    }//onCrate======================================================================================

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            button.setEnabled(!editText.getText().toString().isEmpty());
            if (before<count){
                textView.setText("");
            }else {
                textView.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().startsWith("0")){
                s.delete(0,1);
            }
        }
    };

}//AppCompatActivity================================================================================