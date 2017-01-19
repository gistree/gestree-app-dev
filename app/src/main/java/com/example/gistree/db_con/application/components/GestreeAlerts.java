package com.example.gistree.db_con.application.components;

import android.content.Context;

import com.example.gistree.db_con.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GestreeAlerts {

    public static void errorAlert(Context c, int errorCode, String errorMessage, String errorName){
        new SweetAlertDialog(c, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(errorName + "Error - " + errorCode)
            .setContentText(errorMessage)
            .setConfirmText("OK")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            })
            .show();
    }
    public static void errorAlert(Context c, int errorCode, String errorMessage, String errorName, String errorOn){
        new SweetAlertDialog(c, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(errorName + "Error - " + errorCode)
            .setContentText(errorMessage + System.getProperty("line.separator") + System.getProperty("line.separator") + "ID da Árvore - " + errorOn)
            .setConfirmText("OK")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            })
            .show();
    }
    public static void warningAlert(Context c, String warningMessage){
        new SweetAlertDialog(c, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("AVISO")
            .setContentText(warningMessage)
            .setConfirmText("OK")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            }).show();
    }
    public static SweetAlertDialog progressAlert(Context c, String progressMessage){
        SweetAlertDialog sad = new SweetAlertDialog(c, SweetAlertDialog.PROGRESS_TYPE);
        sad.getProgressHelper().setBarColor(R.color.red_btn_bg_color);
        sad.setTitleText(progressMessage);
        sad.setCancelable(false);
        sad.show();
        return sad;
    }
    public static void successAlert(Context c, String successMessage){
        new SweetAlertDialog(c, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sucesso")
                .setContentText(successMessage)
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }

}
