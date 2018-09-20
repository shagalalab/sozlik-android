package com.shagalalab.sozlik.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.SozlikApp;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;
import com.shagalalab.sozlik.ui.translation.TranslationActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements FavoritesView, FavoritesViewHolder.FavoriteAdapterCallback {
    public static final String TAG = FavoritesFragment.class.getName();
    private FavoritesAdapter adapter;

    @BindView(R.id.recycler_favorites) RecyclerView recyclerView;
    @BindView(R.id.empty_favorites) TextView emptyText;

    @Inject FavoritesPresenter presenter;
    @Inject SozlikDao sozlikDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SozlikApp) getActivity().getApplication()).getComponent().inject(this);

        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);

        adapter = new FavoritesAdapter(sozlikDao.getAllFavorites(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
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
