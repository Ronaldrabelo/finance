package com.fincance.my_money.ui.incomes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fincance.my_money.R
import com.fincance.my_money.databinding.FragmentIncomeBinding
import java.text.SimpleDateFormat
import java.util.*

class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar seleção de data
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.etDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    binding.etDate.setText(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Configurar lógica de repetição
        binding.switchRepeat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showRepeatPopup()
            }
        }

        // Salvar a receita
        binding.btnSave.setOnClickListener {
            saveIncome()
        }
    }

    private fun showRepeatPopup() {
        val popupMenu = PopupMenu(requireContext(), binding.switchRepeat)
        popupMenu.menuInflater.inflate(R.menu.menu_repeat_times, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.repeat_1 -> {
                    Toast.makeText(context, "Repetir 1 vez por mês", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.repeat_2 -> {
                    Toast.makeText(context, "Repetir 2 vezes por mês", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun saveIncome() {
        val value = binding.etValue.text.toString().toFloatOrNull()
        val date = binding.etDate.text.toString()
        val description = binding.etDescription.text.toString()

        if (value == null || date.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Lógica para salvar a receita
        Toast.makeText(requireContext(), "Receita salva com sucesso!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
