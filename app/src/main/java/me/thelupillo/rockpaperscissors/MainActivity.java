package me.thelupillo.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout mainConstraintLayout;
    TextView robotEmojiTextView, appNameTextView, statusTextView;
    Button rockButton, paperButton, scissorsButton, playAgainButton;

    public final int ROCK_OPTION = 0, PAPER_OPTION = 1, SCISSORS_OPTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Views
         // Layouts
        mainConstraintLayout = findViewById(R.id.mainConstraintLayout);
         // TextViews
        robotEmojiTextView = findViewById(R.id.robotEmojiTextView);
        //appNameTextView = findViewById(R.id.appNameTextView);
        statusTextView = findViewById(R.id.statusTextView);
         // Buttons
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelected(ROCK_OPTION);
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelected(PAPER_OPTION);
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelected(SCISSORS_OPTION);
            }
        });
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }

    public void optionSelected(int userOption) {
        switch (userOption) {
            case ROCK_OPTION:
                // Make invisible the other buttons
                paperButton.setVisibility(View.INVISIBLE);
                scissorsButton.setVisibility(View.INVISIBLE);

                // The pressed button make not clickable
                rockButton.setClickable(false);
                break;
            case PAPER_OPTION:
                // Make invisible the other buttons
                rockButton.setVisibility(View.INVISIBLE);
                scissorsButton.setVisibility(View.INVISIBLE);

                // The pressed button make not clickable
                paperButton.setClickable(false);
                break;
            case SCISSORS_OPTION:
                // Make invisible the other buttons
                rockButton.setVisibility(View.INVISIBLE);
                paperButton.setVisibility(View.INVISIBLE);

                // The pressed button make not clickable
                scissorsButton.setClickable(false);
                break;
        }

        fightWithRobot(userOption);

        // Make visible the "Play Again" button
        playAgainButton.setVisibility(View.VISIBLE);
    }

    public void resetAll() {
        // Change the background color to default
        mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.default_backgroundColor, getTheme()));

        // Make visible all the other buttons
        rockButton.setVisibility(View.VISIBLE);
        paperButton.setVisibility(View.VISIBLE);
        scissorsButton.setVisibility(View.VISIBLE);

        // Make clickable all the other buttons
        rockButton.setClickable(true);
        paperButton.setClickable(true);
        scissorsButton.setClickable(true);

        // Make invisible the "Play Again" button
        playAgainButton.setVisibility(View.INVISIBLE);

        // Set the default robot emoji
        robotEmojiTextView.setText(R.string.robot_emoji);

        // Set the default status message
        statusTextView.setText(R.string.main_status_text);
    }

    public int getRandomOption() {
        switch (((Double) (Math.random() * 3)).intValue()) {
            case ROCK_OPTION:
                return ROCK_OPTION;
            case PAPER_OPTION:
                return PAPER_OPTION;
            default:
                return SCISSORS_OPTION;
        }
    }

    public void winGame() {
        statusTextView.setText(R.string.win_status_text);
        mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.win_backgroundColor, getTheme()));
    }
    public void loseGame() {
        statusTextView.setText(R.string.lose_status_text);
        mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.lose_backgroundColor, getTheme()));
    }
    public void tieGame() {
        statusTextView.setText(R.string.tie_status_text);
        mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.tie_backgroundColor, getTheme()));
    }

    public void fightWithRobot(int userOption) {
        switch (getRandomOption()) {
            case ROCK_OPTION:
                // Change the emoji of the robot's option
                robotEmojiTextView.setText(R.string.rock_emoji);

                switch (userOption) {
                    case ROCK_OPTION:
                        tieGame();
                        break;
                    case PAPER_OPTION:
                        winGame();
                        break;
                    case SCISSORS_OPTION:
                        loseGame();
                        break;
                }
                break;
            case PAPER_OPTION:
                // Change the emoji of the robot's option
                robotEmojiTextView.setText(R.string.paper_emoji);

                switch (userOption) {
                    case ROCK_OPTION:
                        loseGame();
                        break;
                    case PAPER_OPTION:
                        tieGame();
                        break;
                    case SCISSORS_OPTION:
                        winGame();
                        break;
                }
                break;
            case SCISSORS_OPTION:
                // Change the emoji of the robot's option
                robotEmojiTextView.setText(R.string.scissors_emoji);

                switch (userOption) {
                    case ROCK_OPTION:
                        winGame();
                        break;
                    case PAPER_OPTION:
                        loseGame();
                        break;
                    case SCISSORS_OPTION:
                        tieGame();
                        break;
                }
                break;
        }
    }
}
