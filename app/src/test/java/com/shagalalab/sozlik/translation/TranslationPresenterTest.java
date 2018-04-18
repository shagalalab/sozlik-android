package com.shagalalab.sozlik.translation;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by QAREKEN on 4/18/2018.
 */
public class TranslationPresenterTest {
    private static final String SOME_WORD = "someword";
    private static final String SOME_TRANSLATION = "translation";
    private static final long TIME = 1000000000;

    private TranslationView translationViewMock;
    private SozlikDao sozlikDaoMock;
    private SozlikDbModel sozlikDbModelMock;
    private TranslationPresenter translationPresenter;

    @Before
    public void setUp() {
        translationViewMock = mock(TranslationView.class);
        sozlikDaoMock = mock(SozlikDao.class);
        sozlikDbModelMock = mock(SozlikDbModel.class);
        translationPresenter = new TranslationPresenter(translationViewMock, sozlikDaoMock, sozlikDbModelMock);
    }

    @Test
    public void testGetTranslation() {
        when(sozlikDbModelMock.getWord()).thenReturn(SOME_WORD);
        when(sozlikDbModelMock.getTranslation()).thenReturn(SOME_TRANSLATION);
        translationPresenter.getTranslation();
        verify(translationViewMock, times(1)).showWord(SOME_WORD);
        verify(translationViewMock, times(1)).showTranslation(SOME_TRANSLATION);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testToggleFavoriteTrue() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(true);
        translationPresenter.toggleFavorite();
        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verify(translationViewMock, times(1)).showFavorite(false);
        verify(sozlikDbModelMock, times(1)).setFavourite(false);
        verifyNoMoreInteractions(sozlikDaoMock);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testToggleFavoriteFalse() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(false);
        translationPresenter.toggleFavorite();
        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verify(translationViewMock, times(1)).showFavorite(true);
        verify(sozlikDbModelMock, times(1)).setFavourite(true);
        verifyNoMoreInteractions(sozlikDaoMock);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testSetFavoriteStatusTrue() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(true);
        translationPresenter.setFavoriteStatus();
        verify(translationViewMock, times(1)).showFavorite(true);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testSetFavoriteStatusFalse() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(false);
        translationPresenter.setFavoriteStatus();
        verify(translationViewMock, times(1)).showFavorite(false);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testShareTranslation() {
        when(sozlikDbModelMock.getWord()).thenReturn(SOME_WORD);
        when(sozlikDbModelMock.getMessageForShare()).thenReturn(SOME_TRANSLATION);
        translationPresenter.shareTranslation();
        verify(translationViewMock, times(1)).goToShare(SOME_WORD, SOME_TRANSLATION);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void testSetLastAccessed() {
        translationPresenter.setLastAccessed(TIME);
        verify(sozlikDbModelMock, times(1)).setLastAccessed(TIME);
        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verifyNoMoreInteractions(sozlikDaoMock);
        verifyNoMoreInteractions(sozlikDbModelMock);
    }

}