package com.example.clapapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar : SeekBar
    private var mp : MediaPlayer? = null
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.sbClap)
        handler = Handler(Looper.getMainLooper())
       
        
    }
    private fun initializeSeekBar(){
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        val tvPlayed = findViewById<TextView>(R.id.tvPlayed)
        val tvRemaining = findViewById<TextView>(R.id.tvRemaining)

        seekBar.max = mp!!.duration/* to equate max of seekbar
        to mediaplayer file duration*/
        runnable = Runnable {
            seekBar.progress = mp!!.currentPosition
            val playedTime = mp!!.currentPosition/1000
            tvPlayed.text = "$playedTime sec"
            val duration = mp!!.duration/1000
            val dueTime = duration - playedTime
            tvRemaining.text = "$dueTime sec"
            handler.postDelayed(runnable,100)
        }
        handler.postDelayed(runnable,100)
    }
}
