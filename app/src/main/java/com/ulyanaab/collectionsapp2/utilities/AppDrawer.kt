package com.ulyanaab.collectionsapp2.utilities

import android.view.View
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.controllers.NinjaTurtlesController
import com.ulyanaab.collectionsapp2.controllers.ScoobyDooCollectionController
import com.ulyanaab.collectionsapp2.controllers.SpiderManCollectionController
import com.ulyanaab.collectionsapp2.controllers.SuperCarsCollectionController

class AppDrawer {

    private lateinit var drawer: Drawer
    private var drawerLayout: DrawerLayout
    private lateinit var header: AccountHeader


    init {
        createHeader()
        createDrawer()
        drawerLayout = drawer.drawerLayout
    }

    fun disableDrawer() {
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStackImmediate()
        }
    }

    fun enableDrawer() {
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            drawer.openDrawer()
        }
    }

    private fun createDrawer() {
        drawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(APP_ACTIVITY.toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(header)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Супергонки")
                    .withSelectable(true)
                    .withIcon(R.drawable.ic_cards),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Человек-паук")
                    .withSelectable(true)
                    .withIcon(R.drawable.ic_cards),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Черепашки-ниндзя")
                    .withSelectable(true)
                    .withIcon(R.drawable.ic_cards),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Скуби-Ду")
                    .withSelectable(true)
                    .withIcon(R.drawable.ic_cards)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    clickOnItem(position)
                    return false
                }
            }).build()
    }

    private fun clickOnItem(position: Int) {
        val bundle = when (position) {
            1 -> bundleOf(
                    key_controller to SuperCarsCollectionController()
                )
            2 -> bundleOf(
                    key_controller to SpiderManCollectionController()
                )
            3 -> bundleOf(
                key_controller to NinjaTurtlesController()
            )
            4 -> bundleOf(
                key_controller to ScoobyDooCollectionController()
            )
            else -> null
        }

        APP_ACTIVITY.navController.navigate(
            R.id.severalCollectionsFragment,
            bundle
        )
    }

    private fun createHeader() {
        header = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            .withHeaderBackground(R.drawable.drawer_header_background)
            .build()
        header.view.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.greetingFragment)
            drawer.closeDrawer()
        }
    }

}