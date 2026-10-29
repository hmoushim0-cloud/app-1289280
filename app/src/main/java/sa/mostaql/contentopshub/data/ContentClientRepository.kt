package sa.mostaql.contentopshub.data

import sa.mostaql.contentopshub.model.ContentClient
import sa.mostaql.contentopshub.model.PublishingTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ContentClientRepository {
    fun getAvailableCatalog(): Flow<List<PublishingTask>>
    fun getActiveRequest(): Flow<ContentClient?>
    suspend fun submitRequest(request: ContentClient): Result<String>
}

class InMemoryContentClientRepository : ContentClientRepository {
    private val _catalog = MutableStateFlow<List<PublishingTask>>(listOf(
        PublishingTask("1", "باقة الخدمة الأساسية", "تنفيذ متقن ومطابق للمواصفات", 4500L, "⭐"),
        PublishingTask("2", "باقة الخدمة المتقدمة", "دعم فني وضمان جودة الأداء", 9500L, "⚡"),
        PublishingTask("3", "باقة VIP المتكاملة", "تغطية شاملة ومتابعة لحظية", 14000L, "💎")
    ))
    private val _active = MutableStateFlow<ContentClient?>(null)

    override fun getAvailableCatalog(): Flow<List<PublishingTask>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<ContentClient?> = _active.asStateFlow()

    override suspend fun submitRequest(request: ContentClient): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
