package com.example.appbanlinhkien.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appbanlinhkien.R;
import com.example.appbanlinhkien.Utils.Utils;
import com.example.appbanlinhkien.adapter.CatergoryAdapter;
import com.example.appbanlinhkien.model.Category;
import com.example.appbanlinhkien.retrofit.ApiLinhKien;
import com.example.appbanlinhkien.retrofit.RetroClient;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMHC;
    NavigationView navigationView;
    ListView listViewMHC;
    DrawerLayout drawerLayout;
    CatergoryAdapter categoryAdapter;
    List<Category> listCategory;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reflect();
        ActionBar();
        
        if(isConnected(this)){
            Toast.makeText(getApplicationContext(), "Connected!",Toast.LENGTH_LONG).show();
            ActionViewFlipper();
        }else{
            Toast.makeText(getApplicationContext(), "No interet!",Toast.LENGTH_LONG).show();
        }
    }

    private void ActionViewFlipper() {
        List<String> list = new ArrayList<>();
        list.add("https://cdn.discordapp.com/attachments/1131867639665406012/1131872096235049060/image.png");
        list.add("https://cdn.discordapp.com/attachments/1131867639665406012/1131872129814638642/image.png");
        list.add("https://cdn.discordapp.com/attachments/1131867639665406012/1131867768707354624/image.png");
        for (int i = 0; i<list.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(list.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Reflect() {
        toolbar = findViewById(R.id.toolbarmhc);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewMHC = findViewById(R.id.recyclerview);
        listViewMHC = findViewById(R.id.listviewmhc);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerlayout);
        //List
        listCategory = new ArrayList<>();
        //Adapter
        categoryAdapter = new CatergoryAdapter(getApplicationContext(), listCategory);
        listViewMHC.setAdapter(categoryAdapter);
    }
    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
                return true;
            }
        } else {
            // not connected to the internet
            return false;
        }
        return false;
    }
}