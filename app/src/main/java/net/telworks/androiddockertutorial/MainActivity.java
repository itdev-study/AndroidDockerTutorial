package net.telworks.androiddockertutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webSpace;
    WebBackForwardList mWebBackForwardList;
    String historyUrl;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        webSpace = (WebView)findViewById(R.id.webSpace);
        WebSettings ws = webSpace.getSettings();
        ws.setJavaScriptEnabled(true);

        webSpace.setWebViewClient(new WebViewClient() );
        // webcontrol.loadUrl("https://www.rbc.ru/");
        webSpace.loadUrl("file:///android_asset/Content.HTML");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    protected void onUserLeaveHint() {
//        Toast toast = Toast.makeText(getApplicationContext(), "Нажата кнопка HOME", Toast.LENGTH_SHORT);
//        toast.show();
//        super.onUserLeaveHint();
//    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        mWebBackForwardList = webSpace.copyBackForwardList();
        if (mWebBackForwardList.getCurrentIndex() > 0)
            historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();

      /*  Toast toast = Toast.makeText(MainActivity.this, "Возврат: "+historyUrl, Toast.LENGTH_LONG);
        toast.show();*/

        if (webSpace.canGoBack()) webSpace.goBack();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mWebBackForwardList = webSpace.copyBackForwardList();
        if (item.getItemId() == R.id.btnBack) {
            if (mWebBackForwardList.getCurrentIndex() > 0)
                historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();

          /*  Toast toast = Toast.makeText(MainActivity.this, "Возврат: "+historyUrl, Toast.LENGTH_LONG);
            toast.show();*/

            if (webSpace.canGoBack()) webSpace.goBack();
        } else
        if (item.getItemId() == R.id.btnForward) {
            if (mWebBackForwardList.getCurrentIndex() > 0)
                historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();

          /*  Toast toast = Toast.makeText(MainActivity.this, "Вперед: " + historyUrl, Toast.LENGTH_LONG);
            toast.show();*/
            if (webSpace.canGoForward()) webSpace.goForward();
        } else
        if (item.getItemId() == R.id.btnMainPage) {
            webSpace.loadUrl("file:///android_asset/Content.HTML");
            /*Toast toast = Toast.makeText(MainActivity.this, "Домой: ", Toast.LENGTH_LONG);
            toast.show();*/

        }else
        if (item.getItemId() == R.id.btnAbout) {
            //webSpace.loadUrl("file:///android_asset/Content.HTML");
           // Toast toast = Toast.makeText(MainActivity.this, "О программе ", Toast.LENGTH_LONG);
          //  toast.show();
            showAboutDialog();
        }

        return true;
    }

    public void showAboutDialog(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("О программе: Справочник по Docker").setPositiveButton("Ok", dialogClickListener)
                .show();
    }
}
