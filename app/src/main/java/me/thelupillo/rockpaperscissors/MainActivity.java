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
                // Make invisible the other buttons
                paperButton.setVisibility(View.INVISIBLE);
                scissorsButton.setVisibility(View.INVISIBLE);

                // Make visible the "Play Again" button
                playAgainButton.setVisibility(View.VISIBLE);

                // The pressed button make not clickable
                rockButton.setClickable(false);

                fightWithRobot("rock");

            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make invisible the other buttons
                rockButton.setVisibility(View.INVISIBLE);
                scissorsButton.setVisibility(View.INVISIBLE);

                // Make visible the "Play Again" button
                playAgainButton.setVisibility(View.VISIBLE);

                // The pressed button make not clickable
                paperButton.setClickable(false);

                fightWithRobot("paper");
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make invisible the other buttons
                rockButton.setVisibility(View.INVISIBLE);
                paperButton.setVisibility(View.INVISIBLE);

                // Make visible the "Play Again" button
                playAgainButton.setVisibility(View.VISIBLE);

                // The pressed button make not clickable
                scissorsButton.setClickable(false);

                fightWithRobot("scissors");
            }
        });
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    public String getRandomOption() {
        switch (((Double) (Math.random() * 3)).intValue()) {
            case 0:
                return "rock";
            case 1:
                return "paper";
            default:
                return "scissors";
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

    public void fightWithRobot(String userOption) {
        switch (getRandomOption()) {
            case "rock":
                robotEmojiTextView.setText(R.string.rock_emoji);
                if (userOption.equals("paper")) {
                    winGame();
                } else if (userOption.equals("scissors")) {
                    loseGame();
                } else {
                    tieGame();
                }
                break;
            case "paper":
                robotEmojiTextView.setText(R.string.paper_emoji);
                if (userOption.equals("rock")) {
                    loseGame();
                } else if (userOption.equals("scissors")) {
                    winGame();
                } else {
                    tieGame();
                }
                break;
            case "scissors":
                robotEmojiTextView.setText(R.string.scissors_emoji);
                if (userOption.equals("rock")) {
                    winGame();
                } else if (userOption.equals("paper")) {
                    loseGame();
                } else {
                    tieGame();
                }
                break;
        }
    }
}
