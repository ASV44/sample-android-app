package com.sample.app.presentation.navigation

import android.content.Context
import android.content.Intent
import com.sample.app.presentation.navigation.activities.ExpensesActivity

/**
 * Created by Vdovicenco Alexandr on 03/23/2021.
 */

class Navigator(private val context: Context) {

    fun openExpensesActivity() {
        context.startActivity(Intent(context, ExpensesActivity::class.java))
    }
}
