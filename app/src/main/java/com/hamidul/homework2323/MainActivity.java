package com.hamidul.homework2323;

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
    long total;
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
                total=9;

                int input = Integer.parseInt(editText.getText().toString());
                long t = 9;
                textView.append(t+"");
                for (int x = 2; x<=input; x++){
                    t=(int) t*10+9;
                    total=total+t;
                    textView.append(", "+t);

                }
                textView.append("\n\nTotal = "+total);
            }
        });


    }//onCreate=====================================================================================

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            button.setEnabled(!editText.getText().toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().startsWith("0")){
                s.delete(0,1);
            }
        }
    };

}//AppCompatActivity================================================================================