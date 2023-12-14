package app.toilets.base.mapper

/**
 * A mapper interface to be implemented.
 *
 * @param FROM the source model to be mapped from
 * @param TO the target model to be mapped to
 */
interface BaseMapper<FROM, TO> {
    fun map(from: FROM): TO

    fun mapInverse(from: TO): FROM

    fun mapList(from: List<FROM>): List<TO> {
        return ArrayList<TO>().also { models ->
            from.forEach { item ->
                models.add(map(item))
            }
        }
    }

    fun mapListInverse(from: List<TO>): List<FROM> {
        return ArrayList<FROM>().also { models ->
            from.forEach { item ->
                models.add(mapInverse(item))
            }
        }
    }
}