package com.fincance.my_money.ui.principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fincance.my_money.databinding.FragmentPrincipalBinding
import com.fincance.my_money.viewmodel.IncomeViewModel

class PrincipalFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    private lateinit var incomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar o IncomeViewModel
        incomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        // Observar a soma total de receitas
        incomeViewModel.totalIncome.observe(viewLifecycleOwner) { total ->
            val formattedTotal = total?.let { String.format("R$ %.2f", it) } ?: "R$ 0.00"
            binding.textPrincipal.text = "Total de Receitas: $formattedTotal"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
