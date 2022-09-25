package com.tiodev.vegtummy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class RecipeActivity extends AppCompatActivity {

    ImageView img, backBtn, overlay, scroll, zoomImage;
    TextView txt, ing, time, steps;
    String [] ingList;
    Button stepBtn, ing_btn;
    boolean isImgCrop = false;
    ScrollView scrollView, scrollView_step;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Find views
        img = findViewById(R.id.recipe_img);
        txt = findViewById(R.id.tittle);
        ing = findViewById(R.id.ing);
        time = findViewById(R.id.time);
        stepBtn = findViewById(R.id.steps_btn);
        ing_btn = findViewById(R.id.ing_btn);
        backBtn = findViewById(R.id.back_btn);
        steps = findViewById(R.id.steps_txt);
        scrollView = findViewById(R.id.ing_scroll);
        scrollView_step = findViewById(R.id.steps);
        overlay = findViewById(R.id.image_gradient);
        scroll = findViewById(R.id.scroll);
        zoomImage = findViewById(R.id.zoom_image);

        // Load recipe image from link
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img"))
                                                    .into(img);
        // Set recipe title
        txt.setText(getIntent().getStringExtra("tittle"));

        // Set recipe ingredients
        ingList = getIntent().getStringExtra("ing").split("\n");
        // Set time
        time.setText(ingList[0]);


        for (int i = 1; i<ingList.length; i++){
            ing.setText(ing.getText()+"\uD83D\uDFE2  "+ingList[i]+"\n");
            /*if(ingList[i].startsWith(" ")){
                ing.setText(ing.getText()+"\uD83D\uDFE2  "+ingList[i].trim().replaceAll("\\s{2,}", " ")+"\n");
            }else{

            }*/

        }
        // Set recipe steps
        steps.setText(getIntent().getStringExtra("des"));
       // steps.setText(Html.fromHtml(getIntent().getStringExtra("des")));

        stepBtn.setBackground(null);

        stepBtn.setOnClickListener(v -> {
            stepBtn.setBackgroundResource(R.drawable.btn_ing);
            stepBtn.setTextColor(getColor(R.color.white));
            ing_btn.setBackground(null);
            ing_btn.setTextColor(getColor(R.color.black));


            scrollView.setVisibility(View.GONE);
            scrollView_step.setVisibility(View.VISIBLE);



//            ing.setText(getIntent().getStringExtra("des"));


        });

        ing_btn.setOnClickListener(v -> {
            ing_btn.setBackgroundResource(R.drawable.btn_ing);
            ing_btn.setTextColor(getColor(R.color.white));
            stepBtn.setBackground(null);
            stepBtn.setTextColor(getColor(R.color.black));

            scrollView.setVisibility(View.VISIBLE);
            scrollView_step.setVisibility(View.GONE);

        });


        // Full recipe image button
        zoomImage.setOnClickListener(view ->{

            if(!isImgCrop){
                img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                overlay.setImageAlpha(0);
                Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img"))
                        .into(img);
                isImgCrop = true;

            }else{
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                overlay.setImageAlpha(255);
                Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img"))
                        .into(img);
                isImgCrop = false;

            }

        });


        // Exit activity
        backBtn.setOnClickListener(v -> finish());



    }
}