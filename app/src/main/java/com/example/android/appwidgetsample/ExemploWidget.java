/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.appwidgetsample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;
/*
Componentes Widget
--Provider-info XML file defines metadata
-- arquivo de Layout XML para UI
-- AppWidgetProvider (codigo Java)
-- Activity de (configuração opcional)


*/


public class ExemploWidget extends AppWidgetProvider {

    // Name of shared preferences file & key
    private static final String SHARED_PREFERENCES_FILE =
            "com.example.android.appwidgetexemplo";
    private static final String COUNT_KEY = "contador";


    private void updateWidget(Context context,
                              AppWidgetManager appWidgetManager,
                              int appWidgetId) {

        // Retorna o contador.
        SharedPreferences preferencias =
                context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
        int count = preferencias.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        // Obtem a data atual
        String dateString =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

        // Define o objeto RemoteView
        /*
        Uma classe que descreve uma hierarquia de visualização que pode ser exibida em outro processo.
        A hierarquia é inflada a partir de um arquivo de recurso de layout, e esta classe fornece algumas
        operações básicas para modificar o conteúdo da hierarquia inflada.
         */
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_id, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.appwidget_update,  context.getResources().getString(
                    R.string.date_count_format, count, dateString));

        // Salva a contagem em shared preferences
        SharedPreferences.Editor preferenciasEditor = preferencias.edit();
        preferenciasEditor.putInt(COUNT_KEY + appWidgetId, count);
        preferenciasEditor.apply();

        // configura 0 botão de update
        Intent intentUpdate = new Intent(context, ExemploWidget.class);

        // A ação da intent deve ser um widget update - intent Filter deve estar definido no manifesto
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Inclui o id do widget para ser atualizado como extra da intent
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);


        // Envolve tudo em uma pending intent para enviar um brodcast.
        // Use o ID do widget do aplicativo como o código de solicitação (terceiro argumento) para que
        // cada intent seja único.
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        // Associa a pending intent ao clique do botão
        views.setOnClickPendingIntent(R.id.button_update, pendingUpdate);

        // atualiza o widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * Override for onUpdate() method, to handle all widget update requests.
     *
     * @param context          The application context.
     * @param appWidgetManager The app widget manager.
     * @param appWidgetIds     An array of the app widget IDs.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // Atualiza todos os widgetes pendentes
        for (int appWidgetId : appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

