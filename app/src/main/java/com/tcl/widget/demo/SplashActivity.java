package com.tcl.widget.demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tcl.widget.demo.ui.base.BaseActivity;
import com.tcl.widget.demo.ui.widget.DrawTextImageView;

import static android.widget.Toast.makeText;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/10 11:08
 * @copyright TCL-MIG
 */

public class SplashActivity extends BaseActivity {
    TextView btn;
    DrawTextImageView drawTextImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FragmentContainerActivity.launch(this,CheckViewFragmentA.class, null);
        //FragmentContainerActivity.launch(this,RadarFragment.class, null);
        //FragmentContainerActivity.launch(this,BeizerFragmentA.class, null);
        //FragmentContainerActivity.launch(this,BeizerFragment2A.class, null);
        //FragmentContainerActivity.launch(this,PathBooleanFragment.class, null);
        //FragmentContainerActivity.launch(this,PathFillTypeFragmentA.class, null);
        //FragmentContainerActivity.launch(this, MatrixFragmentA.class, null);
        //FragmentContainerActivity.launch(this, EventDispatchAndConsumeFragmentA.class, null);
        //FragmentContainerActivity.launch(this, SlideSwitchFragmentA.class, null);
        //FragmentContainerActivity.launch(this, LayoutOptimizeFragmentA.class, null);
        //FragmentContainerActivity.launch(SplashActivity.this, MaterialEdittextFragmentA.class, null);
        setContentView(R.layout.activity_splash);
        btn = (TextView) findViewById(R.id.btn);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SplashActivity.this, SecondActivity.class));
                showDialog(DIALOG_PAUSED_ID);
            }
        });
        //FragmentContainerActivity.launch(SplashActivity.this, HealthTableFragment.class, null);
        //FragmentContainerActivity.launch(SplashActivity.this, CoordinateLayoutFragment.class, null);
        //FragmentContainerActivity.launch(SplashActivity.this, MiClockFragment.class, null);
        //FragmentContainerActivity.launch(SplashActivity.this, WatchBordFragment.class, null);
       // finish();
        getMDActionBar().setDisplayHomeAsUpEnabled(true);
        getMDActionBar().setHomeButtonEnabled(true);
        drawTextImageView = (DrawTextImageView) findViewById(R.id.drawTextImageView);
        drawTextImageView.setDrawText("48℃");


    }



    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return false;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    static final int DIALOG_PAUSED_ID = 0;
    static final int DIALOG_GAMEOVER_ID = 1;
    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        Dialog dialog = null;
        switch (id){
            case DIALOG_PAUSED_ID:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pause").setIcon(R.drawable.ic_launcher).setMessage("Pause it").setPositiveButton("确定", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(SplashActivity.this, "onClicke pause",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0,0);
                        toast.show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                        removeDialog(DIALOG_PAUSED_ID);
                    }
                });
                dialog = builder.create();

                break;
            case DIALOG_GAMEOVER_ID:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Game Over").setMessage("Game Over").setPositiveButton("确定", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        makeText(SplashActivity.this, "onClicke Game Over",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder2.create();
                break;
            default:
                break;

        }
        return  dialog;
    }
}
