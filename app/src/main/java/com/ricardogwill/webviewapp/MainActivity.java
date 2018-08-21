package com.ricardogwill.webviewapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        openURL();
    }


    private EditText enterURLEditText;
    private Button openURLButton;
    private WebView webView;

    private Button savedURLButton;

    private SharedPreferences mPref;
    public static final String PREF_NAME = "sp_name";
    private String savedURL;


    public void openURL() {

        enterURLEditText = findViewById(R.id.enter_url_editText);
        openURLButton = findViewById(R.id.open_url_button);
        webView = findViewById(R.id.web_view);

        savedURLButton = findViewById(R.id.saved_url_button);

        mPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        openURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = enterURLEditText.getText().toString();
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(url);

                String storedURL = mPref.getString("URL", url);
                enterURLEditText.setText(storedURL);

                SharedPreferences.Editor editor = mPref.edit();
                editor.putString(PREF_NAME, url);
                editor.apply();

                enterURLEditText.setText(mPref.getString("URL", ""));
                savedURL = storedURL;
                Toast.makeText(MainActivity.this,"Saved " + storedURL,Toast.LENGTH_LONG).show();
            }
        });

        savedURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterURLEditText.setText(savedURL);

            }
        });
    }

}
