package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator
import java.time.LocalDate

/**
 * Validate that a date isn´t later than the current day
 */
class ValidatorLaterDayToday(private var date: LocalDate?) : Validator {
    override fun isValid(): Boolean {
        date?.let {
            val aux = LocalDate.now()
            return it.isBefore(aux) || it.isEqual(aux)
        }
        return date == null
    }
}