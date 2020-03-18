package com.example.prostagra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var gameStarted= false
    private lateinit var coutDownTimer: CountDownTimer
    private var initialCountDownTimer: Long = 60000
    private var countDownInterval: Long= 1000
    private var timeLeft = 60

    private lateinit var gameScoreTextView: TextView
    private lateinit var timeleftTextView: TextView
    private lateinit var tapmeButton: Button
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView= findViewById(R.id.game_score_text_view)
        timeleftTextView= findViewById(R.id.time_left_text_view)
        tapmeButton= findViewById(R.id.tam_me_button)

        tapmeButton.setOnClickListener{
            incrementScore()
        }
        resetGame()
    }
    private fun incrementScore(){
        if(!gameStarted)
        {
            startGame()
        }

        score++
        val newScore = getString(R.string.your_score, score)
        gameScoreTextView.text= newScore
    }

    private fun resetGame() {
        score = 0
        val initialScore = getString(R.string.your_score,score)
        gameScoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.time_left, timeLeft)
        timeleftTextView.text= initialTimeLeft

        gameStarted= false

        coutDownTimer= object : CountDownTimer(initialCountDownTimer, countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
            timeLeft= millisUntilFinished.toInt()/1000
                val timeLeftString: String = getString(R.string.time_left,timeLeft)
                timeleftTextView.text = timeLeftString
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun startGame(){
        coutDownTimer.start()
        gameStarted= true

    }

    private fun endGame(){
    Toast.makeText( this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}
