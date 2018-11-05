package com.shagalalab.sozlik.ui.favorites;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by QAREKEN on 4/19/2018.
 */
public class FavoritesPresenterTest {

    private FavoritesView favoritesViewMock;
    private SozlikDao sozlikDaoMock;
    private SozlikDbModel sozlikDbModelMock;
    private FavoritesPresenter favoritesPresenter;

    @Before
    public void setUp() {
        favoritesViewMock = mock(FavoritesView.class);
        sozlikDaoMock = mock(SozlikDao.class);
        sozlikDbModelMock = mock(SozlikDbModel.class);
        favoritesPresenter = new FavoritesPresenter(sozlikDaoMock);
        favoritesPresenter.setView(favoritesViewMock);
    }

    @Test
    public void whenFavoritesListIsNullDoNothing() {
        when(sozlikDaoMock.getAllFavorites()).thenReturn(null);

        favoritesPresenter.showFavoritesList();

        verifyNoMoreInteractions(favoritesViewMock);
    }

    @Test
    public void whenFavoritesListIsEmptyShowEmptyScreenMessage() {
        List<SozlikDbModel> list = new ArrayList<>();

        when(sozlikDaoMock.getAllFavorites()).thenReturn(list);

        favoritesPresenter.showFavoritesList();

        verify(favoritesViewMock, times(1)).hideList();
        verify(favoritesViewMock, times(1)).showEmptyScreen();
        verifyNoMoreInteractions(favoritesViewMock);
    }

    @Test
    public void whenFavoritesListIsNotEmptyShowList() {
        List<SozlikDbModel> list = new ArrayList<>();
        list.add(new SozlikDbModel());

        when(sozlikDaoMock.getAllFavorites()).thenReturn(list);

        favoritesPresenter.showFavoritesList();

        verify(favoritesViewMock, times(1)).hideEmptyScreen();
        verify(favoritesViewMock, times(1)).showList();
        verify(favoritesViewMock, times(1)).showFavorites(list);
        verifyNoMoreInteractions(favoritesViewMock);
    }

    @Test
    public void whenModelIsNotNullDeleteFavorite() {
        favoritesPresenter.deleteFavorite(sozlikDbModelMock);

        verify(sozlikDbModelMock, times(1)).setFavourite(false);
        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verifyNoMoreInteractions(sozlikDaoMock);
        verifyNoMoreInteractions(sozlikDbModelMock);
    }

    @Test
    public void whenModelIsNullDoNothing() {
        favoritesPresenter.deleteFavorite(null);

        verifyNoMoreInteractions(sozlikDbModelMock);
        verifyNoMoreInteractions(sozlikDaoMock);
    }
}