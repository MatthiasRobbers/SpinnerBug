
package com.example.spinnerbug;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setAdapter(ArrayAdapter.createFromResource(this,
//                R.array.items,
//                R.layout.spinner_item));
        spinner.setAdapter(new WrapAdapter(this, getResources().getStringArray(R.array.items)));
    }

    class WrapAdapter extends ArrayAdapter<String> {
        private Context mContext;
        private String[] mItems;

        public WrapAdapter(Context context, String[] items) {
            super(context, 0, items);
            mContext = context;
            mItems = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item, parent, false);
            textView.setText(mItems[position]);
            return textView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            final TextView textView = (TextView) LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item, parent, false);
            textView.setText(mItems[position]);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setSingleLine(false);
                }
            });
            return textView;
        }
    }
}
