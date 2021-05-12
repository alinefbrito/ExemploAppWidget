package com.example.android.appwidgetsample;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


public class ExemploNewView extends View {
    public ExemploNewView(Context context) {
        super(context);
    }


    //alterada classe jetbrais para utilizar a annotation default do Android
// Erro por falha da ide
    public ExemploNewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExemploNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExemploNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
