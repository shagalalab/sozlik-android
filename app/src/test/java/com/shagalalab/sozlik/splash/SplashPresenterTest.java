package com.shagalalab.sozlik.splash;

import com.shagalalab.sozlik.dictionary.autocomplete.WordHolder;
import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

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
public class SplashPresenterTest {

    private SplashView splashViewMock;
    private GsonHelper gsonHelperMock;
    private SharedPrefsHelper prefsManagerMock;
    private SozlikDao sozlikDaoMock;
    private AppExecutors appExecutorsMock;
    private WordHolder wordHolderMock;
    private SplashPresenter splashPresenter;

    @Before
    public void setUp() {
        splashViewMock = mock(SplashView.class);
        gsonHelperMock = mock(GsonHelper.class);
        prefsManagerMock = mock(SharedPrefsHelper.class);
        sozlikDaoMock = mock(SozlikDao.class);
        appExecutorsMock = mock(AppExecutors.class);
        wordHolderMock = mock(WordHolder.class);
        splashPresenter = new SplashPresenter(splashViewMock, gsonHelperMock, prefsManagerMock, sozlikDaoMock, appExecutorsMock, wordHolderMock);
    }

    @Test
    public void testLoaderFirstLaunch() {
        List<SozlikDbModel> list = new ArrayList<>();
        list.add(new SozlikDbModel());
        when(prefsManagerMock.isFirstLaunch()).thenReturn(true);
        when(sozlikDaoMock.getAllWords()).thenReturn(list);
        when(gsonHelperMock.getListFromLocalAssets()).thenReturn(list);
        splashPresenter.initSozlikData();
        verify(sozlikDaoMock, times(1)).insertToDB(list);
        verify(prefsManagerMock, times(1)).setFirstLaunch();
        verify(sozlikDaoMock, times(1)).getAllWords();
        verify(wordHolderMock, times(1)).setWordList(list);
        verify(wordHolderMock, times(1)).setWordMap(list);
        verifyNoMoreInteractions(sozlikDaoMock);
    }

    @Test
    public void testLoaderNotFirstLaunch() {
        List<SozlikDbModel> list = new ArrayList<>();
        list.add(new SozlikDbModel());
        when(prefsManagerMock.isFirstLaunch()).thenReturn(false);
        when(sozlikDaoMock.getAllWords()).thenReturn(list);
        splashPresenter.initSozlikData();
        verify(sozlikDaoMock, times(1)).getAllWords();
        verify(wordHolderMock, times(1)).setWordList(list);
        verify(wordHolderMock, times(1)).setWordMap(list);
        verifyNoMoreInteractions(sozlikDaoMock);
    }
}