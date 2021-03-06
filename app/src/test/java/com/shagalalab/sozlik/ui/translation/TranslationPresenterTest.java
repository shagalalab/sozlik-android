package com.shagalalab.sozlik.ui.translation;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

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
    private static final int SOME_RESOURCE = 1;
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
        translationPresenter = new TranslationPresenter(sozlikDaoMock);
        translationPresenter.setView(translationViewMock);
        when(sozlikDaoMock.getTranslationById(1)).thenReturn(sozlikDbModelMock);
        translationPresenter.setModelById(1);
    }

    @Test
    public void whenTranslationIsFoundShowIt() {
        when(sozlikDbModelMock.getWord()).thenReturn(SOME_WORD);
        when(sozlikDbModelMock.getTranslation()).thenReturn(SOME_TRANSLATION);
        when(sozlikDbModelMock.getToResource()).thenReturn(SOME_RESOURCE);
        when(sozlikDbModelMock.getFromResource()).thenReturn(SOME_RESOURCE);

        translationPresenter.getTranslation();

        verify(translationViewMock, times(1)).showWord(sozlikDbModelMock.getRawWord());
        verify(translationViewMock, times(1)).showTranslation(sozlikDbModelMock.getTranslation());
        verify(translationViewMock, times(1)).setToFlags(sozlikDbModelMock.getToResource());
        verify(translationViewMock, times(1)).setFromFlags(sozlikDbModelMock.getFromResource());
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenWordIsFavoritedToggleRemovesFromFavorites() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(true);

        translationPresenter.toggleFavorite();

        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verify(translationViewMock, times(1)).showFavorite(false);
        verify(sozlikDbModelMock, times(1)).setFavourite(false);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenWordIsNotFavoritedToggleAddsToFavorites() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(false);

        translationPresenter.toggleFavorite();

        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verify(translationViewMock, times(1)).showFavorite(true);
        verify(sozlikDbModelMock, times(1)).setFavourite(true);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenWordIsFavoritedReturnSetFavoriteStatusAsTrue() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(true);

        translationPresenter.setFavoriteStatus();

        verify(translationViewMock, times(1)).showFavorite(true);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenWordIsNotFavoritedReturnSetFavoriteStatusAsFalse() {
        when(sozlikDbModelMock.isFavourite()).thenReturn(false);

        translationPresenter.setFavoriteStatus();

        verify(translationViewMock, times(1)).showFavorite(false);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenShareTranslationShowShareMessage() {
        when(sozlikDbModelMock.getWord()).thenReturn(SOME_WORD);
        when(sozlikDbModelMock.getMessageForShare()).thenReturn(SOME_TRANSLATION);

        translationPresenter.shareTranslation();

        verify(translationViewMock, times(1)).goToShare(SOME_WORD, SOME_TRANSLATION);
        verifyNoMoreInteractions(translationViewMock);
    }

    @Test
    public void whenTranslationIsOpenedSetLastAccessTime() {
        translationPresenter.setLastAccessed(TIME);

        verify(sozlikDbModelMock, times(1)).setLastAccessed(TIME);
        verify(sozlikDaoMock, times(1)).update(sozlikDbModelMock);
        verifyNoMoreInteractions(sozlikDbModelMock);
    }

}