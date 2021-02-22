package com.example.audiorecordviewsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

public class AndroidSTT {

    SpeechRecognizer mRecognizer;
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    Intent intents;
    Activity activity;
    private final String TAG = AndroidSTT.class.getSimpleName();
    String packageName = "com.example.audiorecordviewsample";
    public String matche;


    public AndroidSTT(Activity activity, Context context){
        //mRecognizer=SpeechRecognizer.createSpeechRecognizer(context);
        //this.intents = intents;
        this.activity = activity;
       // this.context = context;
        //System.out.println("******************* actAndroidSTT - AndroidSTT -----------------------------");
        //Log.i();
        init();
    }//STTstart() end



    public void init(){
        //this.activity= activity;
        // Recognizer Intent 객체 생성
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
        mRecognizer.setRecognitionListener(listener);
        System.out.println("******************* init() - androidSTT-----------------------");
        //System.out.println(matche);
        //intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName); //EXTRA_CALLING_PACKAGE를 포함해야 STT가 실행됨 (02/05)



        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, // 수행할때 선호하는 음성모델을 인식기에 알림
        //RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); // 자유형식의 음성인식을 기반으로 하는 언어모델 사용


        // 듣기 시작
        //mRecognizer.startListening(intent);

        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language); //선택한 언어로 음성인식을 수행
    }//init() end

    public void start(){
        mRecognizer.startListening(intent);
        System.out.println("*******************start: "+intent); //찍힘

    }//start() end

    public String startAndroidSTT(String str){
        System.out.println("******************* startAndroidSTT - androidSTT-----------------------");
        //intent.putExtra("rst", str);
        System.out.println("******************* startAndroidSTT say :  "+ str);
        return str;
    }//startAndroidSTT() end


    public void stopAndroidSTT(){
        if(mRecognizer != null){
            mRecognizer.stopListening();
        }
    }//stopAndroidSTT


    //RecognitionListener 상속 구현 대신 객체화
    public RecognitionListener listener = new RecognitionListener() {


        @Override
        public void onReadyForSpeech(Bundle params) {
            //사용자가 말할 준비가 되면 호출됨
            System.out.println("*******************onReadyForSpeech - AndroidSTT *********************************");
        }

        @Override
        public void onBeginningOfSpeech() {
            // 사용자가 말하기 시작함
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            //오디오 스트림의 사운드 레벨이 변경됨
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            //더 많은 소리가 수신됨
        }

        @Override
        public void onEndOfSpeech() {
            //사용자가 말하기를 중지한 후 호출됨
            System.out.println("사용자가 말하기를 중지함");

        }

        @Override
        public void onError(int error) {
            //네트워크 또는 인식오류가 발생하면 호출됨

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
                    System.out.println("말하는 시간 초과");
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }
            //Toast.makeText(context, "에러가 발생하였습니다. : " + message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            System.out.println("******************* onResults-------------------------");


            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for(int i = 0; i < matches.size() ; i++){
                matche = matches.get(i);
            }
            System.out.println("onResults - AndroidSTT : "+ matche);

            //System.out.println("onResults - intent : "+intent);
            intents = new Intent(String.valueOf(this));
            System.out.println("onResults - intent : "+ intents);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String matchess = matche;
            intents.putExtra("rst", matchess);
            ((MainActivity)MainActivity.mContext).sttResult(matchess);
            //putResults(matchess);
            startAndroidSTT(matchess);


        }


        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }

    };
    /*
    public String putResults(){

        intent.putExtra("result", matchess);
        return matchess;
    }//putResults() ends

*/

}//class end

class Point{
    String a;
    Point(String a){
        this.a = a;
    }
}//Point end()
