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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * AppWidgetSample demonstrates app widgets, including:
 * - Creating a template app widget to an app.
 * - Updating the app widget periodically (every 30 minutes).
 * - Adding a button to the app widget that updates on demand.
 * <p>
 * MainActivity is unused and includes a message to add the app widget to
 * the user's home screen.
 */
public class MainActivity extends AppCompatActivity {

    ExemploNewView env;
    ExemploCustomView txt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (ExemploCustomView)findViewById(R.id.exemploCustomView);
        env = (ExemploNewView)findViewById(R.id.exemploNewView);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
              public void onClick(View v) {
                int vlr = env.getValor();
                String msg = new StringBuilder().append("Texto: ").append(txt.getText()).append(",  Opção selecionada: ").append(vlr).toString();
                Toast t  = Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }


}
