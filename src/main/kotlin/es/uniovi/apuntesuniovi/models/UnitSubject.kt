package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import javax.persistence.*

/**
 * Represents units of a subject
 */
@Entity
class UnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subject: Subject? = null

    @Column(length = UnitSubjectLimits.NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UnitSubjectLimits.NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UnitSubjectMessages.LIMIT_NAME)
            }
        }

    @OneToMany(mappedBy = "unitSubject", cascade = [(CascadeType.ALL)])
    val partUnits: Set<PartUnitSubject> = HashSet()

    @ManyToMany(mappedBy = "units", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    val tests: Set<Test> = HashSet()
}