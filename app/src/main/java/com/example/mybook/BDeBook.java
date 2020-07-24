package com.example.mybook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ConnectivityManagerCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BDeBook extends AppCompatActivity {

    private System system;
    private  WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bde_book);
        if (haveNetworkConnection()){
            websiteView();
        }else {
            alertView();
        }
    }

    private void websiteView(){
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://bdebooks.com/");
        myWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed(){
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    private boolean haveNetworkConnection(){
        ConnectivityManager cm = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private void alertView(){
        AlertDialog alertDialog = new AlertDialog.Builder(this.getApplicationContext()).create();
        alertDialog.setTitle("Attention");
        alertDialog.setMessage("You are not connected to the Internet");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }

        );

        alertDialog.show();

    }
}
