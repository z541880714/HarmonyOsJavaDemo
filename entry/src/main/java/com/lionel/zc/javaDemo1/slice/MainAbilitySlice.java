package com.lionel.zc.javaDemo1.slice;

import com.lionel.zc.fncmd_name.NativeApi;
import com.lionel.zc.javaDemo1.FnmcdAbility;
import com.lionel.zc.javaDemo1.MainAbility;
import com.lionel.zc.javaDemo1.ResourceTable;

import com.lionel.zc.javaDemo1.tools.MyLog;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP, 0x00201, "log_zc");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initElements();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }

    void initElements() {
        Button bt1 = (Button) findComponentById(ResourceTable.Id_id_bt1);
        Button bt2 = (Button) findComponentById(ResourceTable.Id_id_bt2);
        Button bt3 = (Button) findComponentById(ResourceTable.Id_id_bt3);

        //Name
        bt1.setClickedListener(v -> {
            MyLog.i("button 1 click !!");
            navigateFncmdAbilityAlice("");
            MyLog.i(new NativeApi().add(1, 2));
        });

        //railway
        bt2.setClickedListener(v -> {
            MyLog.i("button 2 click !!");
            navigateFncmdAbilityAlice(FnmcdAbility.ACTION_RAILWAY);

        });
        //airport
        bt3.setClickedListener(v -> {
            MyLog.i("button 3 click !!!");
            navigateFncmdAbilityAlice(FnmcdAbility.ACTION_AIRPORT);
        });
    }


    private void navigateFncmdAbilityAlice(String action) {
        Intent intent = new Intent();
        Operation opt = new Intent.OperationBuilder()
                .withAction(action)
                .withBundleName(getBundleName())
                .withAbilityName(FnmcdAbility.class.getName())
                .build();
        intent.setOperation(opt);
        startAbility(intent);
    }
}