package com.example.maraj.realmquerydemo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maraj.realmquerydemo.R;
import com.example.maraj.realmquerydemo.controller.RealmQueryManagement;
import com.example.maraj.realmquerydemo.model.MyData;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Search Data
    MyData data;
    //flag for checking search operation
    boolean isSearch=false;
    private EditText mEtName, mEtAddress, mEtNumber, mEtEmail, mEtSearch;
    private Button mBtnSave, mBtnNext, mBtnSearch, mBtnUpdate, mBtnDelete, mBtnClear;
    private Context mContext = MainActivity.this;
    //Realm Object
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        //get realm instance
        this.realm = RealmQueryManagement.with(this).getRealm();
    }

    private void findViews() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAddress = (EditText) findViewById(R.id.et_adress);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtSearch = (EditText) findViewById(R.id.et_search);

        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnClear = (Button) findViewById(R.id.btn_clear);

        mBtnSave.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnSave) {
            isSearch=false;
            String name = mEtName.getText().toString().trim();
            String address = mEtAddress.getText().toString().trim();
            String number = mEtNumber.getText().toString().trim();
            String email = mEtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                showToast("Enter Name");
                return;
            }
            if (TextUtils.isEmpty(address)) {
                showToast("Enter Address");
                return;
            }
            if (TextUtils.isEmpty(number)) {
                showToast("Enter Number");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                showToast("Enter Email");
                return;
            }

            MyData data = new MyData();
            data.setId(RealmQueryManagement.getInstance().getAllRecords().size() + 1);
            data.setName(name);
            data.setAddress(address);
            data.setNumber(Long.valueOf(number));
            data.setEmail(email);

            RealmQueryManagement.getInstance().insertData(data);
            setEmptyFields();
        } else if (view == mBtnNext) {
            isSearch=false;
            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);
        } else if (view == mBtnSearch) {
            isSearch=true;
            String id = mEtSearch.getText().toString().trim();
            if (TextUtils.isEmpty(id)) {
                showToast("Enter Id For Search Record");
                return;
            }
            data = RealmQueryManagement.getInstance().search(Long.valueOf(id));
            if (data != null) {
                mEtName.setText(data.getName());
                mEtAddress.setText(data.getAddress());
                mEtNumber.setText("" + data.getNumber());
                mEtEmail.setText(data.getEmail());
            } else {
                showToast("No Record Found");
            }
        } else if(view == mBtnDelete){
            isSearch=false;
            String id = mEtSearch.getText().toString().trim();
            if (TextUtils.isEmpty(id)) {
                showToast("Enter Id For Search Record");
                return;
            }
            RealmQueryManagement.getInstance().delete(Integer.parseInt(id));
            showToast("Record deleted");
            setEmptyFields();
        }else if(view == mBtnUpdate){
            if(isSearch) {
            String name = mEtName.getText().toString().trim();
            String address = mEtAddress.getText().toString().trim();
            String number = mEtNumber.getText().toString().trim();
            String email = mEtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                showToast("Enter Name");
                return;
            }
            if (TextUtils.isEmpty(address)) {
                showToast("Enter Address");
                return;
            }
            if (TextUtils.isEmpty(number)) {
                showToast("Enter Number");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                showToast("Enter Email");
                return;
            }
                MyData data1=new MyData();
                data1.setId(data.getId());
                data1.setName(name);
                data1.setAddress(address);
                data1.setNumber(Long.valueOf(number));
                data1.setEmail(email);
                RealmQueryManagement.getInstance().update(data1);

                showToast("Recard Updated");
                setEmptyFields();
            }else {
                showToast("Please Search The Record First");
            }
            isSearch=false;
        }else if (view == mBtnClear) {
            showToast("Database Cleared");
            RealmQueryManagement.getInstance().clearAll();
            setEmptyFields();
        }
    }

    private void setEmptyFields() {
        mEtName.setText("");
        mEtAddress.setText("");
        mEtNumber.setText("");
        mEtEmail.setText("");
        mEtSearch.setText("");
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
