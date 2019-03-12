package com.zc.wake.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zc.wake.R;
import com.zc.wake.activity.NetWakeActivity;

import razerdp.basepopup.BasePopupWindow;

import static com.zc.wake.activity.NetWakeActivity.MY_MAC;

public class BaseMacPopup extends BasePopupWindow {

    private EditText mac;

    public BaseMacPopup(Context context) {
        super(context);
        setPopupGravity(Gravity.CENTER);
        iniView();
    }

    private void iniView() {
        mac = findViewById(R.id.et_mac);
        Button button = findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mac.getText()==null || mac.getText().toString().isEmpty()){
                    ToastUtils.showLong("请填入MAC地址");
                    return;
                }
                SPUtils.getInstance().put(MY_MAC, mac.getText().toString());
                ToastUtils.showLong("提交成功");

            }
        });
    }

    public void showLong(){
        mac.setText(SPUtils.getInstance().getString(MY_MAC));
        showPopupWindow();
    }
    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_set_mac);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation(true);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

}
