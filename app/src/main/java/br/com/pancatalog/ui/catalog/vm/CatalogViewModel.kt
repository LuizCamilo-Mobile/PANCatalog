package br.com.pancatalog.ui.catalog.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pancatalog.domain.model.Item
import br.com.pancatalog.ui.catalog.state.CatalogUiState
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

            val shouldFail = false
            val items = listOf(
                Item(1, "Cartão", "Crédito • Platinum"),
                Item(2, "Conta", "Digital • Sem tarifas"),
                Item(3, "Investimentos", "CDB • Liquidez diária")
            )

            _uiState.value = when {
                shouldFail -> CatalogUiState.Error("Falha ao carregar catálogo. Tente novamente.")
                items.isEmpty() -> CatalogUiState.Empty
                else -> CatalogUiState.Success(items)
            }

        }
    }
}