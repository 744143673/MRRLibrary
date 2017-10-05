package com.hhg.mrrlibrary.widgets;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hhg.mrrlibrary.R;

/**
 * Created by hhg on 2017/7/23.
 * description  : 通用的DialogFragment
 * version      : 1.0.0
 */


public class CommDialogFragment extends DialogFragment {

    private TextView tvTitle;
    private TextView tvContent;
    private Button btnNegative;
    private Button btnPositive;
    private View viewSpace;

    private static String strContent;
    private static boolean isShowCancel;
    private static PosOnClickListener pos0nClickListener;

    public static CommDialogFragment getInstance(String strContent, boolean isShowCancel, PosOnClickListener pos0nClickListener) {
        CommDialogFragment.strContent = TextUtils.isEmpty(strContent) ? "程序正在运行...." : strContent;
        CommDialogFragment.isShowCancel = isShowCancel;
        CommDialogFragment.pos0nClickListener = pos0nClickListener;
        return new CommDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Holo_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new CommDialogFragmentOnKeyListener());
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setWindowAnimations(R.style.up_down_animation);
//        View view = inflater.inflate(R.layout.dialog_fragment_comm, container, false);

        //以下是  设置宽度填满屏幕
        //需要用android.R.id.content这个view
        View view = inflater.inflate(R.layout.dialog_fragment_comm, ((ViewGroup) getDialog().getWindow().findViewById(android.R.id.content)), false);//需要用android.R.id.content这个view
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处  setBackgroundDrawable 必须设置
//        setLayout必须 在 setContentView之后, 调用;并且  这里的-1,-2可以设置为任意高度;
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//这2行,和上面的一样,注意顺序就行;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent = view.findViewById(R.id.tvContent);
        btnNegative = view.findViewById(R.id.btnNegative);
        btnPositive = view.findViewById(R.id.btnPositive);
        viewSpace = view.findViewById(R.id.viewSpace);

        tvTitle.setText("提 示");
        tvContent.setText(strContent + "");
        btnNegative.setText("取 消");

        if (isShowCancel)
            btnNegative.setVisibility(View.VISIBLE);
        else
            btnNegative.setVisibility(View.GONE);

        btnPositive.setText("确 定");
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (pos0nClickListener != null)
                    pos0nClickListener.onPosClick();
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() == null)
            return;
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getDialog() != null)
            dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    //
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
//        if (isShowTitle)
//            builder.setTitle(strTitle);
//        builder.setMessage(strContent);
//        builder.setPositiveButton(strPositiveText, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                if (pos0nClickListener != null)
//                    pos0nClickListener.onPosClick();
//            }
//        });
//        if (isShowNega)
//            builder.setNegativeButton(strNegativeText, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//        return builder.create();
//    }

    public interface PosOnClickListener {
        void onPosClick();
    }

    private class CommDialogFragmentOnKeyListener implements DialogInterface.OnKeyListener {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            return false;
        }
    }
}
