package com.jose.tal_chess;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;



/**
 * Created by joalphon on 06-Sep-16.
 */
public class BoxView extends LinearLayout {
    public BoxView(Context context) {
        super(context);
        initializeViews(context);
    }

    public BoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public BoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.box, this);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        ImageView imageView = (ImageView) this.findViewById(R.id.box);
        imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }


}
