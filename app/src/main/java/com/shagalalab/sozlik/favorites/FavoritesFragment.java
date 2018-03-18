package com.shagalalab.sozlik.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;
import com.shagalalab.sozlik.translation.TranslationActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements FavoritesView, FavoritesViewHolder.FavoriteAdapterCallback {
    public static final String TAG = FavoritesFragment.class.getName();

    private FavoritesPresenter presenter;
    private FavoritesAdapter adapter;
    private SozlikDao sozlikDao;
    private RecyclerView recyclerView;
    private TextView emptyText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sozlikDao = SozlikDatabase.getSozlikDatabase(getActivity().getApplicationContext()).sozlikDao();
        presenter = new FavoritesPresenter(this, sozlikDao);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        adapter = new FavoritesAdapter(sozlikDao.getAllFavorites(), this);

        recyclerView = view.findViewById(R.id.recycler_favorites);
        emptyText = view.findViewById(R.id.empty_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        presenter.showFavoritesList();
        super.onResume();
    }

    @Override
    public void showFavorites(List<SozlikDbModel> list) {
        adapter.setData(list);
    }

    @Override
    public void showEmptyScreen() {
        emptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyScreen() {
        emptyText.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onFavoriteItemClicked(int translationId) {
        Intent intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra(TranslationActivity.TRANSLATION_ID, translationId);
        startActivity(intent);
    }

    @Override
    public void onFavoriteIconClicked(SozlikDbModel model) {
        presenter.deleteFavorite(model);
        presenter.showFavoritesList();
    }
}
