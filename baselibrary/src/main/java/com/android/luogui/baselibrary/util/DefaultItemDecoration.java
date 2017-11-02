package com.android.luogui.baselibrary.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述：分割线
 * Created by LuoGui on 2017/3/7.
 */

public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int height;
    private int width;
    private int leftPadding = 0;
    private int rightPadding = 0;

    /**
     * @param color 颜色
     * @param height 横线高度
     * @param width 竖线宽度
     */
    public DefaultItemDecoration(Context context, @ColorRes int color, int height, int width) {
      this(context, color, height, width,null);
    }

    /**
     * @param color 颜色
     * @param height 横线高度
     * @param width 竖线宽度
     * @param dashPathEffect 线段样式
     */
    public DefaultItemDecoration(Context context, @ColorRes int color, int height, int width, DashPathEffect dashPathEffect) {
        this(context, color, height,width,0,null);
    }

    public DefaultItemDecoration(Context context, @ColorRes int color, int height, int width, int padding){
        this(context, color, height,width,padding,null);
    }

    /**
     * @param context
     * @param color 颜色
     * @param height 横线高度
     * @param width 竖线宽度
     * @param padding 间隔
     * @param dashPathEffect 线段样式
     */
    public DefaultItemDecoration(Context context, @ColorRes int color, int height, int width, int padding,
                                 DashPathEffect dashPathEffect) {
        this(context, color, height,width,padding,padding,dashPathEffect);
    }


    /**
     * @param context
     * @param color 颜色
     * @param height 横线高度
     * @param width 竖线宽度
     * @param leftPadding
     * @param rightPadding
     * @param dashPathEffect 线段样式
     */
    public DefaultItemDecoration(Context context, @ColorRes int color, int height, int width, int leftPadding,
                                 int rightPadding,
                                 DashPathEffect dashPathEffect) {
        paint = new Paint();
        paint.setColor(context.getResources().getColor(color));
        paint.setStyle ( Paint.Style.STROKE ) ;
        paint.setPathEffect (dashPathEffect) ;
        this.height = height;
        this.width = width;
        this.leftPadding = leftPadding;
        this.rightPadding = rightPadding;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int positon = parent.getChildAdapterPosition(view);
        if (layoutManager instanceof GridLayoutManager){

            int spanCount = getSpanCount(parent);
            if (isLastRaw(parent, positon, spanCount, state.getItemCount())&&
                    isLastColum(parent, positon, spanCount, state.getItemCount())){
                outRect.set(0, 0,0, 0);
            }
            else if (isLastRaw(parent, positon, spanCount, state.getItemCount()))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(0, 0,width, 0);
            } else if (isLastColum(parent, positon, spanCount, state.getItemCount()))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(0, 0, 0, height);
            } else
            {
                outRect.set(0, 0,width,
                       height);
            }

        }
        else if (layoutManager instanceof LinearLayoutManager){
            int orien = ((LinearLayoutManager)layoutManager).getOrientation();
            if (positon!=state.getItemCount()-1){
                if (orien== LinearLayoutManager.HORIZONTAL){
                    outRect.set(0,0,width,0);
                }else {
                    outRect.set(0,0,0,height);
                }
            }

        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            drawGridLayout(c,parent);
        }
        else if (layoutManager instanceof LinearLayoutManager){
            int orien = ((LinearLayoutManager)layoutManager).getOrientation();
            if (orien== LinearLayoutManager.HORIZONTAL){
                drawHorizontal(c,parent);
            }else {
                drawVertical(c,parent);
            }
        }
    }

    /**
     * @param c
     * @param parent
     */
    private void drawGridLayout(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            if (isLastRaw(parent, i, spanCount,childCount)&&
                    isLastColum(parent, i, spanCount, childCount)){
            }
            else if (isLastRaw(parent, i, spanCount,childCount))// 如果是最后一行，则不需要绘制底部
            {
                drawGridVertical(c,child);

            } else if (isLastColum(parent, i, spanCount,childCount))// 如果是最后一列，则不需要绘制右边
            {
                drawGridHorizontal(c,child);
            } else
            {
                drawGridHorizontal(c,child);
                drawGridVertical(c,child);
            }

        }
    }

    /**画竖线
     * @param c
     * @param child
     */
    private void drawGridVertical(Canvas c, View child) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        final int left = child.getRight() + params.rightMargin;

        paint.setStrokeWidth(width);
            Path path = new Path();
            path.moveTo(left+width/2,child.getTop()+leftPadding);
            path.lineTo(left+width/2,child.getBottom()-rightPadding);
            c.drawPath(path,paint);
    }

    /**画横线
     * @param c
     * @param child
     */
    private void drawGridHorizontal(Canvas c, View child) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        final int bottom = child.getBottom() + params.bottomMargin;

        paint.setStrokeWidth(height);
        Path path = new Path();
        path.moveTo(child.getLeft()+leftPadding,bottom+height/2);
        path.lineTo(child.getRight()-rightPadding,bottom+height/2);
        c.drawPath(path,paint);
    }

    /** Linear 垂直布局
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft()+leftPadding;
        final int right = parent.getWidth() - parent.getPaddingRight()-rightPadding;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            paint.setStrokeWidth(height);
            Path path = new Path();
            path.moveTo(left,top+height/2);
            path.lineTo(right,top+height/2);
            c.drawPath(path,paint);
        }
    }

    /** 水平布局
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop()+leftPadding;
        final int bottom = parent.getHeight() - parent.getPaddingBottom()-rightPadding;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            paint.setStrokeWidth(width);
            Path path = new Path();
            path.moveTo(left+width/2,top);
            path.lineTo(left+width/2,bottom);
            c.drawPath(path,paint);
        }
    }


    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount)
    {
        if (childCount==0){
            return true;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            int lastNum = childCount % spanCount==0?spanCount:childCount%spanCount;
            childCount = childCount - lastNum;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                int lastNum = childCount % spanCount==0?spanCount:childCount%spanCount;
                childCount = childCount - lastNum;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }


}
