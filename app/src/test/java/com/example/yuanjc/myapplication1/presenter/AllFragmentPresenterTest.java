package com.example.yuanjc.myapplication1.presenter;

import android.content.Context;

import com.example.yuanjc.myapplication1.bean.Fund;
import com.example.yuanjc.myapplication1.view.mainFragment.AllFragmentContract;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yuanjc on 2016/8/3.
 */
public class AllFragmentPresenterTest {
    @Mock
    public AllFragmentContract.IAllFragmentView iView;
    @Mock
    public AllFragmentContract.IAllFragmentModel iModel;
    @Mock
    public ArrayList<Fund> funds;
    public AllFragmentPresenter presenter;
    @Before
    public void setUp() throws Exception {
//        Context context=mock(Context.class);
        MockitoAnnotations.initMocks(this);
        when(iModel.getFunds()).thenReturn(funds);
//        presenter=new AllFragmentPresenter(iView,context);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetData() throws Exception {
        List<String> list=mock(List.class);
        when(list.get(0)).thenReturn("nihao");
        String result=list.get(0);
        verify(list).get(0);
        Assert.assertEquals(result,list.get(0));
    }

    @Test
    public void testUpdateData() throws Exception {

    }

    @Test
    public void testShowSelctTypeData() throws Exception {

    }

    @Test
    public void testChangeSelectTypeData() throws Exception {

    }

    @Test
    public void testGoTo() throws Exception {

    }
}