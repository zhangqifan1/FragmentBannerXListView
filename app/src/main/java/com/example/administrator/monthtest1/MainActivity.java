package com.example.administrator.monthtest1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 热门
     */
    private Button mBut1;
    /**
     * 分类
     */
    private Button mBut2;
    /**
     * 作者
     */
    private Button mBut3;
    private Frag1Fragment frag1Fragment;
    private Frag2Fragment frag2Fragment;
    private Frag3Fragment frag3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBut1 = (Button) findViewById(R.id.but1);
        mBut1.setOnClickListener(this);
        mBut2 = (Button) findViewById(R.id.but2);
        mBut2.setOnClickListener(this);
        mBut3 = (Button) findViewById(R.id.but3);
        mBut3.setOnClickListener(this);
        frag1Fragment = new Frag1Fragment();
        frag2Fragment = new Frag2Fragment();
        frag3Fragment = new Frag3Fragment();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.but1:
                transaction.replace(R.id.frame,frag1Fragment).commit();
                break;
            case R.id.but2:
                transaction.replace(R.id.frame,frag2Fragment).commit();
                break;
            case R.id.but3:
                transaction.replace(R.id.frame,frag3Fragment).commit();
                break;
        }
    }


}
