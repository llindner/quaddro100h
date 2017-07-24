package br.com.luizlindner.quaddro100h.lab04.app.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.luizlindner.quaddro100h.R;
import br.com.luizlindner.quaddro100h.databinding.EnderecoAlterarViewBinding;
import br.com.luizlindner.quaddro100h.lab01.app.controller.QuaddroActivity;
import br.com.luizlindner.quaddro100h.lab04.app.binding.EnderecoAction;
import br.com.luizlindner.quaddro100h.lab04.domain.model.Endereco;
import br.com.luizlindner.quaddro100h.lab04.domain.model.UF;

/**
 * Created by Luiz on 06/07/2017.
 */

public class EnderecoAlterarActivity extends QuaddroActivity {

    EnderecoAlterarViewBinding binding;

    BroadcastReceiver consultaCEP = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TIPO_DE_LOG, "Entrou no broadcast receiver");
            Endereco model = (Endereco) intent.getSerializableExtra("model");
            if(model != null){
                binding.setEndereco(model);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.endereco_alterar_view);
        binding.setController(new EnderecoAction(binding));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = new MenuInflater(getApplicationContext());
        i.inflate(R.menu.endereco_salvar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.endereco_salvar:
                // TODO Salvar alteração no SQLite
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.uf.setAdapter(new ArrayAdapter<UF>(this, android.R.layout.simple_list_item_1, UF.values()));
        registerReceiver(consultaCEP, new IntentFilter("retornoCEP"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(consultaCEP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.i(TIPO_DE_LOG, "Alteração de endereço cancelada!");
        Toast.makeText(this, "Alteração cancelada!", Toast.LENGTH_LONG).show();
    }

}
