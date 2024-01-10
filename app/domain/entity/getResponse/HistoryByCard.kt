package uz.gita.lesson40.domain.entity.getResponse

data class HistoryByCard(
    val `data`: List<DataX>,
    val links: LinksX,
    val meta: MetaX
)