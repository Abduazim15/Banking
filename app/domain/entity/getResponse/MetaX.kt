package uz.gita.lesson40.domain.entity.getResponse

data class MetaX(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val links: List<LinkX>,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)