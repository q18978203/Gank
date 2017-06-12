package cn.sjwym.gank.gank.utils.permission;

public interface PermissionResult {
    void onGranted();

    void onDenied();
}
