package com.example.tumpalan_exer3_lightsout

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainButton = findViewById<Button>(R.id.mainButton)

        //setListeners after clicking the main button
        mainButton.setOnClickListener{
            setListeners()
        }

    }

    private fun setListeners(){

        //create variables for the textviews and buttons
        val name_header = findViewById<TextView>(R.id.name_head)
        val editTextBox: EditText = findViewById<EditText>(R.id.name_edit)
        val mainButton = findViewById<Button>(R.id.mainButton)

        //list of the initial app view
        val clickableViews: List<View> = listOf(name_header, editTextBox, mainButton)

        //set visibility to gone after clicking the mainButton
        for(item in clickableViews){
            item.setVisibility(View.GONE)
        }

        //set the main header to the text input of the user
        findViewById<TextView>(R.id.mainHeader).text = editTextBox.getText().toString()

        //Hide virtual keyboard after clicking main button
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(mainButton.windowToken, 0)

        //set Listeners to the app view displaying the main game interface
        setButtonListeners()
    }

    private fun updateButton(button:Button): Int{
        if (button.text == "1") {
            button.text = "0"
            button.setBackgroundColor(Color.BLACK)
            button.setTextColor(Color.BLACK)
            return -1;
        }
        else {
            button.text = "1"
            button.setBackgroundColor(Color.YELLOW)
            button.setTextColor(Color.YELLOW)
            return 1;
        }
    }

    private fun setButtonListeners(){

        //Set variables for the buttons of the grid
        val buttona1 = findViewById<Button>(R.id.button_a1)
        val buttona2 = findViewById<Button>(R.id.button_a2)
        val buttona3 = findViewById<Button>(R.id.button_a3)
        val buttona4 = findViewById<Button>(R.id.button_a4)
        val buttona5 = findViewById<Button>(R.id.button_a5)
        val buttonb1 = findViewById<Button>(R.id.button_b1)
        val buttonb2 = findViewById<Button>(R.id.button_b2)
        val buttonb3 = findViewById<Button>(R.id.button_b3)
        val buttonb4 = findViewById<Button>(R.id.button_b4)
        val buttonb5 = findViewById<Button>(R.id.button_b5)
        val buttonc1 = findViewById<Button>(R.id.button_c1)
        val buttonc2 = findViewById<Button>(R.id.button_c2)
        val buttonc3 = findViewById<Button>(R.id.button_c3)
        val buttonc4 = findViewById<Button>(R.id.button_c4)
        val buttonc5 = findViewById<Button>(R.id.button_c5)
        val buttond1 = findViewById<Button>(R.id.button_d1)
        val buttond2 = findViewById<Button>(R.id.button_d2)
        val buttond3 = findViewById<Button>(R.id.button_d3)
        val buttond4 = findViewById<Button>(R.id.button_d4)
        val buttond5 = findViewById<Button>(R.id.button_d5)
        val buttone1 = findViewById<Button>(R.id.button_e1)
        val buttone2 = findViewById<Button>(R.id.button_e2)
        val buttone3 = findViewById<Button>(R.id.button_e3)
        val buttone4 = findViewById<Button>(R.id.button_e4)
        val buttone5 = findViewById<Button>(R.id.button_e5)
        val retryButton = findViewById<Button>(R.id.retry_button)
        val clicksValue = findViewById<TextView>(R.id.clicks_text)
        var numOfClicks = 0
        var numOfLighted = 25



        //create a 2D list of buttons
        val buttonList: List<List<Button>> = listOf( listOf(
            buttona1, buttona2, buttona3, buttona4, buttona5), listOf(buttonb1,buttonb2,buttonb3,buttonb4,buttonb5), listOf(buttonc1,buttonc2,buttonc3,buttonc4,
            buttonc5), listOf(buttond1,buttond2,buttond3,buttond4,buttond5),listOf(buttone1,buttone2,buttone3,buttone4,buttone5)
        )

        //Set the retry and number of clicks indicator to visible
        retryButton.visibility = View.VISIBLE
        clicksValue.visibility = View.VISIBLE

        retryButton.setOnClickListener{
            numOfClicks = 0
            numOfLighted = 25
            clicksValue.text = numOfClicks.toString()
            findViewById<TextView>(R.id.mainHeader).visibility = View.VISIBLE

            //resets visibility and state of all the initialized buttons
            for((i, row) in buttonList.withIndex()){
                for((j, col) in row.withIndex()){
                    buttonList[i][j].text = "1"
                    buttonList[i][j].setTextColor(Color.YELLOW)
                    buttonList[i][j].setBackgroundColor(Color.YELLOW)
                    buttonList[i][j].visibility = View.VISIBLE
                }
            }
        }

        //creates an initial state of all the buttons then sets clicks listeners to each one
        for((i, row) in buttonList.withIndex()) {
            for((j, col) in row.withIndex()){
                buttonList[i][j].visibility = View.VISIBLE
                buttonList[i][j].text = "1"
                buttonList[i][j].setTextColor(Color.YELLOW)

                //Listener that does button update depending on the button's state
                buttonList[i][j].setOnClickListener {
                    numOfClicks++;
                        if (buttonList[i][j].text == "1") {
                            buttonList[i][j].text = "0"
                            buttonList[i][j].setTextColor(Color.BLACK)
                            buttonList[i][j].setBackgroundColor(Color.BLACK)
                            numOfLighted--;
                            if (i > 0) {
                                numOfLighted+= updateButton(buttonList[i - 1][j])
                            }
                            if (i < 4) {
                                numOfLighted+= updateButton(buttonList[i + 1][j])
                            }
                            if (j < 4) {
                                numOfLighted+= updateButton(buttonList[i][j + 1])
                            }
                            if (j > 0) {
                                numOfLighted+= updateButton(buttonList[i][j - 1])
                            }
                        } else {
                            buttonList[i][j].text = "1"
                            buttonList[i][j].setBackgroundColor(Color.YELLOW)
                            buttonList[i][j].setTextColor(Color.YELLOW)
                            numOfLighted++;
                            if (i > 0) {
                                numOfLighted+= updateButton(buttonList[i - 1][j])
                            }
                            if (i < 4) {
                                numOfLighted+= updateButton(buttonList[i + 1][j])
                            }
                            if (j < 4) {
                                numOfLighted+= updateButton(buttonList[i][j + 1])
                            }
                            if (j > 0) {
                                numOfLighted+= updateButton(buttonList[i][j - 1])
                            }
                        }
                        clicksValue.text = numOfClicks.toString()

                        //If all the buttons' state are set to unlighted, loop the 2D button list to set visibility to Gone and edit the clicks TV to a You win! Text View
                        if (numOfLighted <= 0) {
                            clicks_text.text = "You win!"
                            for (list in buttonList) {
                                for (button in list) {
                                    button.visibility = View.GONE
                                }
                            }

                            findViewById<TextView>(R.id.mainHeader).visibility = View.GONE
                        }
                }
            }
        }
    }
}
