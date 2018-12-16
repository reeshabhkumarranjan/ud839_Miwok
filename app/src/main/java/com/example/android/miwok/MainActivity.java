/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView colors,family,numbers,phrases;
        colors=(TextView)findViewById(R.id.colors);
        family=(TextView)findViewById(R.id.family);
        numbers=(TextView)findViewById(R.id.numbers);
        phrases=(TextView)findViewById(R.id.phrases);

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("title","Colors");
                intent.putExtra("list",WordList.getColorsList());
                intent.putExtra("tileColorResourceID",R.color.category_colors);
                startActivity(intent);
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("title","Family");
                intent.putExtra("list",WordList.getFamilyList());
                intent.putExtra("tileColorResourceID",R.color.category_family);
                startActivity(intent);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("title","Numbers");
                intent.putExtra("list",WordList.getNumbersList());
                intent.putExtra("tileColorResourceID",R.color.category_numbers);
                startActivity(intent);
            }
        });

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("title","Phrases");
                intent.putExtra("list",WordList.getPhrasesList());
                intent.putExtra("tileColorResourceID",R.color.category_phrases);
                startActivity(intent);
            }
        });
    }

//    public void openNumbersActivity(View view) {
//        Intent intent=new Intent(this,NumbersActivity.class);
//        startActivity(intent);
//    }
}
