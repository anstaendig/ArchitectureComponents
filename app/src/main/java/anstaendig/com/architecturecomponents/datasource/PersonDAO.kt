package anstaendig.com.architecturecomponents.datasource

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PersonDAO {

    @Query("SELECT * FROM persons WHERE id LIKE :personId")
    fun loadById(personId: String): Flowable<PersonData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonData)

}
