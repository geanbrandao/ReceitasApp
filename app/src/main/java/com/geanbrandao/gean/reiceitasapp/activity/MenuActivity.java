package com.geanbrandao.gean.reiceitasapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.adapter.ReceitasAdapter;
import com.geanbrandao.gean.reiceitasapp.conexao.Yummly;
import com.geanbrandao.gean.reiceitasapp.helper.MelhoraImagem;
import com.geanbrandao.gean.reiceitasapp.json.Recipe;
import com.geanbrandao.gean.reiceitasapp.json.ResultadoFeed;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, ReceitasAdapter.ReceitaAdapaterListener {

    private List<Recipe> recipes = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ReceitasAdapter mAdapater;
    private CircleImageView ivFotoPerfil;
    private TextView mNome, mEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        ivFotoPerfil = view.findViewById(R.id.iv_perfil_foto);
        mEmail = view.findViewById(R.id.tv_perfil_email);
        mNome = view.findViewById(R.id.tv_perfil_nome);

        // firebase
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recycler_view_receitas);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        Yummly y = new Yummly(getResources().getString(R.string.appId),
                getResources().getString(R.string.appKey));
        ResultadoFeed result;

        Log.i("RetornoApi", "antes do try ");
        try {
            result = y.search();
            //Log.i("RetornoApi", "entrou no try "+result.getMatches().size());
            for (Recipe recipe : result.getMatches()) {
                String url = recipe.getSmallImageUrls().get(0);
                recipe.getSmallImageUrls().set(0, MelhoraImagem.alteraUrl(url));
                recipes.add(recipe);
                //Log.i("RetornoApi",recipe.getRecipeName()+" - "+recipe.getId()+" - "+recipe.getSourceDisplayName());
            }

        } catch (Exception e) {
            Log.i("RetornoApi", "Erro "+e);
            e.printStackTrace();
        }



        mAdapater = new ReceitasAdapter(recipes, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapater);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getReceitas();
            }
        });

        mNome.setText(mAuth.getCurrentUser().getDisplayName());
        mEmail.setText(mAuth.getCurrentUser().getEmail());
        Glide.with(this)
                .load(mAuth.getCurrentUser().getPhotoUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivFotoPerfil);

    }

    private void getReceitas() {
//        swipeRefreshLayout.setRefreshing(true);
//        swipeRefreshLayout.setRefreshing(false);

        // implementa as progressBar para avisar o usuario enquanto faz a task de rede
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
//            case R.id.menu_favoritos:
//                startActivity(new Intent(this, FavoritosActivity.class));
//                break;
            case R.id.menu_sair:
                mAuth.signOut();
                onResume();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onImageClicked(int position) {
        Log.i("RespostaRecycler", " = "+ recipes.get(position).getRecipeName());
        Intent i = new Intent(this, DetalhesActivity.class);
        i.putExtra("smallImageUrls", MelhoraImagem.alteraUrl(recipes.get(position).getSmallImageUrls().get(0)));
        //i.putExtra("sourceDisplayName", recipes.get(position).getSourceDisplayName());
        //i.putExtra("sourceDisplayName", recipes.get(position).getSourceDisplayName());
        i.putExtra("quantidadeIngredientes", recipes.get(position).getIngredients().size());
        int aux = 0;
        for (String s: recipes.get(position).getIngredients()) {
            i.putExtra("ing"+aux, s); // adiciona um ingrediente
            ++aux;
        }
        i.putExtra("id", recipes.get(position).getId());
        //i.putExtra("recipeName", recipes.get(position).getRecipeName());
        //i.putExtra("totalTimeInSeconds", recipes.get(position).getTotalTimeInSeconds());
        i.putExtra("rating", recipes.get(position).getRating());
        startActivity(i);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
