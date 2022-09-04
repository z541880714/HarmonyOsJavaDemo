package com.lionel.zc.javaDemo1;

import com.lionel.zc.javaDemo1.slice.SliceAirport;
import com.lionel.zc.javaDemo1.slice.SliceName;
import com.lionel.zc.javaDemo1.slice.SliceRailway;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class FnmcdAbility extends Ability {


    public final static String ACTION_NAME = "FNCMD_ABILITY_NAME_SLICE";
    public final static String ACTION_RAILWAY = "FNCMD_ABILITY_RAIWAY_SLICE";
    public final static String ACTION_AIRPORT = "FNCMD_ABILITY_AIRPORT_SLICE";

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SliceName.class.getName());
//        addActionRoute(ACTION_NAME, SliceName.class.getName());
        addActionRoute(ACTION_RAILWAY, SliceRailway.class.getName());
        addActionRoute(ACTION_AIRPORT, SliceAirport.class.getName());
    }
}
