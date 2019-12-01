package com.github.redouane64.debt.internals

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentHelper(private val fragmentManager: FragmentManager) {

    private var current : Fragment? = null;

    fun show(fragment: Fragment, hostId: Int) {

        if(current != null) {
            fragmentManager.beginTransaction()
                .remove(current!!)
                .commit();
        }

        fragmentManager.beginTransaction()
            .add(hostId, fragment)
            .commit();

        this.current = fragment;
    }

}