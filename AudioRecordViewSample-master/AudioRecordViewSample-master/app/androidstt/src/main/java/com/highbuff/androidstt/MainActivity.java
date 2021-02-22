package com.highbuff.androidstt;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    SpeechRecognizer mRecognizer;
    Button sttBtn;
    TextView textView;
    final int PERMISSION = 1;

    protected void STTMain() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        if ( Build.VERSION.SDK_INT >= 23 ){
            // 퍼미션 체크
            ActivityCompat.requestPermissions(
                    this, new String[]{
                        Manifest.permission.INTERNET,
                        Manifest.permission.RECORD_AUDIO
                    },
                    PERMISSION);
        }//if end

        textView = (TextView)findViewById(R.id.sttResult);
        sttBtn = (Button) findViewById(R.id.sttStart);

        intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
        sttBtn.setOnClickListener(v ->{
            // 새 SpeechRecognizer를 만드는 팩토리 메서드
            mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
            mRecognizer.setRecognitionListener(listener);
            // 듣기 시작
            mRecognizer.startListening(intent);
        });
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            //사용자가 말할 준비가 되면 호출됨
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {
            //사용자가 말하기 시작했을 때 호출
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            //입력받는 소리 크기를 알려줌
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            //사용자 말중 인식된 단어를 buffer에 담음
        }

        @Override
        public void onEndOfSpeech() {
            //사용자가 말하기를 중지하면 호출됨
        }

        @Override
        public void onError(int error) {
            // 네트워크 또는 인식 오류 발생 시 호출됨
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트웍 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }

            Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            ArrayList<String> matches =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for(int i = 0; i < matches.size() ; i++){
                textView.setText(matches.get(i));
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {}

        @Override
        public void onEvent(int eventType, Bundle params) {}
    };

}