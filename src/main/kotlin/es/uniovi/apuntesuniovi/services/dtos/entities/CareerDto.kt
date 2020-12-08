package es.uniovi.apuntesuniovi.services.dtos.entities

/**
 * Data Transfer Object of careers
 */
data class CareerDto(
    var id: Long?,
    var name: String,
    var code: String? = null,
    var yearImplantation: Int? = null,
    var ECTS: Int? = null,
    var languages: String? = null,
    var centerId: Long? = null,
)
