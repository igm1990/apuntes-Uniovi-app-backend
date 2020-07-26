package es.uniovi.apuntesuniovi.servicies.dtos.entities

data class PersonDto(
        var id: Long?,
        var name: String,
        var surname: String,
        var email: String,
        var phone: String,
        var active: Boolean,
        var img: String,
        var birthDate: String,
        var username: String,
        var password: String,
        var role: String,
        var identificationType: String,
        var numberIdentification: String
)