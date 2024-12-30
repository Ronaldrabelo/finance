package com.fincance.my_money

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fincance.my_money.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração do BottomNavigationView
        val navView: BottomNavigationView = binding.navView

        // Configuração do NavController
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar o FloatingActionButton
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)
        fabAdd.setOnClickListener {
            // Criando o PopupMenu para Adicionar Receita e Despesa
            val popupMenu = PopupMenu(this, fabAdd)
            popupMenu.menuInflater.inflate(R.menu.menu_add_expense_income, popupMenu.menu)

            // Exibir o PopupMenu
            popupMenu.show()

            // Configurar os cliques nas opções do PopupMenu
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_income -> {
                        // Exemplo: abrir tela de Receita (se já tiver essa funcionalidade)
                         openIncomeFragment()
                        true
                    }
                    R.id.menu_expense -> {
                        // Abrir o ExpenseFragment para Adicionar Despesa
                        openExpenseFragment()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    // Função para navegar até o fragmento de Receita
    private fun openIncomeFragment() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController
        navController.navigate(R.id.action_navigation_home_to_incomeFragment) // Substitua pelo ID correto da ação
    }

    // Função para navegar até o fragmento de Despesa rivate fun openExpenseFragment() {
    private fun openExpenseFragment() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController
        navController.navigate(R.id.action_navigation_home_to_expenseFragment)
    }

}
