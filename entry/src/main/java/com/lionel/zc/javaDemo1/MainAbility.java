package com.lionel.zc.javaDemo1;

import com.lionel.zc.javaDemo1.slice.MainAbilitySlice;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.utils.Color;
import ohos.agp.window.service.Window;
import ohos.agp.window.service.WindowManager;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
//        getWindow().addFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_STATUS);
        Window window = getWindow();
        window.setStatusBarColor(Color.WHITE.getValue());
//        window.setStatusBarVisibility(Component.VISIBLE);

    }
}