package com.example.maraj.realmquerydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maraj.realmquerydemo.R;
import com.example.maraj.realmquerydemo.model.MyData;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    private RealmResults<MyData> arrData;
    private Realm realm;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvId,mTvName, mTvNumber, mTvAddress,mTvEmail;

        public MyViewHolder(View view) {
            super(view);
            mTvId = (TextView) view.findViewById(R.id.tv_id);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvAddress = (TextView) view.findViewById(R.id.tv_address);
            mTvNumber = (TextView) view.findViewById(R.id.tv_number);
            mTvEmail = (TextView) view.findViewById(R.id.tv_email);
        }
    }


    public MyDataAdapter(RealmResults<MyData> arrData) {
        this.arrData = arrData;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyData data = arrData.get(position);
        holder.mTvId.setText(data.getId()+"");
        holder.mTvName.setText(data.getName());
        holder.mTvAddress.setText(data.getAddress());
        holder.mTvNumber.setText(data.getNumber()+"");
        holder.mTvEmail.setText(data.getEmail());
    }
 
    @Override
    public int getItemCount() {
        return arrData.size();
    }
}