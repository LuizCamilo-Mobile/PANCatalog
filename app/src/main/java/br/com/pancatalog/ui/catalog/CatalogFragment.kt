package br.com.pancatalog.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pancatalog.R
import br.com.pancatalog.databinding.FragmentCatalogBinding
import br.com.pancatalog.ui.catalog.adapter.ItemAdapter
import kotlinx.coroutines.launch

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatalogViewModel by viewModels()
    private val adapter = ItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogBinding.bind(view)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.retry.setOnClickListener { viewModel.load() }

        // Coleta segura do Flow: respeita lifecycle do Fragment view
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    render(state)
                }
            }
        }
    }

    private fun render(state: CatalogUiState) {
        when (state) {
            CatalogUiState.Loading -> {
                binding.loading.visibility = View.VISIBLE
                binding.recycler.visibility = View.GONE
                binding.message.visibility = View.GONE
                binding.retry.visibility = View.GONE
            }

            CatalogUiState.Empty -> {
                binding.loading.visibility = View.GONE
                binding.recycler.visibility = View.GONE
                binding.message.visibility = View.VISIBLE
                binding.message.text = "Nenhum item disponível."
                binding.retry.visibility = View.VISIBLE
            }

            is CatalogUiState.Error -> {
                binding.loading.visibility = View.GONE
                binding.recycler.visibility = View.GONE
                binding.message.visibility = View.VISIBLE
                binding.message.text = state.message
                binding.retry.visibility = View.VISIBLE
            }

            is CatalogUiState.Success -> {
                binding.loading.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
                binding.message.visibility = View.GONE
                binding.retry.visibility = View.GONE
                adapter.submitList(state.items)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // evita memory leak (binding referencia view destruída)
    }
}
