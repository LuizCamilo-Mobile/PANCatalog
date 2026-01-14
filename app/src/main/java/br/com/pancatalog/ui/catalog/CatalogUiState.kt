package br.com.pancatalog.ui.catalog

import br.com.pancatalog.domain.model.Item

sealed interface CatalogUiState {
    data object Loading : CatalogUiState
    data class Success(val items: List<Item>) : CatalogUiState
    data object Empty : CatalogUiState
    data class Error(val message: String) : CatalogUiState
}
