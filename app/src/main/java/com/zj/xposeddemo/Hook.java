package com.zj.xposeddemo;
import android.os.Bundle;
import android.view.View;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(loadPackageParam.packageName.equals("com.zj.wuaipojie")) {
            return;
        }
        XposedHelpers.findAndHookMethod("com.zj.wuaipojie.ui.ChallengeSixth", loadPackageParam.classLoader,
                "onCreate", Bundle.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        View img = (View)XposedHelpers.callMethod(param.thisObject,"findViewById", 0x7f0800de);// 控件的id
                        img.setVisibility(View.GONE);
                    }
                });
    }
}