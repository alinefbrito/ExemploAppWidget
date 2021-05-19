package com.example.android.appwidgetsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


public class ExemploNewView extends View  {
    //define as variáveis necessárias para o elemento ( controle de discagem)


    private static int SELECTION_COUNT = 4; // Contador de Seleções disponíveis
    private float mWidth;                   // largura.
    private float mHeight;                  // altura
    private Paint mTextPaint;               // desenhar elemento de texto
    private Paint mDialPaint;               // desenhar elemento de discador
    private float mRadius;                  // Raio do circulo
    private int mActiveSelection;           //Seleção atual
    // String buffer para os labels e valores
    private final StringBuffer mTempLabel = new StringBuffer(8);
    private final float[] mTempResult = new float[2];
    private int onColor;
    private int offColor;


    //alterada classe jetbrais para utilizar a annotation default do Android
    // Erro por falha da ide
    public ExemploNewView(Context context) {
        super(context);
        init(context, null);
    }

    public ExemploNewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExemploNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExemploNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        //Customização :define as cores on / off padrão
        onColor = Color.CYAN;
        offColor = Color.GRAY;
        //customiza as cores conforme escolha do usuário
        if (attrs!= null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.NewView,
                    0, 0);
            // sete os valores a partitr dos atributos
            onColor = typedArray.getColor(R.styleable.NewView_onColor,
                    onColor);
            offColor = typedArray.getColor(R.styleable.NewView_offColor,
                    offColor);
            typedArray.recycle();
        }

        //habilita o anti alias (alias -> serrilhado)
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //define a cor
        mTextPaint.setColor(Color.BLACK);
        //estilo de preenchimento
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //alinhamento
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //tamanho do texto
        mTextPaint.setTextSize(40f);

        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(offColor);
        // Inicializa a Seleção
        mActiveSelection = 0;



        //após a criação do layout implementar o listener
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // rotaciona para próxima posição válida
                mActiveSelection = (mActiveSelection + 1) % SELECTION_COUNT;
                // muda a cor do bckg quando o valor selecionado for maior que 1
                if (mActiveSelection >= 1) {
                    mDialPaint.setColor(onColor);
                } else {
                    mDialPaint.setColor(offColor);
                }
                // O método invalidade redesenha a view
                //ele invalida a view e força a chamada do onDraw()
                invalidate();
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Calcula o raio a partir da altura/largura
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
    }

    private float[] computeXYForPosition(final int pos, final float radius) {
        //recebe o rsultado temporário
        float[] result = mTempResult;
        //calcula os angulos em radianos
        Double startAngle = Math.PI * (9 / 8d);
        Double angle = startAngle + (pos * (Math.PI / 4));
        //caltula aos valores de posição ( x e y)
        result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Desenha o circulo (discador)
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mDialPaint);
        // Desenha os labels
        //posição  dinâmica
        final float labelRadius = mRadius + 20;
        StringBuffer label = mTempLabel;
        for (int i = 0; i < SELECTION_COUNT; i++) {
            //recupera as posições calculadas
            float[] xyData = computeXYForPosition(i, labelRadius);
            float x = xyData[0];
            float y = xyData[1];
            label.setLength(0);
            label.append(i);
            canvas.drawText(label, 0, label.length(), x, y, mTextPaint);
        }
        // desenha o indicador de marca
        final float markerRadius = mRadius - 35;
        //define o item selecionado
        float[] xyData = computeXYForPosition(mActiveSelection,
                markerRadius);
        float x = xyData[0];
        float y = xyData[1];
        //efetua o desenha inicial
        canvas.drawCircle(x, y, 20, mTextPaint);
    }

    //define método para devolver um valor
    public int getValor(){
        return this.mActiveSelection;
    }


}
