package com.example.onestore;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INTERNET CONNECTION CHECKING
        if (isConnectedToInternet()==false) {
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        }
        //INTERNET CONNECTION CHECKING

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Line of Code for opening links in app
        webView.setWebViewClient(new IgnoreSSLErrorWebViewClient());
        webView.loadUrl("https://www.one-store.ml/");

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/web.html");

            }
        });
    }

    //Code For Back Button
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    // A new webclient that ignore ssl errors
    private class IgnoreSSLErrorWebViewClient extends WebViewClient {
        // You can all the class anything

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // When an error occurs, ignore and go on
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request.getUrl()));
            //if you want to open a specific activity from webview
            if (String.valueOf(request.getUrl()).startsWith("https://api.whatsapp.com/") || String.valueOf(request.getUrl()).startsWith("mailto") || String.valueOf(request.getUrl()).startsWith("http://maps.google.com/") ) {
                view.stopLoading();

                //if you want to open an activity chooser
                //our url scheme starts with intent,g app found then it opens that,otherwise it catch
                try {
                    Intent intent = Intent.parseUri((String.valueOf(request.getUrl())), Intent.URI_INTENT_SCHEME);
                    startActivity(intent);
                } catch (Exception e) {

                }
            }


            return true;
        }

    }//SSL END CURLY BRACKETS

        //INTERNET CONNECTION CHECKING

        boolean isConnectedToInternet() {

            ConnectivityManager cm = (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            return isConnected;

        }
        //INTERNET CONNECTION CHECKING
}