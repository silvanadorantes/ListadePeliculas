package com.example.silvanadorantes.listadepeliculas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements PeliculaAdapter.OnItemClickListener,
      View.OnClickListener{

    private RecyclerView mRecyclerView;
    private PeliculaAdapter peliculaAdapter;
    private LinearLayoutManager linearLayoutManager;

    private TextInputLayout tilPelicula;
    private EditText etPelicula;

    private String mMovie;

    private Button mButtonGuardar;
    private Button mButtonCancelar;

    private Pelicula mPelicula;
    private PeliculaTable mPeliculaTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPelicula = new Pelicula();
        mPeliculaTable = new PeliculaTable(this);
        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        peliculaAdapter = new PeliculaAdapter(this, mPeliculaTable.getListaPeliculas(), this);
        mRecyclerView.setAdapter(peliculaAdapter);

        tilPelicula = (TextInputLayout) findViewById(R.id.til_nombre);
        etPelicula = (EditText) findViewById(R.id.et_nombre);



        mButtonCancelar = (Button) findViewById(R.id.button_cancelar);
        mButtonCancelar.setOnClickListener(this);

        mButtonGuardar = (Button) findViewById(R.id.button_guardar);
        mButtonGuardar.setOnClickListener(this);



    }

    @Override
    public void onClick(PeliculaAdapter.ViewHolder viewHolder, int idPelicula) {

        mPeliculaTable.deletepelicula(idPelicula);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        peliculaAdapter = new PeliculaAdapter(this, mPeliculaTable.getListaPeliculas(), this);
        mRecyclerView.setAdapter(peliculaAdapter);
       

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_guardar:
                mMovie = etPelicula.getText().toString();
                if (!mMovie.equals(" ")){
                    Log.d("Insert: ", "Insertando Pelicula..");
                    mPeliculaTable.insertPelicula(mMovie, "Activo");
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    peliculaAdapter = new PeliculaAdapter(this, mPeliculaTable.getListaPeliculas(), this);
                    mRecyclerView.setAdapter(peliculaAdapter);
                    Snackbar.make(view, "La pelicula a sido guardada en la lista", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    etPelicula.setText(" ");
                    etPelicula.requestFocus();
                } else {
                    etPelicula.requestFocus();
                    Snackbar.make(view, "Por favor introduzca la pelicula", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                break;

            case R.id.button_cancelar:
                etPelicula.setText(" ");
                etPelicula.requestFocus();
                break;
        }

    }


    //Datos Precargados en la base de datos
    private void initData(){
        Log.d("Insert: ", "Insertando Lista de Peliculas..");
        mPeliculaTable.insertPelicula("Iron Man 2", "Activo");
        mPeliculaTable.insertPelicula("Avengers", "Activo");
        mPeliculaTable.insertPelicula("Iron Man 3", "Activo");
        mPeliculaTable.insertPelicula("Thor  El Mundo Oscuro", "Activo");
        mPeliculaTable.insertPelicula("Capitan America y el Soldado del Invierno", "Activo");
        mPeliculaTable.insertPelicula("Guardianes de la Galaxia", "Activo");
        mPeliculaTable.insertPelicula("Avengers: La Era de Ultron", "Activo");
        mPeliculaTable.insertPelicula("Ant-Man", "Activo");
        mPeliculaTable.insertPelicula("Capitan America Civil War", "Activo");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }
}
