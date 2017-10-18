package receipe002.thegenius.kyungjoon.receipelist003;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);

		Intent intent = getIntent();
		String url = intent.getStringExtra("url");


		webView = (WebView) findViewById(R.id.webview01);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);

		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setProgress(progress * 100);
			}
		});

		webView.setWebViewClient(new InsideWebViewClient());



	}
/*
	@Override
	public void onBackPressed() {
		if(webView.canGoBack()){
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}*/


	private class InsideWebViewClient extends WebViewClient {
		@Override

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;

		}

	}



}