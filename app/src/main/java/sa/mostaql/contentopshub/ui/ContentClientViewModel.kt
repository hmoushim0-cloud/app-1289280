package sa.mostaql.contentopshub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.mostaql.contentopshub.data.ContentClientRepository
import sa.mostaql.contentopshub.model.ContentClient
import sa.mostaql.contentopshub.model.PublishingTask
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContentClientViewModel(
    private val repository: ContentClientRepository
) : ViewModel() {
    val catalog: StateFlow<List<PublishingTask>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<ContentClient?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: ContentClient) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
