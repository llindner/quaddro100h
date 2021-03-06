package br.com.luizlindner.quaddro100h.lab08.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.luizlindner.quaddro100h.R;
import br.com.luizlindner.quaddro100h.lab01.app.controller.QuaddroListFragment;
import br.com.luizlindner.quaddro100h.lab08.domain.model.Hotel;

/**
 * Created by Luiz on 26/07/2017.
 */

public class HotelListFragment extends QuaddroListFragment {

    List<Hotel> lista;
    ArrayAdapter<Hotel> adapter;
    FragmentManager fm;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lista = new ArrayList<>();
        lista.add(new Hotel("New Beach Hotel", "Av. Boa Viagem", 4.5f));
        lista.add(new Hotel("Recife Hotel", "Av. Boa Viagem", 4.0f));
        lista.add(new Hotel("Canario Hotel", "Rua dos Navegantes", 3.0f));
        lista.add(new Hotel("Byanca Beach Hotel", "Rua Manguape", 4.0f));
        lista.add(new Hotel("Grand Hotel Dor", "Av. Bernardo", 3.5f));
        lista.add(new Hotel("Hotel Cool", "Av. Conselheiro Aguiar", 4.0f));
        lista.add(new Hotel("Hotel Infinito", "Rua Ribeiro de Brito", 5.0f));
        lista.add(new Hotel("Hotel Tulipa", "Av. Boa Viagem", 4.5f));


        adapter = new ArrayAdapter<Hotel>(getActivity(), android.R.layout.simple_list_item_1, lista);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        View frame = getActivity().findViewById(R.id.detalhe);
        Serializable model = (Serializable) l.getItemAtPosition(position);

        if(frame == null) {
            Intent i = new Intent(getActivity(), HotelActivity.class);
            i.putExtra("hotel", model);
            getActivity().startActivity(i);
        } else {
            HotelFragment hotelFragment;
            FragmentTransaction ft;

            hotelFragment = new HotelFragment();
            Bundle b = new Bundle();
            b.putSerializable("hotel", model);
            hotelFragment.setArguments(b);

            ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detalhe, hotelFragment, "LAB");

            ft.commit();
        }
    }
}
