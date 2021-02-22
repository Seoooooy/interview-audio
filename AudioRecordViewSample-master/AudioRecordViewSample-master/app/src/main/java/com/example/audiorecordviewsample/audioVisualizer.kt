package com.example.audiorecordviewsample

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
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

class audioVisualizer(context: Context) {

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
    private var REQ_CODE_SPEECH = 100
    private lateinit var audioRecordView : AudioRecordView


    private fun startRecording() {
        /*
        if (!permissionsIsGranted(requiredPermissions)) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 200)
            return
        }
*/
        //startRecording.isEnabled = false
        //stopRecording.isEnabled = true
//        try {
//            audioFile = File.createTempFile("audio", "tmp", cacheDir)
//        } catch (e: IOException) {
//            Log.e(MainActivity::class.simpleName, e.message ?: e.toString())
//            return
//        }
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
//        startRecording.isEnabled = true
//        stopRecording.isEnabled = false
        //stopping recorder
        recorder?.apply {
            stop()
            release()
        }
        stopDrawing()
    }


//    private fun setSwitchListeners() {
//        switchAlignTo.setOnCheckedChangeListener { _, isChecked ->
//            audioRecordView.chunkAlignTo = if (isChecked) {
//                AudioRecordView.AlignTo.CENTER
//            } else {
//                AudioRecordView.AlignTo.BOTTOM
//            }
//        }
//        switchRoundedCorners.setOnCheckedChangeListener { _, isChecked ->
//            audioRecordView.chunkRoundedCorners = isChecked
//        }
//        switchSoftTransition.setOnCheckedChangeListener { _, isChecked ->
//            audioRecordView.chunkSoftTransition = isChecked
//        }
//        switchDirection.setOnCheckedChangeListener { _, isChecked ->
//            audioRecordView.direction = if (isChecked) {
//                AudioRecordView.Direction.RightToLeft
//            } else {
//                AudioRecordView.Direction.LeftToRight
//            }
//        }
//    }

    private fun startDrawing() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                val currentMaxAmplitude = recorder?.maxAmplitude
                //audioRecordView.update(currentMaxAmplitude ?: 0) //redraw view
                //View audioView =((MainActivity)MainActivity.mContext).a
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
                val rountString = c.roundToInt().toString()


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
/*
    //퍼미션 체크. activity에서 확인하니 여기선 필요X
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
    */


    open fun getAmplitude(): Double {
        return if (recorder != null) //return  (mRecorder.getMaxAmplitude()/2700.0);
            20 * Math.log10(recorder!!.maxAmplitude / 16.0) else 0.0
    }



 */
}//class end