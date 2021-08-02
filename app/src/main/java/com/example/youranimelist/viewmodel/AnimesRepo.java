package com.example.youranimelist.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.youranimelist.objects.Anime;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AnimesRepo {

    private AnimesDao animesDao;

    public AnimesRepo(AnimesDao animesDao) {
        this.animesDao = animesDao;
    }

    public LiveData<List<Anime>> getAnimes(){
        return animesDao.getAnimes();
    }


    public void deleteAnime(@NotNull Anime anime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                animesDao.deleteAnime(anime);
            }
        }).start();

    }
}
