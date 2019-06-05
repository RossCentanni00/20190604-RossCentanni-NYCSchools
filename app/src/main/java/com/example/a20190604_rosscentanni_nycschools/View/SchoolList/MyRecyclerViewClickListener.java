package com.example.a20190604_rosscentanni_nycschools.View.SchoolList;

/**
 * Name: MyRecyclerViewClickListener
 * Purpose: Acts as a communication interface between the SchoolListFragment and its RecyclerView
 */
interface MyRecyclerViewClickListener {
    void onItemSelected(String dbKey, String schoolName);
}
