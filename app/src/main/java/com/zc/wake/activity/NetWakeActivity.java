package com.zc.wake.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.zc.wake.R;
import com.zc.wake.popup.BaseMacPopup;
import com.zc.wake.wake.Wake;

import pl.droidsonroids.gif.GifImageView;


public class NetWakeActivity extends Activity implements View.OnLongClickListener {
    public static final  String MY_MAC ="mac";
    private BaseMacPopup baseMacPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_wake);
        RelativeLayout rl = findViewById(R.id.rl);
        GifImageView gifImageView = findViewById(R.id.gif);
        gifImageView.setOnLongClickListener(this);
        FlowingGradientClass grad = new FlowingGradientClass();

                //translate
        grad.setBackgroundResource(R.drawable.trans)
                .onRelativeLayout(rl)
                .setTransitionDuration(1000)
                .start();
        baseMacPopup = new BaseMacPopup(this);

    }



    public void click(View view) {
        String mac = SPUtils.getInstance().getString(MY_MAC);
        if(mac == null || mac.isEmpty()){
            baseMacPopup.showPopupWindow();
            return;
        }

        Wake.sendPacket(this,null,SPUtils.getInstance().getString(MY_MAC),null,9);
    }

    @Override
    public boolean onLongClick(View v) {
        baseMacPopup.showLong();
        return true;
    }
}
