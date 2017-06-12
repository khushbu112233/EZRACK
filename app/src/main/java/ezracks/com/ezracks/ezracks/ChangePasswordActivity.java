package ezracks.com.ezracks.ezracks;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ezracks.com.ezracks.Adapter.StoreNameAdapter;
import ezracks.com.ezracks.Model.Store;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.Webservice.WebseriveUrl;
import ezracks.com.ezracks.databinding.ChangePasswordLayoutBinding;
import ezracks.com.ezracks.utils.ConnectionDetector;
import ezracks.com.ezracks.utils.Constants;
import ezracks.com.ezracks.utils.Pref;

/**
 * Created by aipxperts-ubuntu-01 on 25/4/17.
 */

public class ChangePasswordActivity extends BaseActivity {

    ChangePasswordLayoutBinding mBinding;
    String current_pass,new_pass,confirm_new_pass;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.change_password_layout);
        cd = new ConnectionDetector(this);
        mBinding.txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_pass = mBinding.edtOldPass.getText().toString();
                new_pass = mBinding.edtNewPass.getText().toString();
                confirm_new_pass = mBinding.edtNewConfirmPass.getText().toString();
                if(current_pass.equalsIgnoreCase(""))
                {
                    mBinding.edtOldPass.setError("Current Password is required!");
                }else if(new_pass.equalsIgnoreCase(""))
                {
                    mBinding.edtNewPass.setError("New Password is required!");
                }else if(confirm_new_pass.equalsIgnoreCase(""))
                {
                    mBinding.edtNewConfirmPass.setError("Confirm New Password is required!");
                }else
                {
                    if(cd.isConnectingToInternet()){
                        new ExecuteTask().execute();
                    }else
                    {
                        Toast.makeText(ChangePasswordActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    class ExecuteTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // WebseriveUrl.showProgress(NewOrderActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            Map<String, String> param = new HashMap<String, String>();

            param.put(Constants.Change_password_param[0], Pref.getValue(ChangePasswordActivity.this,"authKey",""));
            param.put(Constants.Change_password_param[1], current_pass);
            param.put(Constants.Change_password_param[2], new_pass);
            param.put(Constants.Change_password_param[3], confirm_new_pass);
            String res= WebseriveUrl.PostData(WebseriveUrl.ChangePassword,param);
            Log.e("res....",""+res);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                WebseriveUrl.dismissProgress();
                JSONObject json2;
                Log.e(getLocalClassName(),"result"+result);
                json2 = new JSONObject(result);
                JSONObject jsonObject = json2.optJSONObject("response");
                String message = jsonObject.optString("message");
                Toast.makeText(ChangePasswordActivity.this,message,Toast.LENGTH_LONG).show();
                Pref.setValue(ChangePasswordActivity.this,"success","1");
                Intent intent = new Intent(ChangePasswordActivity.this, NewOrderActivity.class);
                startActivity(intent);
                finish();
                Log.e("json2",""+json2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
