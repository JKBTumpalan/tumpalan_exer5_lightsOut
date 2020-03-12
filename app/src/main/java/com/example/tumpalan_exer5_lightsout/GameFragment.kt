package com.example.tumpalan_exer5_lightsout

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tumpalan_exer5_lightsout.databinding.GameLayoutBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<GameLayoutBinding>(
            inflater,
            R.layout.game_layout,
            container,
            false
        )

        setButtonListeners(binding)

        return binding.root
    }

    private fun setListeners(binding: GameLayoutBinding) {

//        //set the main header to the text input of the user
//        findViewById<TextView>(R.id.mainHeader).text = editTextBox.getText().toString()

        //Hide virtual keyboard after clicking main button
//        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(mainButton.windowToken, 0)

        //set Listeners to the app view displaying the main game interface
        setButtonListeners(binding)
    }

    private fun updateButton(button: Button): Int {
        if (button.text == "1") {
            button.text = "0"
            button.setBackgroundColor(Color.BLACK)
            button.setTextColor(Color.BLACK)
            return -1;
        } else {
            button.text = "1"
            button.setBackgroundColor(Color.YELLOW)
            button.setTextColor(Color.YELLOW)
            return 1;
        }
    }

    private fun setButtonListeners(binding: GameLayoutBinding) {

        var numOfClicks = 0
        var numOfLighted = 25

        //create a 2D list of buttons
        val buttonList: List<List<Button>> = listOf(
            listOf(
                binding.buttonA1,
                binding.buttonA2,
                binding.buttonA3,
                binding.buttonA4,
                binding.buttonA5
            ),
            listOf(
                binding.buttonB1,
                binding.buttonB2,
                binding.buttonB3,
                binding.buttonB4,
                binding.buttonB5
            ),
            listOf(
                binding.buttonC1, binding.buttonC2, binding.buttonC3, binding.buttonC4,
                binding.buttonC5
            ),
            listOf(
                binding.buttonD1,
                binding.buttonD2,
                binding.buttonD3,
                binding.buttonD4,
                binding.buttonD5
            ),
            listOf(
                binding.buttonE1,
                binding.buttonE2,
                binding.buttonE3,
                binding.buttonE4,
                binding.buttonE5
            )
        )

        //Set the retry and number of clicks indicator to visible
//        binding.retryButton.visibility = View.VISIBLE
//        binding.clicksText.visibility = View.VISIBLE

        //creates an initial state of all the buttons then sets clicks listeners to each one
        for ((i, row) in buttonList.withIndex()) {
            for ((j, col) in row.withIndex()) {
                buttonList[i][j].visibility = View.VISIBLE
                buttonList[i][j].text = "1"
                buttonList[i][j].setTextColor(Color.YELLOW)

                //Listener that does button update depending on the button's state
                buttonList[i][j].setOnClickListener { view: View ->
                    numOfClicks++;
                    if (buttonList[i][j].text == "1") {
                        buttonList[i][j].text = "0"
                        buttonList[i][j].setTextColor(Color.BLACK)
                        buttonList[i][j].setBackgroundColor(Color.BLACK)
                        numOfLighted--;
                        if (i > 0) {
                            numOfLighted += updateButton(buttonList[i - 1][j])
                        }
                        if (i < 4) {
                            numOfLighted += updateButton(buttonList[i + 1][j])
                        }
                        if (j < 4) {
                            numOfLighted += updateButton(buttonList[i][j + 1])
                        }
                        if (j > 0) {
                            numOfLighted += updateButton(buttonList[i][j - 1])
                        }
                    } else {
                        buttonList[i][j].text = "1"
                        buttonList[i][j].setBackgroundColor(Color.YELLOW)
                        buttonList[i][j].setTextColor(Color.YELLOW)
                        numOfLighted++;
                        if (i > 0) {
                            numOfLighted += updateButton(buttonList[i - 1][j])
                        }
                        if (i < 4) {
                            numOfLighted += updateButton(buttonList[i + 1][j])
                        }
                        if (j < 4) {
                            numOfLighted += updateButton(buttonList[i][j + 1])
                        }
                        if (j > 0) {
                            numOfLighted += updateButton(buttonList[i][j - 1])
                        }
                    }
                    binding.clicksText.text = numOfClicks.toString()

                    //If all the buttons' state are set to unlighted, loop the 2D button list to set visibility to Gone and edit the clicks TV to a You win! Text View
                    if (numOfLighted <= 0) {
                        binding.clicksText.text = "You win!"
                        for (list in buttonList) {
                            for (button in list) {
                                button.visibility = View.GONE
                            }
                        }
                        view.findNavController()
                            .navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                    else if (numOfClicks >= 15 && numOfLighted < 25) {
                        view.findNavController()
                            .navigate(R.id.action_gameFragment_to_gameOverFragment)
                    }
                }
            }
        }


        val timer = object: CountDownTimer(61000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                view?.findNavController()?.navigate(R.id.action_gameFragment_to_gameOverFragment)
            }
        }
        timer.start()

    }

}
