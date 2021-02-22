package com.example.audiorecordviewsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;


public class MainActivity extends AppCompatActivity implements
        RecordingSampler.CalculateVolumeListener {
    Intent intent;
    SpeechRecognizer mRecognizer;
    Button sttBtn;
    TextView textView;
    final int PERMISSION = 1;
    AndroidSTT androidSTT;
    audioVisualizer audioVisual;
    Context context;
    String rst;
    public static Context mContext;
    private RecordingSampler mRecordingSampler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT >= 23) {
//            // 퍼미션 체크
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
//                    Manifest.permission.RECORD_AUDIO}, PERMISSION);
//        }
        //textView = (TextView) findViewById(R.id.sttTextview);
        sttBtn = (Button) findViewById(R.id.sttStart);
        //textView.setText(androidSTT.matchess);
        //init();
        mContext = this;

        androidSTT = new AndroidSTT(this, context);
        //audioVisual = new audioVisualizer(context);
        sttBtn.setOnClickListener(v -> {
            androidSTT.start();
            System.out.println(getIntent());

        }); //clickListener end
//fff
//        VisualizerView mVisualizerView = (VisualizerView) findViewById(R.id.visualizer3);
//
//        ViewTreeObserver observer = mVisualizerView.getViewTreeObserver();
//        RecordingSampler recordingSampler = new RecordingSampler();
//        recordingSampler.setVolumeListener(this);  // for custom implements
//        recordingSampler.setSamplingInterval(100); // voice sampling interval
//        recordingSampler.link(mVisualizerView);     // link to visualizer
//
//        recordingSampler.startRecording();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @Override
//            public void onGlobalLayout() {
//                mVisualizerView.setBaseY(mVisualizerView.getHeight());
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    mVisualizerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    mVisualizerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//            }
//        });

    }//onCreate()


    public void sttResult(String a){
        textView = (TextView) findViewById(R.id.sttTextview);
        textView.setText(a);

    }

    @Override
    public void onCalculateVolume(int volume) {
        System.out.println("volume"+String.valueOf(volume));
    }
/*
    public View Drawing(int a){
        //audioRecordView = (View) findViewById(R.id.audioRecordView);
       // audioRecordView.update(1);
        //return audioRecordView;
    }
*/

}//class end





