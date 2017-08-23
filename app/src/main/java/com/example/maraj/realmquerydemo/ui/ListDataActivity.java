package com.example.maraj.realmquerydemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.maraj.realmquerydemo.R;
import com.example.maraj.realmquerydemo.adapter.MyDataAdapter;
import com.example.maraj.realmquerydemo.controller.RealmQueryManagement;
import com.example.maraj.realmquerydemo.model.MyData;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListDataActivity extends AppCompatActivity {

    private RealmResults<MyData> arrData;
    private RecyclerView recyclerView;
    private MyDataAdapter mAdapter;
    Realm realm;
    private Context mContext=ListDataActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        //get realm instance
        this.realm = RealmQueryManagement.with(this).getRealm();
        arrData=RealmQueryManagement.getInstance().getAllRecords();
        if(!RealmQueryManagement.getInstance().hasMyData()){
            showToast("No Data In Realm DataBase");
        }else {
            setUpRecyclerView();
        }
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MyDataAdapter(arrData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    public void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
