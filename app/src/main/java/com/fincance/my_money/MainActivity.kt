package com.fincance.my_money

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fincance.my_money.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate o layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar o BottomNavigationView
        val navView: BottomNavigationView = binding.navView

        // Configurar o NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        // Configurar os destinos principais do AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )

        // Configurar a ActionBar e o BottomNavigationView com o NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar o FloatingActionButton
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)

        fabAdd.setOnClickListener {

            val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController

            // Criar o PopupMenu ou implementar ações globais
            val popupMenu = PopupMenu(this, fabAdd)
            popupMenu.menuInflater.inflate(R.menu.menu_add_expense_income, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_income -> {
                        // Navegar para o fragmento de Receita e limpar a pilha
                        val options = androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.navigation_home, true) // Remove todos os fragmentos até o Home
                            .build()
                        navController.navigate(R.id.incomeFragment, null, options)
                        true
                    }
                    R.id.menu_expense -> {
                        // Navegar para o fragmento de Despesa e limpar a pilha
                        val options = androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.navigation_home, true) // Remove todos os fragmentos até o Home
                            .build()
                        navController.navigate(R.id.expenseFragment, null, options)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

    }

    // Função para exibir o PopupMenu com opções de Adicionar Receita ou Despesa
    private fun showPopupMenu(fabAdd: FloatingActionButton, navController: androidx.navigation.NavController) {
        val popupMenu = PopupMenu(this, fabAdd)
        popupMenu.menuInflater.inflate(R.menu.menu_add_expense_income, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_income -> {
                    // Navegar para o IncomeFragment
                    navController.navigate(R.id.action_navigation_home_to_incomeFragment)
                    true
                }
                R.id.menu_expense -> {
                    // Navegar para o ExpenseFragment
                    navController.navigate(R.id.action_navigation_home_to_expenseFragment)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    // Configurar o botão "Up" na ActionBar
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
