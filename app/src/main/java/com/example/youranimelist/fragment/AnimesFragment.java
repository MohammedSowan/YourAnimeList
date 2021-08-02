package com.example.youranimelist.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youranimelist.viewmodel.AnimesAdapter;
import com.example.youranimelist.viewmodel.AnimesViewModel;
import com.example.youranimelist.viewmodel.AnimesViewModelFactory;
import com.example.youranimelist.R;
import com.example.youranimelist.activity.ProfileActivity;
import com.example.youranimelist.databinding.FragmentAnimesBinding;
import com.example.youranimelist.firebase.FirebaseList;
import com.example.youranimelist.objects.Anime;

import java.util.List;

public class AnimesFragment extends Fragment {

    private FragmentAnimesBinding binder;
    private AnimesAdapter animesAdapter;
    private AnimesViewModel animesViewModel;
    public AnimesFragment(){
        super(R.layout.fragment_animes);
    }
    Button List1Btn;
    Button btnProfile;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        animesViewModel = new ViewModelProvider(this, new AnimesViewModelFactory(requireContext())).get(AnimesViewModel.class);
        animesViewModel.getAnimes().observe(getViewLifecycleOwner(), new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> animes) {
               animesAdapter.submitList(animes);
            }
        });
        binder=FragmentAnimesBinding.bind(view);
        binder.recyclerAnimes.setLayoutManager(new LinearLayoutManager(getActivity()));
        animesAdapter = new AnimesAdapter();
        binder.recyclerAnimes.setAdapter(animesAdapter);
        binder.fabAddAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Navigation.findNavController(view).navigate(AnimesFragmentDirections);
                Fragment fragment = new AddAnimeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMainContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        binder.List1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirebaseList.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        binder.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT| ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Anime anime=animesAdapter.getCurrentList().get(viewHolder.getAdapterPosition());
                animesViewModel.deleteAnime(anime);
            }
        }).attachToRecyclerView(binder.recyclerAnimes);

    }
}
