package com.example.np

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

   
    private lateinit var titleText: TextView
    private lateinit var infoText: TextView
    private lateinit var redButton: Button
    private lateinit var blueButton: Button
    private lateinit var yellowButton: Button
    private lateinit var orangeButton: Button
    private lateinit var purpleButton: Button
    private lateinit var grayIndicator: View

  
    private lateinit var leftPanel: View
    private lateinit var centerPanel: View
    private lateinit var rightPanel: View

  
    private var progress = 0

    
    companion object {
        private val COLOR_LIGHT_BLUE = Color.parseColor("#64B5F6")
        private val COLOR_BLUE = Color.parseColor("#2196F3")
        private val COLOR_DARK_BLUE = Color.parseColor("#1976D2")
        private val COLOR_GREEN = Color.parseColor("#4CAF50")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     
        initViews()

       
        setupClickListeners()

       
        resetToInitialState()
    }

    private fun initViews() {
        
        titleText = findViewById(R.id.titleText)
        infoText = findViewById(R.id.infoText)
        redButton = findViewById(R.id.redButton)
        blueButton = findViewById(R.id.blueButton)
        yellowButton = findViewById(R.id.yellowButton)
        orangeButton = findViewById(R.id.orangeButton)
        purpleButton = findViewById(R.id.purpleButton)
        grayIndicator = findViewById(R.id.grayIndicator)

       
        leftPanel = findViewById(R.id.leftPanel)
        centerPanel = findViewById(R.id.centerPanel)
        rightPanel = findViewById(R.id.rightPanel)
    }

    private fun setupClickListeners() {
     
        yellowButton.setOnClickListener {
            addProgress()
        }

      
        redButton.setOnClickListener {
            resetToInitialState()
        }

       
        blueButton.setOnClickListener {
            showInfo()
        }

        
        orangeButton.setOnClickListener {
           
        }

        purpleButton.setOnClickListener {
           
        }
    }

    private fun addProgress() {
      
        progress += 10

       
        if (progress > 100) {
            progress = 100
        }

        
        updatePanelsByProgress()

      
        Toast.makeText(this, "Прогресс: $progress%", Toast.LENGTH_SHORT).show()

  
        updateInfoText()
    }

    private fun updatePanelsByProgress() {
        when {
            progress >= 100 -> {
           
                rightPanel.setBackgroundColor(COLOR_GREEN)
                centerPanel.setBackgroundColor(COLOR_BLUE)
                leftPanel.setBackgroundColor(COLOR_LIGHT_BLUE)
            }
            progress >= 70 -> {
              
                centerPanel.setBackgroundColor(COLOR_GREEN)
                leftPanel.setBackgroundColor(COLOR_LIGHT_BLUE)
                rightPanel.setBackgroundColor(COLOR_DARK_BLUE)
            }
            progress >= 30 -> {
                
                leftPanel.setBackgroundColor(COLOR_GREEN)
                centerPanel.setBackgroundColor(COLOR_BLUE)
                rightPanel.setBackgroundColor(COLOR_DARK_BLUE)
            }
            else -> {
             
                resetPanels()
            }
        }
    }

    private fun resetToInitialState() {
      
        progress = 0

        
        resetPanels()

        
        updateInfoText()

     
        Toast.makeText(this, "Состояние сброшено", Toast.LENGTH_SHORT).show()
    }

    private fun resetPanels() {
        leftPanel.setBackgroundColor(COLOR_LIGHT_BLUE)
        centerPanel.setBackgroundColor(COLOR_BLUE)
        rightPanel.setBackgroundColor(COLOR_DARK_BLUE)
    }

    private fun showInfo() {
       
        val infoMessage = when {
            progress >= 100 -> "Этап 3: Правая панель зеленая (прогресс 100%)"
            progress >= 70 -> "Этап 2: Центральная панель зеленая (прогресс ≥ 70%)"
            progress >= 30 -> "Этап 1: Левая панель зеленая (прогресс ≥ 30%)"
            else -> "Начальный этап: все панели синих оттенков (прогресс 0%)"
        }

        Toast.makeText(this, "$infoMessage\nТекущий прогресс: $progress%", Toast.LENGTH_LONG).show()
        updateInfoText()
    }

    private fun updateInfoText() {
        val stageText = when {
            progress >= 100 -> "Этап 3: Правая панель зеленая"
            progress >= 70 -> "Этап 2: Центральная панель зеленая"
            progress >= 30 -> "Этап 1: Левая панель зеленая"
            else -> "Начальный этап"
        }
        infoText.text = "$stageText\nПрогресс: $progress%"
    }
}
