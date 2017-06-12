package ezracks.com.ezracks.ezracks;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import ezracks.com.ezracks.R;
import ezracks.com.ezracks.databinding.StoreDetailsLayoutBinding;
import ezracks.com.ezracks.utils.FontCustom;
import ezracks.com.ezracks.utils.Pref;

/**
 * Created by aipxperts-ubuntu-01 on 31/3/17.
 */

public class StoreDetailsActivity extends BaseActivity {
    /**
     * declare for use view binding.
     */
    StoreDetailsLayoutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.store_details_layout);
        /**
         *  set material design functionality
         */
        // settext_watcher();
        set_typeface();
        /**
         * set text of view
         */
        settext();
        mBinding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "Name", mBinding.edtName.getText().toString());

            }
        });

        mBinding.edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "Address", mBinding.edtAddress.getText().toString());

            }
        });
        mBinding.edtCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "City", mBinding.edtCity.getText().toString());

            }
        });
        mBinding.edtState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "State", mBinding.edtState.getText().toString());
            }
        });
        mBinding.edtZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "Zip", mBinding.edtZipCode.getText().toString());

            }
        });

        mBinding.edtFlStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Pref.setValue(StoreDetailsActivity.this, "FLStore", mBinding.edtFlStore.getText().toString());

            }
        });



        /**
         * go to to item description page
         */
        mBinding.txtPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if (mBinding.edtName.getText().toString().length() == 0) {
                    mBinding.edtName.setError("Name is required!");
                } else if (mBinding.edtAddress.getText().toString().length() == 0) {
                    mBinding.edtAddress.setError("Address is required!");
                } else if (mBinding.edtCity.getText().toString().length() == 0) {
                    mBinding.edtCity.setError("City is required!");
                } else if (mBinding.edtState.getText().toString().length() == 0) {
                    mBinding.edtState.setError("State is required!");
                } else if (mBinding.edtZipCode.getText().toString().length() == 0) {
                    mBinding.edtZipCode.setError("Zip Code is required!");
                } else if (mBinding.edtFlStore.getText().toString().length() == 0) {
                    mBinding.edtFlStore.setError("FL Store to be Reassigned is required!");
                } else {
                    Log.v("data", "complete");
                    //}
                    /**
                     *  store details save in share pref and use next screen.
                     */
                    Pref.setValue(StoreDetailsActivity.this, "Address", mBinding.edtAddress.getText().toString());
                    Pref.setValue(StoreDetailsActivity.this, "Name", mBinding.edtName.getText().toString());
                    Pref.setValue(StoreDetailsActivity.this, "City", mBinding.edtCity.getText().toString());
                    Pref.setValue(StoreDetailsActivity.this, "State", mBinding.edtState.getText().toString());
                    Pref.setValue(StoreDetailsActivity.this, "Zip", mBinding.edtZipCode.getText().toString());
                    Pref.setValue(StoreDetailsActivity.this, "FLStore", mBinding.edtFlStore.getText().toString());


                    Intent intent = new Intent(StoreDetailsActivity.this, ItemDescriptionActivity.class);
                    startActivity(intent);
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
    protected void onResume() {
        super.onResume();

        mBinding.edtName.setText(Pref.getValue(StoreDetailsActivity.this, "Name", ""));
        mBinding.edtAddress.setText(Pref.getValue(StoreDetailsActivity.this, "Address", ""));
        mBinding.edtCity.setText(Pref.getValue(StoreDetailsActivity.this, "City", ""));
        mBinding.edtState.setText(Pref.getValue(StoreDetailsActivity.this, "State", ""));
        mBinding.edtZipCode.setText(Pref.getValue(StoreDetailsActivity.this, "Zip", ""));
        mBinding.edtFlStore.setText(Pref.getValue(StoreDetailsActivity.this, "FLStore", ""));
    }

    private void settext() {

        //mBinding.edtName.setText(NewOrderActivity.storeArrayList.get(0).getStore_name());
        // Log.e("store_Detail_array size","$$"+NewOrderActivity.storeArrayList.size()+"   ");
        // Log.e("store detail position",""+Pref.getValue(StoreDetailsActivity.this,"selected_position",0));
       /* if (NewOrderActivity.storeArrayList.size()>0) {
           *//* for (int i = 0; i < NewOrderActivity.storeArrayList.size(); i++) {
                if(Pref.getValue(StoreDetailsActivity.this,"id","").equalsIgnoreCase(NewOrderActivity.storeArrayList.get(i).getId()))
                {*//*
            if(NewOrderActivity.storeArrayList.size()==1)
            {
                mBinding.edtName.setText(NewOrderActivity.storeArrayList.get(0).getStore_name());
                mBinding.edtAddress.setText(NewOrderActivity.storeArrayList.get(0).getAddress());
                mBinding.edtCity.setText(NewOrderActivity.storeArrayList.get(0).getCity());
                mBinding.edtState.setText(NewOrderActivity.storeArrayList.get(0).getState());
                mBinding.edtZipCode.setText(NewOrderActivity.storeArrayList.get(0).getZipcode());
                mBinding.edtFlStore.setText(NewOrderActivity.storeArrayList.get(0).getFl_store_to_be_reassigned());

            }else
            {

                mBinding.edtName.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getStore_name());
                mBinding.edtAddress.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getAddress());
                mBinding.edtCity.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getCity());
                mBinding.edtState.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getState());
                mBinding.edtZipCode.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getZipcode());
                mBinding.edtFlStore.setText(NewOrderActivity.storeArrayList.get(Pref.getValue(StoreDetailsActivity.this,"selected_position",0)).getFl_store_to_be_reassigned());

            }
            //}
            // }
        }*/


        mBinding.edtName.setText(Pref.getValue(StoreDetailsActivity.this, "Name", ""));
        mBinding.edtAddress.setText(Pref.getValue(StoreDetailsActivity.this, "Address", ""));
        mBinding.edtCity.setText(Pref.getValue(StoreDetailsActivity.this, "City", ""));
        mBinding.edtState.setText(Pref.getValue(StoreDetailsActivity.this, "State", ""));
        mBinding.edtZipCode.setText(Pref.getValue(StoreDetailsActivity.this, "Zip", ""));
        mBinding.edtFlStore.setText(Pref.getValue(StoreDetailsActivity.this, "FLStore", ""));
    }
    private void set_typeface() {
        mBinding.edtName.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtName.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.edtAddress.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtAddress.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.edtCity.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtCity.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.edtState.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtState.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.edtZipCode.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtZipCode.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.edtFlStore.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtFlStore.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtTitle.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
        mBinding.txtPlaceOrder.setTypeface(FontCustom.setFont(StoreDetailsActivity.this));
    }
    public void settext_watcher()
    {
        // setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtStoreCode,mBinding.txtStoreCode);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtName,mBinding.txtName);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtAddress,mBinding.txtAddress);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtCity,mBinding.txtCity);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtState,mBinding.txtState);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtZipCode,mBinding.txtZipCode);
        setTextWatcher_new(StoreDetailsActivity.this,mBinding.edtFlStore,mBinding.txtFlStore);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // NewOrderActivity.storeArrayList.clear();
        mBinding.edtName.setText(Pref.getValue(StoreDetailsActivity.this, "Name", ""));
        mBinding.edtAddress.setText(Pref.getValue(StoreDetailsActivity.this, "Address", ""));
        mBinding.edtCity.setText(Pref.getValue(StoreDetailsActivity.this, "City", ""));
        mBinding.edtState.setText(Pref.getValue(StoreDetailsActivity.this, "State", ""));
        mBinding.edtZipCode.setText(Pref.getValue(StoreDetailsActivity.this, "Zip", ""));
        mBinding.edtFlStore.setText(Pref.getValue(StoreDetailsActivity.this, "FLStore", ""));


    }
}
