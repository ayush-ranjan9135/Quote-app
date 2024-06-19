package com.example.quotes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView quotesTextView;
    private Button nextQuoteButton;
    private String[] quotesArray;
    private int currentQuoteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quotesTextView = findViewById(R.id.quots_tv);
        nextQuoteButton = findViewById(R.id.next_quote_btn);
        quotesArray = getResources().getStringArray(R.array.quotes);

        if (quotesArray.length > 0) {
            quotesTextView.setText(quotesArray[0]);
        }

        nextQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuote();
            }
        });
    }

    private void showNextQuote() {
        if (currentQuoteIndex < quotesArray.length - 1) {
            currentQuoteIndex++;
            quotesTextView.setText(quotesArray[currentQuoteIndex]);
        } else {
            quotesTextView.setText(R.string.last_quotes_message);
            nextQuoteButton.setEnabled(false);  // Disable the button when no more quotes are left
            showExitDialog();
        }
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("No more quotes left. Do you want to exit the app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();  // Close the app
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, just close the dialog
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
