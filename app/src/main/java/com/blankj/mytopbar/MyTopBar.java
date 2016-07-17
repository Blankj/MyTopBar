package com.blankj.mytopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*********************************************
 * author: Blankj on 2016/6/13 09:57
 * blog:   http://blankj.com
 * e-mail: blankj@qq.com
 *********************************************/
public class MyTopBar extends RelativeLayout {

    public MyTopBar(Context context) {
        this(context, null);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftLayoutParams;
    private LayoutParams mRightLayoutParams;
    private LayoutParams mTitleLayoutParams;

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 通过这个方法，将你在attrs.xml中定义的declare-styleable的所有属性值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar__titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar__titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar__title);
        // 获取完TypedArray的值后，一般要调用recycle方法来避免重新创建时的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // 为组件元素设置相应的布局元素
        mLeftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mLeftButton, mLeftLayoutParams);

        mRightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightLayoutParams);

        mTitleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleLayoutParams);

        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    // 映射传入的接口对象
    private topbarClickListener mListener;


    // 接口对象，实现回调机制，在回调方法中
    // 通过映射的接口对象调用接口中的方法
    // 而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();

        // 右按钮点击事件
        void rightClick();
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }


    /**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}




