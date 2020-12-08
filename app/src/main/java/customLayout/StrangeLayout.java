package customLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class StrangeLayout extends ViewGroup {

    private int rowHeight = 200;
    private int paddingTop = 5;

    @Override
    protected int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public StrangeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left = l + getPaddingLeft();
        int right = r - getPaddingRight();
        int top = t + getPaddingTop();

        int width = getWidth();
        int viewWidth = width/2 - 5;
        int middleLeft = width/2 - (viewWidth/2);
        boolean odd = false;

        for(int i = 0 ;i< count; i++){

            View child = getChildAt(i);

            if(odd){
                child.layout(middleLeft, top, middleLeft + viewWidth, top + rowHeight);
                odd= false;
            }
            else {
                View child1 = getChildAt(i+1);
                child.layout(left, top, left + viewWidth, top + rowHeight);
                if(child1!=null)child1.layout(right - viewWidth, top, right, top + rowHeight);
                i++;
                odd = true;
            }

            top+=rowHeight + 5;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int maxHeight = getChildCount() * (rowHeight + paddingTop);
        setMeasuredDimension(getMeasure(widthMeasureSpec, getWidth()), getMeasure(heightMeasureSpec, maxHeight));
    }

    private int getMeasure(int spec, int desired) {
        switch (MeasureSpec.getMode(spec)){
            case MeasureSpec.EXACTLY :
                return MeasureSpec.getSize(spec);
            case MeasureSpec.AT_MOST:
                return Math.min(MeasureSpec.getSize(spec), desired);

            case MeasureSpec.UNSPECIFIED:
            default:
                return desired;
        }
    }
}
