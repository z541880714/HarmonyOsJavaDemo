package com.lionel.zc.javaDemo1;

import com.lionel.zc.javaDemo1.tools.MyLog;
import ohos.aafwk.ability.AbilityPackage;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class MyApplication extends AbilityPackage {
    private static MyApplication app;

    @Override
    public void onInitialize() {
        super.onInitialize();
        app = this;

        MyLog.i("global application onInitialized!!");
    }

    public static TaskDispatcher getGlobalDispatch() {
        return app.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
    }
}
