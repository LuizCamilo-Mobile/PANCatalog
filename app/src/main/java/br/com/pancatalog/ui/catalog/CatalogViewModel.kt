package br.com.pancatalog.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pancatalog.domain.model.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = CatalogUiState.Loading

            // Simula IO pesado (não bloqueante)
            delay(3000)

            val items = listOf(
                Item(1, "Cartão", "Crédito • Platinum"),
                Item(2, "Conta", "Digital • Sem tarifas"),
                Item(3, "Investimentos", "CDB • Liquidez diária")
            )

            _uiState.value =
                if (items.isEmpty()) CatalogUiState.Empty
                else CatalogUiState.Success(items)
        }
    }
}
