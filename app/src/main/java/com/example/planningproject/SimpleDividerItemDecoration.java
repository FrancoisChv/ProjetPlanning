package com.example.planningproject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * SimpleDividerItemDecoration est une classe permettant de gerer l'affichage des differentes listes.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration{

    private Drawable mDivider;

    /* Procedure gerant l'esthetisme de la liste. */
    public SimpleDividerItemDecoration(Resources resources) {

        mDivider = resources.getDrawable(R.drawable.divider);
    }

    /* Procedure permettant de rajouter des elements de decoration a une liste. */
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
        }
    }
}