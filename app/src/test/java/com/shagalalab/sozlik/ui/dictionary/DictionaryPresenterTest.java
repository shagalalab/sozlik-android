package com.shagalalab.sozlik.ui.dictionary;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.ui.dictionary.spellchecker.SpellChecker;
import com.shagalalab.sozlik.helper.PackageHelper;
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
 * Created by QAREKEN on 4/12/2018.
 */
public class DictionaryPresenterTest {
    private static final String SOME_WORD = "someword";
    private static final String BELOW_MIN_WIDTH_WORD = "aa";

    private DictionaryView dictionaryViewMock;
    private SozlikDao sozlikDaoMock;
    private PackageHelper packageHelperMock;
    private SpellChecker spellCheckerMock;
    private DictionaryPresenter dictionaryPresenter;

    @Before
    public void setUp() {
        dictionaryViewMock = mock(DictionaryView.class);
        sozlikDaoMock = mock(SozlikDao.class);
        packageHelperMock = mock(PackageHelper.class);
        spellCheckerMock = mock(SpellChecker.class);
        dictionaryPresenter = new DictionaryPresenter(sozlikDaoMock, packageHelperMock, spellCheckerMock);
        dictionaryPresenter.setView(dictionaryViewMock);
    }

    @Test
    public void whenSearchWordIsEmptyDoNothing() {
        dictionaryPresenter.search("");
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void whenSearchWordFoundShowTranslation() {
        SozlikDbModel result = new SozlikDbModel();
        int id = 1;
        result.setId(id);
        when(sozlikDaoMock.getTranslation(SOME_WORD)).thenReturn(result);
        dictionaryPresenter.search(SOME_WORD);
        verify(dictionaryViewMock, times(1)).showTranslation(id);
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void whenSearchWordNotFoundButSuggestionsFoundShowSuggestions() {
        List<SozlikDbModel> list = new ArrayList<>();
        list.add(new SozlikDbModel());
        when(sozlikDaoMock.getTranslation(SOME_WORD)).thenReturn(null);
        when(spellCheckerMock.check(SOME_WORD, true)).thenReturn(list);
        dictionaryPresenter.search(SOME_WORD);
        verify(dictionaryViewMock, times(1)).showMessage(R.string.suggestion_found);
        verify(dictionaryViewMock, times(1)).setMessageVisible();
        verify(dictionaryViewMock, times(1)).showResults(list);
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void whenSearchWordAndSuggestionsNotFoundShowNoResults() {
        List<SozlikDbModel> list = new ArrayList<>();
        when(sozlikDaoMock.getTranslation(SOME_WORD)).thenReturn(null);
        when(sozlikDaoMock.getSuggestions('%' + SOME_WORD + '%')).thenReturn(list);
        dictionaryPresenter.search(SOME_WORD);
        verify(dictionaryViewMock, times(1)).showMessage(R.string.suggestion_not_found);
        verify(dictionaryViewMock, times(1)).setMessageVisible();
        verify(dictionaryViewMock, times(1)).showResults(list);
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void whenSearchWordNotFoundAndLessThanMinimalShowNoResults() {
        dictionaryPresenter.search(BELOW_MIN_WIDTH_WORD);
        verify(dictionaryViewMock, times(1)).showMessage(R.string.suggestion_not_found);
        verify(dictionaryViewMock, times(1)).setMessageVisible();
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void hideKeyboardLinkIfAppInstalled() {
        when(packageHelperMock.isAppInstalled()).thenReturn(true);
        dictionaryPresenter.setKeyboardMessageVisibility();
        verify(dictionaryViewMock, times(1)).hideKeyboardMessage();
        verifyNoMoreInteractions(dictionaryViewMock);
    }

    @Test
    public void showKeyboardLinkIfAppNotInstalled() {
        when(packageHelperMock.isAppInstalled()).thenReturn(false);
        dictionaryPresenter.setKeyboardMessageVisibility();
        verify(dictionaryViewMock, times(1)).showKeyboardMessage();
        verifyNoMoreInteractions(dictionaryViewMock);
    }
}