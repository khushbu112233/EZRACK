package ezracks.com.ezracks.Webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ezracks.com.ezracks.R;
import ezracks.com.ezracks.utils.MySSLSocketFactory1;

public class WebseriveUrl {
    public static ProgressDialog mProgressDialog;
    public static String BASE_URL ="https://api.yourcargoonline.com/index.php/rtsapi/";
    // public static String BASE_URL = "https://api.yourcargoonline.com/index.php/api/";

    // https://api.yourcargoonline.com/index.php/api/
    public static String LOGIN = BASE_URL+"login";
    public static String GET_ROUTE = BASE_URL+"getRoute";
    public static String InsertDriverOrder=BASE_URL+"insertDriverOrder";
    public static String ChangePassword = BASE_URL+"changePassword";

    public static String GetData(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String PostData(String url, Map<String, String> map) {
        String s = "";
        try {
            HttpClient httpClient = getNewHttpClient();
            ;
            HttpPost httpPost = new HttpPost(url);

            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                reqEntity.addPart(entry.getKey(), new StringBody(entry.getValue()));
            }
            httpPost.setEntity(reqEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.e("s", "" + s);
            HttpEntity httpEntity = httpResponse.getEntity();
            s = readResponse(httpResponse);
            Log.e("s", "" + s);
        } catch (Exception exception) {
            Log.e("s", "ex " + exception);
            exception.printStackTrace();

        }
        return s;
    }

    public static HttpClient getNewHttpClient() {
        try {
            // Log.v("truststore","1------------------");
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            //  Log.v("truststore","2------------------"+trustStore);
            SSLSocketFactory sf = new MySSLSocketFactory1(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            //SSLSocketFactory sslFactory = new SimpleSSLSocketFactory(null);
            //  sslFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static String readResponse(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;

    }


    public static void showProgress(Context context) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();
        try {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mProgressDialog.show();
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgress() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
