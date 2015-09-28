package com.example.kb50group6.userapplication;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OfficeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OfficeFragment extends Fragment implements AdapterView.OnItemClickListener {


    String URL = "content://com.example.user.contentprovider.AS3DB/offices";

    Uri offices = Uri.parse(URL);
    SimpleCursorAdapter adapter;
    Cursor c;
    ListView listView;
    private OnFragmentInteractionListener mListener;

    public OfficeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_office, container, false);
        // Inflate the layout for this fragment

        CursorLoader cursorLoader = new CursorLoader(
                getActivity(),
                offices,
                null,
                null,
                null,
                "o_city"
        );
        c = cursorLoader.loadInBackground();

        int[] views = new int[]{R.id.office_city};
        String[] columns = new String[]{
                "o_city"
        };


        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.fragment_office,
                c,
                columns,
                views,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

       // c.moveToFirst();
        //TextView textView = (TextView)rootView.findViewById(R.id.contactName);
        //textView.setText(c.getString(c.getColumnIndex("o_city")));


        listView = (ListView)rootView.findViewById(R.id.listView);

        try{
            listView.setOnItemClickListener(this);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {

        }


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        c.moveToPosition(position);


        Toast.makeText(getActivity(),"test"+ c.getString(0),Toast.LENGTH_SHORT).show();
        String pos= Integer.toString(position);
        //pos.valueOf(position);
        Intent intent = new Intent(getActivity(),OfficeActivity.class);
        intent.putExtra("position",pos);
        startActivity(intent);


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
