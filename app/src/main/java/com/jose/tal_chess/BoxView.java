package com.jose.tal_chess;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.sax.RootElement;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;




/**
 * Created by joalphon on 06-Sep-16.
 */
/*
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
*/

public class BoxView extends LinearLayout{
    ViewGroup.LayoutParams layoutParams;
    public BoxView(Context context) {
        super(context);
        //initializeViews(context);
        //createTable(context);
        practice(context);
    }

    public BoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //initializeViews(context);
        //createTable(context);;
        practice(context);
    }
    /*
    private void createTable(Context context){
        String[] row = { "ROW1", "ROW2", "Row3", "Row4", "Row 5", "Row 6",
                "Row 7" };
        String[] column = { "COLUMN1", "COLUMN2", "COLUMN3", "COLUMN4",
                "COLUMN5", "COLUMN6" };
        int rl=row.length; int cl=column.length;
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
        layoutParams.height = 100;
        layoutParams.width = 100;
        TableLayout tableLayout = new TableLayout(context);
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.width = 100;
        tableRowParams.height = 100;
        TableRow tableRow = new TableRow(context);
        TextView textView1 = new TextView(context);
        TextView textView2 = new TextView(context);
        textView1.setBackgroundColor(getResources().getColor(R.color.blackSquare));
        textView2.setBackgroundColor(getResources().getColor(R.color.whiteSquare));
        textView1.setMinimumHeight(100);
        textView2.setMinimumHeight(100);
        textView1.setMinWidth(100);
        textView2.setMinimumWidth(100);
        tableRow.addView(textView1,tableRowParams);
        tableLayout.addView(tableRow,layoutParams);
        this.addView(tableLayout);




    }*/
    @Override
    protected void onMeasure(int a,int b){
        super.onMeasure(a,a);
    }
    private void practice(Context context){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TableLayout tableLayout = new TableLayout(context);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.setWeightSum(8);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            linearLayout.setBackground(getResources().getDrawable(R.drawable.customborder));
        }
        else{
            linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.customborder));
        }
        int k=0;
        for (int i = 0; i < 8; i++) {
            TableRow tableRow = new TableRow(context);
            //tableRow.setGravity(Gravity.CENTER);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            if(k==1){
                k=0;
            }
            else{
                k=1;
            }
            for (int j = 0; j < 8; j++) {
                TextView_New button = new TextView_New(context);
                final int buttonNumber = (j + i * 8);
                button.setText("" + buttonNumber);
                if(k==0){
                    button.setBackgroundColor(getResources().getColor(R.color.blackSquare));
                    k=1;
                }
                else{
                    button.setBackgroundColor(getResources().getColor(R.color.whiteSquare));
                    k=0;
                }
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));

                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
        }

        linearLayout.addView(tableLayout);
        this.addView(linearLayout);
        /*
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);


        relativeLayout.setLayoutParams(layoutParams);
        int cell_width = relativeLayout.getWidth()/8;
        int cell_height = cell_width;
        //relativeLayout.setLayoutParams(layoutParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            relativeLayout.setBackground(getResources().getDrawable(R.drawable.customborder));
        }
        else{
            relativeLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.customborder));
        }
        TableLayout tableLayout = new TableLayout(context);
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TabLayout.LayoutParams.WRAP_CONTENT);

        TableRow tableRow = new TableRow(context);
        TextView textView1 = new TextView_New(context);
        TextView textView2 = new TextView_New(context);


        textView1.setBackgroundColor(getResources().getColor(R.color.blackSquare));
        textView2.setBackgroundColor(getResources().getColor(R.color.whiteSquare));
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
        textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
        //textView1.setLayoutParams(new TableRow.LayoutParams(1));
        //textView2.setLayoutParams(new TableRow.LayoutParams(2));



        TextView textView3 = new TextView_New(context);
        TextView textView4 = new TextView_New(context);
        //textView3.setLayoutParams(new TableRow.LayoutParams(3));
        //textView4.setLayoutParams(new TableRow.LayoutParams(4));

        textView3.setBackgroundColor(getResources().getColor(R.color.blackSquare));
        textView4.setBackgroundColor(getResources().getColor(R.color.whiteSquare));
        textView3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
        textView4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));


        textView1.setText("Cell1");
        textView2.setText("Cell2");

        textView4.setText("Cell4");
        textView3.setText("Cell3");



        textView2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        tableRow.addView(textView1,tableRowParams);
        tableRow.addView(textView2,tableRowParams);
        tableRow.addView(textView3,tableRowParams);
        tableRow.addView(textView4,tableRowParams);
        tableLayout.addView(tableRow,tableLayoutParams);
        relativeLayout.addView(tableLayout);

        this.addView(relativeLayout);

        */


        /*
        TextView textView = new TextView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 100);
        textView.setLayoutParams(params);
        textView.setText("Cell1");
        textView.setBackgroundColor(getResources().getColor(R.color.blackSquare));
        //textView.setTextSize(40);
        textView.setTextColor(getResources().getColor(R.color.whiteSquare));
        //textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(params);
        textView1.setText("Cell2");
        textView1.
        textView1.setBackgroundColor(getResources().getColor(R.color.whiteSquare));
        textView1.setTextColor(getResources().getColor(R.color.blackSquare));

        relativeLayout.addView(textView);
        relativeLayout.addView(textView1);
        this.addView(relativeLayout);
        */
    }
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.example, this);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        //ImageView imageView = (ImageView) this.findViewById(R.id.A8);
        //imageView.setLayoutParams();
        //imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        /*

        ImageView imageView = (ImageView) this.findViewById(R.id.box);
        imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        */
    }
}

