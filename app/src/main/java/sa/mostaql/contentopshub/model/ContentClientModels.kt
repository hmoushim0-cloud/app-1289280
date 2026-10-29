package sa.mostaql.contentopshub.model

import kotlinx.serialization.Serializable

@Serializable
data class PublishingTask(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val icon: String = "⭐"
)

@Serializable
data class ContentClient(
    val requestId: String,
    val items: List<PublishingTask>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ContentClientStatus = ContentClientStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ContentClientStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
