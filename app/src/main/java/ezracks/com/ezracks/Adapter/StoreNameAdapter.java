package ezracks.com.ezracks.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ezracks.com.ezracks.Model.Store;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.ezracks.NewOrderActivity;
import ezracks.com.ezracks.utils.Pref;

public class StoreNameAdapter extends BaseAdapter implements Filterable {

    public  ArrayList<Store> StoreNameArrayList;
    public  ArrayList<Store> StoreNameArrayList_filter;



    private Context context;

    public StoreNameAdapter(Context context, ArrayList<Store> StoreNameArrayList) {
        this.context = context;
        this.StoreNameArrayList=StoreNameArrayList;


    }
    @Override
    public int getCount() {
        if (StoreNameArrayList != null) {
            return StoreNameArrayList.size();
        } else {
            return 0;
        }
    }
    @Override
    public String getItem(int position) {
        if (StoreNameArrayList != null) {
            return StoreNameArrayList.get(position).getStore_name();
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {


        TextView txtstore_name;
        LinearLayout ll1;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_store_name_list, null);

        // Constants.hidekeyboard(v);
        txtstore_name=(TextView)v.findViewById(R.id.txtstore_name);
        ll1 = (LinearLayout)v.findViewById(R.id.ll1);
        txtstore_name.setText(StoreNameArrayList.get(position).getStore_name()+","+StoreNameArrayList.get(position).getAddress());

        //  Pref.setValue(context,"id",StoreNameArrayList.get(position).getId());

        txtstore_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Pref.setValue(context,"isclick","1");
                return false;
            }
        });
/*
        txtstore_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Pref.setValue(context,"selected_position",position);
                return false;
            }
        });*/
        return v;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Store> results = new ArrayList<Store>();
                if (StoreNameArrayList_filter == null)
                    StoreNameArrayList_filter = StoreNameArrayList;
                if (constraint != null) {
                    if (StoreNameArrayList_filter != null && StoreNameArrayList_filter.size() > 0) {
                        for (final Store g : StoreNameArrayList_filter) {
                            if (g.getStore_name().toLowerCase()
                                    .contains(constraint.toString().toLowerCase()))
                                results.add(g);


                        }
                    }
                    oReturn.values = results;

                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {

             /*   ArrayList<Store> newValues = (ArrayList<Store>) results.values;
                if(newValues !=null) {
                    for (int i = 0; i < newValues.size(); i++) {
                        //add(newValues.get(i));

                    }
                    Log.e("result_count",""+results.count);
                    if(results.count>0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }*/
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}
