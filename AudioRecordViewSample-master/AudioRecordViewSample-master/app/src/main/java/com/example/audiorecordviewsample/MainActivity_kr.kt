package com.example.audiorecordviewsample

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.visualizer.amplitude.AudioRecordView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.roundToInt


open class MainActivity_kr : AppCompatActivity(){//, View.OnClickListener {
/*
    private val requiredPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO
    )


    private var timer: Timer? = null
    private var recorder: MediaRecorder? = null
    private var audioFile: File? = null
    private var EMA_FILTER: Double = 1.0
    private var mEMA: Double = 0.0
    //private val androidSTT = AndroidSTT() //초기화
    private var isOncustomSTT: Boolean = false
    private var REQ_CODE_SPEECH = 100
    private var activity = Activity()
    //private var content = contentResolver
   // private var test1 = test()
    //private var onRst  = null
    private val TAG: String? = AndroidSTT::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //startRecording.setOnClickListener {

        //음성 시각화 시작
        startRecording()

        //AndroidSTT init()을 호출한 init() 실행

       init()


        setSwitchListeners()
        //sttTextview.text = test1.testM()


    }

    private fun init(){
        var androidSTT = AndroidSTT(this)
        androidSTT.init()
        //androidSTT = AndroidSTT(this)
        // 음성인식 결과 표시 텍스트뷰
        var sttTextview : TextView = findViewById(R.id.sttTextview)

        // 음성인식 시작 버튼
        //var sttButton : Button = findViewById<Button>(R.id.sttButton)

       // sttButton.setOnClickListener{
            //if(isOncustomSTT){
            androidSTT.startAndroidSTT()
            // sttTextview.text = androidSTT.sttResult

            //사용자가 말할 준비가 되면 호출됨

            Toast.makeText(this, "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show()

           // }else{
               //startNormalSTT()
           // }
        }
        //sttText.setText("")


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
       // var sttText = findViewById<TextView>(R.id.sttTextview)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SPEECH) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val result =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                sttTextview.text = result!!.get(0)
                Log.i(TAG ,"" )
            }
        }
    }

    private fun startRecording() {
        if (!permissionsIsGranted(requiredPermissions)) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 200)
            return
        }

        startRecording.isEnabled = false
        stopRecording.isEnabled = true
        //Creating file
        try {
            audioFile = File.createTempFile("audio", "tmp", cacheDir)
        } catch (e: IOException) {
            Log.e(MainActivity_kr::class.simpleName, e.message ?: e.toString())
            return
        }
        //Creating MediaRecorder and specifying audio source, output format, encoder & output format
        recorder = MediaRecorder()
        recorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(audioFile?.absolutePath)
            setAudioSamplingRate(48000)
            setAudioEncodingBitRate(48000)
            prepare()
            start()
        }

        startDrawing()
        mEMA = 0.0


    }

    private fun stopRecording() {
        startRecording.isEnabled = true
        stopRecording.isEnabled = false
        //stopping recorder
        recorder?.apply {
            stop()
            release()
        }
        stopDrawing()
    }

    private fun setSwitchListeners() {
        switchAlignTo.setOnCheckedChangeListener { _, isChecked ->
            audioRecordView.chunkAlignTo = if (isChecked) {
                AudioRecordView.AlignTo.CENTER
            } else {
                AudioRecordView.AlignTo.BOTTOM
            }
        }
        switchRoundedCorners.setOnCheckedChangeListener { _, isChecked ->
            audioRecordView.chunkRoundedCorners = isChecked
        }
        switchSoftTransition.setOnCheckedChangeListener { _, isChecked ->
            audioRecordView.chunkSoftTransition = isChecked
        }
        switchDirection.setOnCheckedChangeListener { _, isChecked ->
            audioRecordView.direction = if (isChecked) {
                AudioRecordView.Direction.RightToLeft
            } else {
                AudioRecordView.Direction.LeftToRight
            }
        }
    }

    private fun startDrawing() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                val currentMaxAmplitude = recorder?.maxAmplitude
                //audioRecordView.update(currentMaxAmplitude ?: 0) //redraw view
                audioRecordView.update(currentMaxAmplitude ?: 0) //redraw view
                val mediaRecorder = MediaRecorder()

                val me = currentMaxAmplitude?.div(10)
                val mer = currentMaxAmplitude?.toDouble()
                //▼
//                if (recorder!!.maxAmplitude > 0 && recorder!!.maxAmplitude < 1000000) {
                    val d : Float? = World.setDbCount(20 * Math.log10(recorder!!.maxAmplitude.toDouble()).toFloat())
//                    textView1.text = d!!.roundToInt().toString()
 //               }


                //var mes = 20*Math.log10(Math.abs(mediaRecorder.maxAmplitude).toDouble())
                //var mes = Math.abs(20*Math.log10(mediaRecorder?.maxAmplitude.toDouble()))
                //--------------------------------------------------------------------------------
                val a = (20*Math.log10(Math.abs(mer?:0.0)))/16
                val b : Double = 20*Math.log10(mer?:0.0)/10
                //  ▼
                val c : Double = 20 * Math.log10(recorder!!.maxAmplitude / 16.0)
                //val data1 = getAmplitude().roundToInt()
                textView1.text = c.roundToInt().toString()


                //if (currentMaxAmplitude != 0) {
                    // Change the sound pressure value to the decibel value and post it


                //}

                }
        }, 0, 100)
    }

    private fun stopDrawing() {
        timer?.cancel()
        audioRecordView.recreate()
    }

    private fun permissionsIsGranted(perms: Array<String>): Boolean {
        for (perm in perms) {
            val checkVal: Int = checkCallingOrSelfPermission(perm)
            if (checkVal != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }
        startRecording()
    }
    open fun getAmplitude(): Double {
        return if (recorder != null) //return  (mRecorder.getMaxAmplitude()/2700.0);
            20 * Math.log10(recorder!!.maxAmplitude / 16.0) else 0.0
    }

    open fun getAmplitudeEMA(): Double {
        val amp = getAmplitude()
        val mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA
        return mEMA
    }


/*  STT
    open fun startNormalSTT() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ko-KR")
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,  // 수행할때 선호하는 음성모델을 인식기에 알림
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something")
        startActivityForResult(intent, REQ_CODE_SPEECH)
    }
*/


//    override fun onClick(v: View?) {
//        if(isOncustomSTT){
//            androidSTT?.startAndroidSTT()
//            init()
//        }else{
//            startNormalSTT()
//        }
//    }
*/

}//class end
