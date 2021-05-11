package com.example.android.appwidgetsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//criar uma nova classe Java
// implementar a herança de um elemento ou diretamente da classe View
//No exemplo será uma Edit Text alterada com o X para limpar o texto
// Após definir a herança implementar todos os contrutores necessário/ sugeridos
// O preview será exibido
// Na pasta drawables adicionar o icone X ( drawable -> add vector -> cliparte --> selecionar a forma)
//Adicionar um icone preto e outro cinza ( opacidade 50%)
//define a variável de acesso ao icone
// cria um helper que instancia o icone - init()
//chama o helper nos contrutores
//se herdar da view prcisa desenhar a interface, sobrescrevendo o método onDraw
//quando herda de uma subclasse é possivel apenas sobrescrever para customizar
public class ExemploCustomView extends AppCompatEditText {


    Drawable mClearButtonImage;

    public ExemploCustomView(Context context) {
        super(context);
        init();
    }

    public ExemploCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExemploCustomView(Context context,
                             AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize Drawable member variable.
        mClearButtonImage =
                ResourcesCompat.getDrawable(getResources(),
                        R.drawable.ic_baseline_clear_opaque_24, null);

        // If the X (clear) button is tapped, clear the text.
        setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Use the getCompoundDrawables()[2] expression to check
                // if the drawable is on the "end" of text [2].
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if ((getCompoundDrawablesRelative()[2] != null)) {
                        float clearButtonStart; // Used for LTR languages
                        float clearButtonEnd;  // Used for RTL languages
                        boolean isClearButtonClicked = false;
                        // Detect the touch in RTL or LTR layout direction.
                        if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                            // If RTL, get the end of the button on the left side.
                            clearButtonEnd = mClearButtonImage
                                    .getIntrinsicWidth() + getPaddingStart();
                            // If the touch occurred before the end of the button,
                            // set isClearButtonClicked to true.
                            if (event.getX() < clearButtonEnd) {
                                isClearButtonClicked = true;
                            }
                        } else {
                            // Layout is LTR.
                            // Get the start of the button on the right side.
                            clearButtonStart = (getWidth() - getPaddingEnd()
                                    - mClearButtonImage.getIntrinsicWidth());
                            // If the touch occurred after the start of the button,
                            // set isClearButtonClicked to true.
                            if (event.getX() > clearButtonStart) {
                                isClearButtonClicked = true;
                            }
                        }
                        // Check for actions if the button is tapped.
                        if (isClearButtonClicked) {
                            // Check for ACTION_DOWN (always occurs before ACTION_UP).
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                // Switch to the black version of clear button.
                                mClearButtonImage =
                                        ResourcesCompat.getDrawable(getResources(),
                                                R.drawable.ic_baseline_clear_24, null);
                                showClearButton();
                            }
                            // Check for ACTION_UP.
                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                // Switch to the opaque version of clear button.
                                mClearButtonImage =
                                        ResourcesCompat.getDrawable(getResources(),
                                                R.drawable.ic_baseline_clear_opaque_24, null);
                                // Clear the text and hide the clear button.
                                getText().clear();
                                hideClearButton();
                                return true;
                            }
                        } else {
                            return false;
                        }
                    }
                }
                return false;
            }
        });

        // If the text changes, show or hide the X (clear) button.
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing.
            }
        });
    }

    /**
     * Shows the clear (X) button.
     */

    private void showClearButton() {
        // Sets the Drawables (if any) to appear to the left of,
        // above, to the right of, and below the text.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds
                    (null,                      // Start of text.
                            null,               // Top of text.
                            mClearButtonImage,  // End of text.
                            null);              // Below text.
        }
    }

    /**
     * Hides the clear button.
     */
    private void hideClearButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds
                    (null,             // Start of text.
                            null,      // Top of text.
                            null,      // End of text.
                            null);     // Below text.
        }
    }
}