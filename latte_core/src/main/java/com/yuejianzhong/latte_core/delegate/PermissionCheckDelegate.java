package com.yuejianzhong.latte_core.delegate;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.yalantis.ucrop.UCrop;
import com.yuejianzhong.latte_core.ui.camera.RequestCodes;
import com.yuejianzhong.latte_core.ui.scanner.ScannerDelegate;
import com.yuejianzhong.latte_core.util.callback.CallbackManager;
import com.yuejianzhong.latte_core.util.callback.CallbackType;
import com.yuejianzhong.latte_core.util.callback.IGlobalCallback;
import com.yuejianzhong.latte_ec.camera.CameraImageBean;
import com.yuejianzhong.latte_ec.camera.LatteCamera;

import org.jetbrains.annotations.NotNull;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class PermissionCheckDelegate extends BaseDelegate {

    //不会直接调用此方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void starCamera() {
        PermissionCheckDelegatePermissionsDispatcher.checkStoryPermissionWithPermissionCheck(this);
//        LatteCamera.INSTANCE.start(this);
    }

    //这个是真正调用的方法
    public void startCamerWithCheck() {
        PermissionCheckDelegatePermissionsDispatcher.starCameraWithPermissionCheck(this);
    }


    //扫描二维码(不直接调用)
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDelegate fragment) {
        fragment.getSupportDelegate().startForResult(new ScannerDelegate(),RequestCodes.SCAN);
    }

    public void startScanWithCheck(BaseDelegate fragment) {
        PermissionCheckDelegatePermissionsDispatcher.startScanWithPermissionCheck(this,fragment);
    }

    @OnPermissionDenied((Manifest.permission.CAMERA))
    void onCameraDenied() {
        ToastUtils.showShort("不允许拍照");
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        ToastUtils.showShort("永久拒绝权限");
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    //存储权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void checkStoryPermission() {
        LatteCamera.INSTANCE.start(this);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onStorageRationale(final PermissionRequest request) {
        showRationaleDialog(request);
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onStorageDenied() {
        ToastUtils.showShort("存储权限已拒绝");
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onStorageNever() {
        ToastUtils.showShort("存储权限已被永久拒绝");
    }

    //不是直接调用此方法
    private void showRationaleDialog(final PermissionRequest request) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            request.proceed();
                        }
                    })
                    .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            request.cancel();
                        }
                    })
                    .setCancelable(false)
                    .setMessage("权限管理")
                    .show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    if (getContext() != null) {
                        UCrop.of(resultUri, resultUri)
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放裁剪后的图片
                        final String pickCropPath = LatteCamera.INSTANCE.createCropFile().getPath();
                        if (pickPath != null && getContext() != null) {
                            UCrop.of(pickPath, Uri.parse(pickCropPath))
                                    .withMaxResultSize(400, 400)
                                    .start(getContext(), this);
                        }
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到裁剪后的数据进行处理
                    @SuppressWarnings("unchecked") final IGlobalCallback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    ToastUtils.showShort("裁剪出错！");
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
//        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.SCAN) {
            if (data != null) {
                final String qrCode = data.getString("SCAN_RESULT");

            }
        }
    }
}
