package receipe002.thegenius.kyungjoon.receipelist003;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getHttpResponseDataAndsetListItems();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("");

                    getHttpResponseDataAndsetListItems();
                    return true;
                case R.id.navigation_dashboard:

                    Toast.makeText(getApplicationContext(), "대쉬보두.", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getApplicationContext(), "ㅡ노티피케이션.", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


    public void clickImage(View view) {

        TextView textView1 = (TextView) findViewById(R.id.textView1);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        String url = textView1.getText().toString();

    //    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), textView2.toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);


        startActivity(intent);

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }


    public void getHttpResponseDataAndsetListItems() {


        final List<HashMap> contactList2 = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        //title, image,url
        client.get("http://35.194.150.240:8080/receipe/receipeListToJson", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String jsonString) {


                        Gson gson = new Gson();
                        Type type = new TypeToken<List<HashMap>>() {
                        }.getType();

                        //jsonString to arrayList make
                        List<HashMap> contactList = gson.fromJson(jsonString, type);

                        try {
                            addArrayListToAdaptor(contactList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );


    }

    public void addArrayListToAdaptor(List<HashMap> responseArrList) throws Exception {

        ListView listview;

        //커스텀 아답타 입니다sdlkfsdlkflsdkflksldkf
        ListViewAdapter adapter = new ListViewAdapter(MainActivity.this);


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);


        for (HashMap contactOne : responseArrList) {

            Log.d("sdlkflskdf-->", (String) contactOne.get("title"));

            String imgUrl = "http://35.194.150.240:8080/receipeImage/" + contactOne.get("image");


            adapter.addItem(imgUrl, (String) contactOne.get("url"), (String) contactOne.get("title"));
        }

    }

}
