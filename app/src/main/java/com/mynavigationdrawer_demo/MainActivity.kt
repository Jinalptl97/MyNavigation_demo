package com.mynavigationdrawer_demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_home_screen.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

   // var list: RecyclerView? = null
    var adapter: U_NavigationAdapter? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    var headertext: TextView? = null
    var array_menu = ArrayList<String>()
    var mDrawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            // mDrawerToggle = new ActionBarDrawerToggle(ServiceProvider_home.this, mDrawerLayout, R.string.openDrawer, R.string.openDrawer) {
            override fun onDrawerClosed(drawerView: View) {

                super.onDrawerClosed(drawerView)
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }

        mDrawerLayout?.setDrawerListener(mDrawerToggle)
        (mDrawerToggle as ActionBarDrawerToggle).setDrawerIndicatorEnabled(true)

        adapter = U_NavigationAdapter()

        list!!.setAdapter(adapter)
        list!!.setLayoutManager(LinearLayoutManager(this))
        list!!.setHasFixedSize(true)

        notify_bar?.setOnClickListener {
            mDrawerLayout?.openDrawer(GravityCompat.START)
        }

    }


    inner class U_NavigationAdapter : RecyclerView.Adapter<U_NavigationHolder>() {

        init {
            array_menu.add("Dashboard")
            array_menu.add("Earning")
            array_menu.add("Rating")
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): U_NavigationHolder {

            return U_NavigationHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_navigation_layout, null))

        }

        override fun getItemCount(): Int {
            return array_menu.size

        }

        override fun onBindViewHolder(holder: U_NavigationHolder, position: Int) {
            holder.txt_row!!.setText(array_menu.get(position))

        }


    }

    class U_NavigationHolder(var v: View) : RecyclerView.ViewHolder(v) {

        var txt_row: TextView? = null


        init {
            txt_row = v.findViewById(R.id.txt_row) as TextView
        }
    }


}
