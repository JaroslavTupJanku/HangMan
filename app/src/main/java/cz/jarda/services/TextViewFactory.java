package cz.jarda.services;

import cz.jarda.hangman.R;
import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextViewFactory
{
    private final Context context;

    public TextViewFactory(Context context)
    {
        this.context = context;
    }

    public TextView createTextView(int id)
    {
        TextView textView = new TextView(context);
        textView.setId(id);
        textView.setText(context.getResources().getText(R.string.empty_char));
        textView.setTextColor(context.getResources().getColor(R.color.white, context.getTheme()));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        textView.setLayoutParams(getLayoutParams());

        return textView;
    }

    private ViewGroup.MarginLayoutParams getLayoutParams()
    {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        //Convert to px
        int marginStart = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
        int marginEnd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
        int marginBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics());

        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(layoutParams);
        marginParams.setMargins(marginStart, 0, marginEnd, marginBottom);
        return marginParams;
    }
}


