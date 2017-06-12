package ezracks.com.ezracks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.Model.Store;
import ezracks.com.ezracks.ezracks.MainActivity;

/**
 * Created by aipxperts-ubuntu-01 on 12/5/17.
 */

public class AutocompleteCustomArrayAdapter extends ArrayAdapter<Store> {

    Context mContext;
    int layoutResourceId;
    public ArrayList<Store> StoreNameArrayList;

    public AutocompleteCustomArrayAdapter(Context mContext, int layoutResourceId,ArrayList<Store> StoreNameArrayList) {

        super(mContext, layoutResourceId, StoreNameArrayList);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.StoreNameArrayList = StoreNameArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try{

            if(convertView==null){
                // inflate the layout
                LayoutInflater inflater =  ((MainActivity)mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
            }


            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textViewItem = (TextView) convertView.findViewById(R.id.txtstore_name);
            textViewItem.setText(StoreNameArrayList.get(position).getStore_name()+""+StoreNameArrayList.get(position).getAddress());


        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }
}