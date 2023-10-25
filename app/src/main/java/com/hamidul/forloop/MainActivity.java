package com.hamidul.forloop;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout,myLayout;
    Button button;
    EditText editText;
    TextView textView;
    BroadcastReceiver broadcastReceiver;
    TextToSpeech textToSpeech;
    SeekBar sPitch,sSpeed;
    TextView countPitch,countSpeed;
    int sumOfEvenNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        myLayout = findViewById(R.id.myLayout);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        sPitch = findViewById(R.id.pitch);
        sSpeed = findViewById(R.id.speed);
        countPitch = findViewById(R.id.countPitch);
        countSpeed = findViewById(R.id.countSpeed);
        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        broadcastReceiver = new InternetCheck();
        register();

        sPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countPitch.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countSpeed.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                sumOfEvenNumber=0;
                if (editText.getText().toString().equals("")){
                    editText.setError("Please Enter Your Number");
                    editText.requestFocus();
                    return;
                }

                int nTerms = Integer.parseInt(editText.getText().toString());
                if (nTerms<=0){
                    editText.setError("Minimum Number 1");
                    editText.requestFocus();
                }else {
                    int evenNumber = (int) nTerms*2;
                    for (int x=1; x<=evenNumber;x++){
                        if (x%2==0 && x!=evenNumber){
                            sumOfEvenNumber=sumOfEvenNumber+x;
                            textView.append(x+", ");
                        }else if (x==evenNumber){
                            sumOfEvenNumber=sumOfEvenNumber+x;
                            textView.append(""+x);
                        }
                    }
                    textView.append("\n\nTotal = "+sumOfEvenNumber);
                }
            }
        });


    }//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case 1:
                    Toast.makeText(MainActivity.this, "First Click", Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    Toast.makeText(MainActivity.this, "Second Click", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public void onBackPressed() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        final AlertDialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    protected void register(){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }
    protected void unRegister(){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        unRegister();
        super.onDestroy();
    }

    private void Speck(){
        float pitch = (float) sPitch.getProgress()/50;
        if (pitch<0) pitch = 0.10f;

        float speed = (float) sSpeed.getProgress()/50;
        if (speed<0) speed = 0.01f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);

        textToSpeech.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
    }


}//=================================================================================================