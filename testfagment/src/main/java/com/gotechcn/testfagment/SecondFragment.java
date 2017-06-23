package com.gotechcn.testfagment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pengzhimao on 2017/6/23.
 */

public class SecondFragment extends Fragment
{

    private static final String TAG = "FragmentLife";

//    @Override
//    public void onAttach(Context context)
//    {
//        Log.e(TAG,"onAttach(context)");
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onAttach(Activity activity)
//    {
//        Log.e(TAG,"onAttach(activity)");
//        super.onAttach(activity);
//    }
//
//    @Override
//    public void onCreate( Bundle savedInstanceState)
//    {
//        Log.e(TAG,"onCreate");
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState)
    {
        Log.e(TAG,"onCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


//    @Override
//    public void onActivityCreated( Bundle savedInstanceState)
//    {
//        Log.e(TAG,"onActivityCreated");
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onStart()
//    {
//        Log.e(TAG,"onStart");
//        super.onStart();
//    }
//
//    @Override
//    public void onResume()
//    {
//        Log.e(TAG,"onResume");
//
//        super.onResume();
//    }
//
//    @Override
//    public void onPause()
//    {
//        Log.e(TAG,"onPause");
//        super.onPause();
//    }
//
//    @Override
//    public void onStop()
//    {
//        Log.e(TAG,"onStop");
//        super.onStop();
//    }
//
//
//    @Override
//    public void onDestroyView()
//    {
//        Log.e(TAG,"onDestroyView");
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy()
//    {
//        Log.e(TAG,"onDestroy");
//        super.onDestroy();
//    }
//
//    @Override
//    public void onDetach()
//    {
//        Log.e(TAG,"onDetach");
//        super.onDetach();
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden)
//    {
//        Log.e(TAG,"onHiddenChanged");
//        super.onHiddenChanged(hidden);
//    }
//
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState)
//    {
//        Log.e(TAG,"onViewCreated");
//        super.onViewCreated(view, savedInstanceState);
//
//    }

}
