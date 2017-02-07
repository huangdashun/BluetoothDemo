package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ScrollView scrollView;
    private TextView tv;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ListView lv;
    private BluetoothAdapter mBluetoothAdapter;
    private Set<BluetoothDevice> mBondedDevices;

    private void assignViews() {
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        tv = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        lv = (ListView) findViewById(R.id.listView1);
        //获取蓝牙适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* //获取蓝牙适配器
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //调用使用蓝牙的意图
        //ACTION_REQUEST_DISCOVERABLE 此常数用于开启蓝牙的发现
        //ACTION_STATE_CHANGED 通知蓝牙状态已经改变
        //ACTION_FOUND  用于接受关于所发现的每个设备的信息
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent,0);
        //获取蓝牙设备列表
        //set集合无序，不可重复   list有序，可重复
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();*/
        assignViews();
    }

    //打开蓝牙
    public void on(View view) {
        if (!mBluetoothAdapter.isEnabled()) {
            //目前是关闭的，要打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
            Toast.makeText(MainActivity.this, "Turned on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Already on", Toast.LENGTH_SHORT).show();
        }
    }

    //展示蓝牙设备列表
    public void list(View view) {
        mBondedDevices = mBluetoothAdapter.getBondedDevices();
        if(mBondedDevices.isEmpty()){
            Toast.makeText(MainActivity.this, "列表是空的", Toast.LENGTH_SHORT).show();
        }
        ArrayList list = new ArrayList();
        for (BluetoothDevice bluetoothDevice : mBondedDevices) {
            list.add(bluetoothDevice.getName());
            Log.v("MainActivity",bluetoothDevice.getName().toString());
        }
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",
                Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
        lv.setAdapter(adapter);
    }
    //关闭蓝牙
    public void off(View view){
        mBluetoothAdapter.disable();
        Toast.makeText(MainActivity.this, "Turned Off", Toast.LENGTH_SHORT).show();
    }
    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible,0);

    }
}
