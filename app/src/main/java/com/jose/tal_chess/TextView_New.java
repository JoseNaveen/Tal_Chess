package com.jose.tal_chess;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class TextView_New extends TextView {

    public TextView_New(Context context) {
        super(context);
    }

    public TextView_New(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView_New(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int a,int b){
        //int width = MeasureSpec.getSize(a);
        super.onMeasure(a,a);
    }
}
