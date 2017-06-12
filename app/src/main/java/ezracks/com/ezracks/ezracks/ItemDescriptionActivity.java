package ezracks.com.ezracks.ezracks;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.Webservice.WebseriveUrl;
import ezracks.com.ezracks.databinding.ItemDescriptionLayoutBinding;
import ezracks.com.ezracks.utils.ConnectionDetector;
import ezracks.com.ezracks.utils.Constants;
import ezracks.com.ezracks.utils.FontCustom;
import ezracks.com.ezracks.utils.InputFilterMinMax;
import ezracks.com.ezracks.utils.Pref;

/**
 * Created by aipxperts-ubuntu-01 on 31/3/17.
 */

public class ItemDescriptionActivity extends BaseActivity {
    /**
     * declare for use view binding.
     */
    ItemDescriptionLayoutBinding mBinding;
    /**
     *pass  item description
     */
    String descrip1,descrip2,descrip3,descrip4,descrip5,descrip6,descrip7,descrip8;
    ConnectionDetector cd;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    boolean isvalid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.item_description_layout);
        cd = new ConnectionDetector(this);
        /**
         *  set material design functionality
         */
        // settext_watcher();
        set_typeface();
        /**
         * validate for 1 to 999
         */
        setfilter();
        Log.e("flvalue",""+Pref.getValue(ItemDescriptionActivity.this, "FLStore", ""));
        mBinding.edtCornerPost.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip1",""));
        mBinding.edtDrb48.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip2",""));
        mBinding.edtDrb40.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip3",""));
        mBinding.edtShelving4048.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip4",""));
        mBinding.edtCornerPost7Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip5",""));
        mBinding.edtDrb48Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip6",""));
        mBinding.edtDrb40Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip7",""));
        mBinding.edtCornerPost7Purple.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip8",""));
        mBinding.edtCornerPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip1 = mBinding.edtCornerPost.getText().toString();

                Pref.setValue(ItemDescriptionActivity.this,"descrip1",descrip1);
                }
        });
        mBinding.edtDrb48.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip2 = mBinding.edtDrb48.getText().toString();

                Pref.setValue(ItemDescriptionActivity.this,"descrip2",descrip2);
            }
        });
        mBinding.edtDrb40.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip3 = mBinding.edtDrb40.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip3",descrip3);
            }
        });
        mBinding.edtShelving4048.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip4 = mBinding.edtShelving4048.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip4",descrip4);
            }
        });
        mBinding.edtCornerPost7Black.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip5 = mBinding.edtCornerPost7Black.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip5",descrip5);
            }
        });
        mBinding.edtDrb48Black.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip6 = mBinding.edtDrb48Black.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip6",descrip6);
            }
        });
        mBinding.edtDrb40Black.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip7 = mBinding.edtDrb40Black.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip7",descrip7);
            }
        });
        mBinding.edtCornerPost7Purple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                descrip8 = mBinding.edtCornerPost7Purple.getText().toString();
                Pref.setValue(ItemDescriptionActivity.this,"descrip8",descrip8);
            }
        });

        /**
         * submit new order.
         */
        mBinding.txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.txtNext.setEnabled(false);
                if (mBinding.edtCornerPost.getText().toString().length() > 0) {
                    isvalid = true;
                }
                if(mBinding.edtDrb48.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtDrb40.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtCornerPost7Black.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtDrb48Black.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtDrb40Black.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtShelving4048.getText().toString().length()>0)
                {
                    isvalid = true;
                }
                if(mBinding.edtCornerPost7Purple.getText().toString().length()>0)
                {
                    isvalid = true;
                }




               /* if(mBinding.edtCornerPost.getText().toString().length()==0)
                {
                    mBinding.edtCornerPost.setError("Corner Post 7' gray/silver vein is required!");
                }else if(mBinding.edtDrb48.getText().toString().length()==0)
                {
                    mBinding.edtDrb48.setError("DRB 48''gray/silver vein is required!");
                }else if(mBinding.edtDrb40.getText().toString().length()==0)
                {
                    mBinding.edtDrb40.setError("DRB 40''gray/silver vein is required!");
                }else if(mBinding.edtCornerPost7Black.getText().toString().length()==0)
                {
                    mBinding.edtCornerPost7Black.setError("Corner Post 7' Black USED is required!");
                }else if(mBinding.edtDrb48Black.getText().toString().length()==0)
                {
                    mBinding.edtDrb48Black.setError("DRB 48'' Black USED is required!");
                }else if(mBinding.edtDrb40Black.getText().toString().length()==0)
                {
                    mBinding.edtDrb40Black.setError("DRB 40'' Black USED is required!");

                }else if(mBinding.edtShelving4048.getText().toString().length()==0)
                {
                    mBinding.edtShelving4048.setError("Shelving 40*48''  is required!");
                }else if(mBinding.edtCornerPost7Purple.getText().toString().length()==0)
                {
                    mBinding.edtCornerPost7Purple.setError("Corner Post 7' purple  is required!");
                }*/
                if(isvalid==false){
                    Toast.makeText(ItemDescriptionActivity.this,"Please provide at least one quantity!",Toast.LENGTH_LONG).show();
                    mBinding.txtNext.setEnabled(true);
                }else if(isvalid==true){

                    descrip1 = mBinding.edtCornerPost.getText().toString();
                    descrip2 = mBinding.edtDrb48.getText().toString();
                    descrip3 = mBinding.edtDrb40.getText().toString();
                    descrip4 = mBinding.edtShelving4048.getText().toString();
                    descrip5 = mBinding.edtCornerPost7Black.getText().toString();
                    descrip6 = mBinding.edtDrb48Black.getText().toString();
                    descrip7 = mBinding.edtDrb40Black.getText().toString();
                    descrip8 = mBinding.edtCornerPost7Purple.getText().toString();
                    Pref.setValue(ItemDescriptionActivity.this,"descrip1",descrip1);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip2",descrip2);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip3",descrip3);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip4",descrip4);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip5",descrip5);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip6",descrip6);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip7",descrip7);
                    Pref.setValue(ItemDescriptionActivity.this,"descrip8",descrip8);


                    if(descrip1.length()==0)
                    {
                        descrip1="0";
                    }

                    if(descrip2.length()==0)
                    {
                        descrip2="0";
                    }

                    if(descrip3.length()==0)
                    {
                        descrip3="0";
                    }

                    if(descrip4.length()==0)
                    {
                        descrip4="0";
                    }

                    if(descrip5.length()==0)
                    {
                        descrip5="0";
                    }

                    if(descrip6.length()==0)
                    {
                        descrip6="0";
                    }

                    if(descrip7.length()==0)
                    {
                        descrip7="0";
                    }

                    if(descrip8.length()==0)
                    {
                        descrip8="0";
                    }

                    /**
                     * call api for submit new order details.
                     */
                    if(cd.isConnectingToInternet()){
                        new ExecuteTask().execute();
                    }else
                    {
                        Toast.makeText(ItemDescriptionActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        /**
         * on back pressed
         */
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        descrip1 = mBinding.edtCornerPost.getText().toString();
        descrip2 = mBinding.edtDrb48.getText().toString();
        descrip3 = mBinding.edtDrb40.getText().toString();
        descrip4 = mBinding.edtShelving4048.getText().toString();
        descrip5 = mBinding.edtCornerPost7Black.getText().toString();
        descrip6 = mBinding.edtDrb48Black.getText().toString();
        descrip7 = mBinding.edtDrb40Black.getText().toString();
        descrip8 = mBinding.edtCornerPost7Purple.getText().toString();
      /*  Pref.setValue(ItemDescriptionActivity.this,"descrip1",descrip1);
        Pref.setValue(ItemDescriptionActivity.this,"descrip2",descrip2);
        Pref.setValue(ItemDescriptionActivity.this,"descrip3",descrip3);
        Pref.setValue(ItemDescriptionActivity.this,"descrip4",descrip4);
        Pref.setValue(ItemDescriptionActivity.this,"descrip5",descrip5);
        Pref.setValue(ItemDescriptionActivity.this,"descrip6",descrip6);
        Pref.setValue(ItemDescriptionActivity.this,"descrip7",descrip7);
        Pref.setValue(ItemDescriptionActivity.this,"descrip8",descrip8);
*/
        mBinding.edtCornerPost.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip1",""));
        mBinding.edtDrb48.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip2",""));
        mBinding.edtDrb40.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip3",""));
        mBinding.edtShelving4048.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip4",""));
        mBinding.edtCornerPost7Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip5",""));
        mBinding.edtDrb48Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip6",""));
        mBinding.edtDrb40Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip7",""));
        mBinding.edtCornerPost7Purple.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip8",""));

    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  mBinding.edtCornerPost.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip1",""));
        mBinding.edtDrb48.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip2",""));
        mBinding.edtDrb40.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip3",""));
        mBinding.edtShelving4048.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip4",""));
        mBinding.edtCornerPost7Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip5",""));
        mBinding.edtDrb48Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip6",""));
        mBinding.edtDrb40Black.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip7",""));
        mBinding.edtCornerPost7Purple.setText(Pref.getValue(ItemDescriptionActivity.this,"descrip8",""));
*/
    }

    private void setfilter() {
        mBinding.edtCornerPost.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtDrb48.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtDrb40.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtShelving4048.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtCornerPost7Black.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtDrb48Black.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtDrb40Black.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
        mBinding.edtCornerPost7Purple.setFilters(new InputFilter[]{new InputFilterMinMax(1, 999)});
    }

    public void checkValid(EditText edt)
    {

        if (Integer.parseInt(edt.getText().toString()) >= 0 && Integer.parseInt(edt.getText().toString()) <= 999) {


        } else {
            edt.setError("Please enter Quantity within 0-999");

        }
    }
    private void set_typeface() {
        mBinding.edtCornerPost.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtDrb40Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtDrb40.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtDrb48Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtDrb48.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtCornerPost7Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtCornerPost7Purple.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.edtShelving4048.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtNext.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtTitle.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtCornerPost.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtDrb48Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtDrb48.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtDrb40Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtDrb40.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtCornerPost7Black.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtCornerPost7Purple.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));
        mBinding.txtShelving4048.setTypeface(FontCustom.setFont(ItemDescriptionActivity.this));

    }
    public void settext_watcher()
    {
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtCornerPost,mBinding.txtCornerPost);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtDrb48,mBinding.txtDrb48);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtDrb40,mBinding.txtDrb40);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtShelving4048,mBinding.txtShelving4048);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtCornerPost7Black,mBinding.txtCornerPost7Black);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtDrb48Black,mBinding.txtDrb48Black);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtDrb40Black,mBinding.txtDrb40Black);
        setTextWatcher_new(ItemDescriptionActivity.this,mBinding.edtCornerPost7Purple,mBinding.txtCornerPost7Purple);

    }

    /*  public boolean checkPermissionREAD_EXTERNAL_STORAGE(
              final Context context) {
          int currentAPIVersion = Build.VERSION.SDK_INT;
          if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
              if (ContextCompat.checkSelfPermission(context,
                      Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                  if (ActivityCompat.shouldShowRequestPermissionRationale(
                          (Activity) context,
                          Manifest.permission.READ_EXTERNAL_STORAGE)) {
                      showDialog("External storage", context,Manifest.permission.READ_EXTERNAL_STORAGE);

                  } else {
                      ActivityCompat
                              .requestPermissions(
                                      (Activity) context,
                                      new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                      MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                  }
                  return false;
              } else {
                  return true;
              }

          } else {
              return true;
          }
      }
      public void showDialog(final String msg, final Context context,
                             final String permission) {
          AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
          alertBuilder.setCancelable(true);
          alertBuilder.setTitle("Permission necessary");
          alertBuilder.setMessage(msg + " permission is necessary");
          alertBuilder.setPositiveButton(android.R.string.yes,
                  new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                          ActivityCompat.requestPermissions((Activity) context,
                                  new String[] { permission },
                                  MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                      }
                  });
          AlertDialog alert = alertBuilder.create();
          alert.show();
      }*/
    class ExecuteTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WebseriveUrl.showProgress(ItemDescriptionActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            Map<String, String> param = new HashMap<String, String>();

            param.put(Constants.Item_all_detail[0], Pref.getValue(ItemDescriptionActivity.this,"driveid",""));
            param.put(Constants.Item_all_detail[1], Pref.getValue(ItemDescriptionActivity.this,"Order_Number",""));
            param.put(Constants.Item_all_detail[2], descrip1);
            param.put(Constants.Item_all_detail[3], descrip2);
            param.put(Constants.Item_all_detail[4], descrip3);
            param.put(Constants.Item_all_detail[5], descrip4);
            param.put(Constants.Item_all_detail[6], descrip5);
            param.put(Constants.Item_all_detail[7], descrip6);
            param.put(Constants.Item_all_detail[8], descrip7);
            param.put(Constants.Item_all_detail[9], descrip8);
            param.put(Constants.Item_all_detail[10],Pref.getValue(ItemDescriptionActivity.this,"Name",""));
            param.put(Constants.Item_all_detail[11],Pref.getValue(ItemDescriptionActivity.this,"Address",""));
            param.put(Constants.Item_all_detail[12],Pref.getValue(ItemDescriptionActivity.this,"City",""));
            param.put(Constants.Item_all_detail[13],Pref.getValue(ItemDescriptionActivity.this,"State",""));
            param.put(Constants.Item_all_detail[14],Pref.getValue(ItemDescriptionActivity.this,"Zip",""));
            param.put(Constants.Item_all_detail[15],Pref.getValue(ItemDescriptionActivity.this,"OrderDate",""));
            param.put(Constants.Item_all_detail[16],Pref.getValue(ItemDescriptionActivity.this,"StoreCode",""));
            param.put(Constants.Item_all_detail[17],Pref.getValue(ItemDescriptionActivity.this,"FLStore",""));
            param.put(Constants.Item_all_detail[18],Pref.getValue(ItemDescriptionActivity.this,"authKey",""));
            param.put(Constants.Item_all_detail[19],Pref.getValue(ItemDescriptionActivity.this,"status_1",""));
            String res=WebseriveUrl.PostData(WebseriveUrl.InsertDriverOrder,param);
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

                //update_preference_token(getActivity(), json2.getString("token"));
                if (json2.getString("success").equals("true")) {

                    JSONObject jsonObject = json2.getJSONObject("response");
                    String message = jsonObject.optString("message");

                    Log.e("message",""+message);
                    Pref.setValue(ItemDescriptionActivity.this,"success","1");
                    Toast.makeText(ItemDescriptionActivity.this,message,Toast.LENGTH_LONG).show();
                    Pref.setValue(ItemDescriptionActivity.this, "Address", "");
                    Pref.setValue(ItemDescriptionActivity.this, "Name","");
                    //date();
                    Pref.setValue(ItemDescriptionActivity.this,"OrderDate","");
                    Pref.setValue(ItemDescriptionActivity.this, "City","");
                    Pref.setValue(ItemDescriptionActivity.this, "State", "");
                    Pref.setValue(ItemDescriptionActivity.this, "FLStore","");
                    Pref.setValue(ItemDescriptionActivity.this, "Zip", "");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip1","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip2","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip3","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip4","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip5","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip6","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip7","");
                    Pref.setValue(ItemDescriptionActivity.this,"descrip8","");
                    Intent intent = new Intent(ItemDescriptionActivity.this,
                            NewOrderActivity.class);
                    startActivity(intent);
                    finishAffinity();

                    Pref.setValue(ItemDescriptionActivity.this,"success","1");

                }else if(json2.getString("error").equals("true"))
                {
                    JSONArray jsonArray = json2.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String message = jsonObject.optString("message");

                        Log.e("message",""+message);
                        Toast.makeText(ItemDescriptionActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
