package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY


class GreetingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_greeting, container, false)
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = APP_ACTIVITY.getString(R.string.default_title)
    }

}